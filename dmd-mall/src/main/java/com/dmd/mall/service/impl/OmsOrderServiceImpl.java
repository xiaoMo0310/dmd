package com.dmd.mall.service.impl;

import com.dmd.BigDecimalUtil;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.base.enums.ErrorCodeEnum;
import com.dmd.core.support.BaseService;
import com.dmd.mall.constant.OmsApiConstant;
import com.dmd.mall.exceptions.OmsBizException;
import com.dmd.mall.mapper.*;
import com.dmd.mall.model.domain.*;
import com.dmd.mall.model.vo.OrderCreateVo;
import com.dmd.mall.service.OmsCartService;
import com.dmd.mall.service.OmsOrderItemService;
import com.dmd.mall.service.OmsOrderService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-14
 */
@Service
public class OmsOrderServiceImpl extends BaseService<OmsOrder> implements OmsOrderService {

    @Autowired
    private OmsOrderMapper omsOrderMapper;
    @Autowired
    private OmsCartMapper omsCartMapper;
    @Autowired
    private OmsOrderItemService omsOrderItemService;
    @Autowired
    private OmsCartService omsCartService;
    @Autowired
    private PmsSkuStockMapper skuStockMapper;
    @Autowired
    private UmsIntegrationRuleSettingMapper integrationRuleSettingMapper;
    @Autowired
    private UmsMemberMapper umsMemberMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Value("${redis.key.prefix.orderId}")
    private String REDIS_KEY_PREFIX_ORDER_ID;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createOrder(LoginAuthDto loginAuthDto, OrderCreateVo orderCreateVo) {
        Long userId = loginAuthDto.getUserId();
        //查询用户的信息
        UmsMember umsMember = umsMemberMapper.selectByPrimaryKey(userId);
        //查询购物车的信息
        List<OrderCreateVo.OrderGroupByShop> orderGroupByShopList = orderCreateVo.getOrderGroupByShopList();
        if (CollectionUtils.isEmpty(orderGroupByShopList)) {
            throw new OmsBizException(ErrorCodeEnum.OMS10031001, userId);
        }
        //总的购物车id
        List<OmsCart> carts = new ArrayList<>(0);
        for (OrderCreateVo.OrderGroupByShop orderGroupByShop : orderGroupByShopList) {
            List<OrderCreateVo.OrderDetailGroupByShop> orderDetailGroupByShop = orderGroupByShop.getOrderDetailGroupByShop();
            if (CollectionUtils.isEmpty(orderDetailGroupByShop)) {
                continue;
            }
            List<Long> cartIds = orderDetailGroupByShop.stream().map(OrderCreateVo.OrderDetailGroupByShop::getCartId).collect(Collectors.toList());
            List<OmsCart> cartList = omsCartMapper.selectCartByUserIdAndCartId(userId, orderGroupByShop.getShopId(), cartIds);
            carts.addAll(cartList);

            //计算这个订单的总价
            List<OmsOrderItem> orderItemList = omsCartService.getCartOrderItem(userId, cartList);

            if (CollectionUtils.isEmpty(orderItemList)) {
                throw new OmsBizException(ErrorCodeEnum.OMS10031001, userId);
            }
            //订单总的金额
            BigDecimal payment = this.getOrderTotalPrice(orderItemList);
            if(payment.compareTo(orderGroupByShop.getShopPayment()) != 0){
                throw new OmsBizException(ErrorCodeEnum.OMS10031017);
            }
            //该订单需要支付的运费 todo 运费计算逻辑待开发
            BigDecimal postage = new BigDecimal("0.00");
            if(postage.compareTo(orderGroupByShop.getPostage()) != 0){
                throw new OmsBizException(ErrorCodeEnum.OMS10031018);
            }
            //订单积分抵扣的钱数
            BigDecimal integrationAmount = new BigDecimal("0.00");
            //判断是否使用积分
            if (!orderCreateVo.getIsUserIntegration()) {
                //不使用积分
                for (OmsOrderItem orderItem : orderItemList) {
                    orderItem.setIntegrationAmount(new BigDecimal(0));
                }
            } else {
                //使用积分
                integrationAmount = getUseIntegrationAmount(orderCreateVo.getUseIntegration(), payment, umsMember, false);
                if (integrationAmount.compareTo(new BigDecimal(0)) == 0) {
                    throw new OmsBizException(ErrorCodeEnum.OMS10031015);
                } else {
                    //分摊到可用商品中
                    for (OmsOrderItem orderItem : orderItemList) {
                        BigDecimal perAmount = orderItem.getProductPrice().divide(payment , 3, RoundingMode.HALF_EVEN).multiply(integrationAmount);
                        orderItem.setIntegrationAmount(perAmount);
                    }
                }
            }
            //生成订单
            OmsOrder order = this.assembleOrder(umsMember, orderGroupByShop.getShopId(), orderCreateVo, payment, postage, integrationAmount, loginAuthDto, orderGroupByShop.getRemark());
            if (order == null) {
                logger.error("生成订单失败, userId={}, shippingId={}, payment={}", userId, orderCreateVo.getShippingId(), payment);
                throw new OmsBizException(ErrorCodeEnum.OMS10031002);
            }

            //保存订单详情数据
            omsOrderItemService.batchInsertOrderDetail(orderItemList);
            //进行库存锁定
            lockStock(orderItemList);
        };

        //清空一下购物车
        this.cleanCart(carts, loginAuthDto);
        //返回给前端数据
       // return assembleOrderVo(order, omcOrderDetailList);
    }

    /**
     *计算每个商铺订单总的金额
     * @param orderItemList
     * @return
     */
    private BigDecimal getOrderTotalPrice(List<OmsOrderItem> orderItemList) {
        BigDecimal payment = new BigDecimal("0");
        for (OmsOrderItem orderItem : orderItemList) {
            payment = BigDecimalUtil.add(payment.doubleValue(), orderItem.getTotalPrice().doubleValue());
        }
        return payment;
    }

    private OmsOrder assembleOrder(UmsMember umsMember, Long shopId, OrderCreateVo orderCreateVo, BigDecimal payment, BigDecimal postage, BigDecimal integrationAmount, LoginAuthDto loginAuthDto, String remark) {
        OmsOrder order = new OmsOrder();

        order.setStatus(OmsApiConstant.OrderStatusEnum.NO_PAY.getCode());
        order.setDeleteStatus(0);
        order.setFreightAmount(postage);
        order.setIntegrationAmount(integrationAmount);
        //todo 订单生成的积分数逻辑规则待做
        order.setIntegration(0);
        order.setUseIntegration(orderCreateVo.getUseIntegration());
        //订单总的金额
        order.setTotalAmount(payment);
        //实际需要支付的金额
        order.setPayAmount(payment.add(postage).subtract(integrationAmount));
        order.setPayType(0);
        order.setSourceType(1);
        order.setMemberId(umsMember.getId());
        order.setShopId(shopId);
        order.setMemberUsername(umsMember.getUsername());
        order.setShippingId(orderCreateVo.getShippingId());
        order.setIsInvoice(order.getIsInvoice());
        if(order.getIsInvoice() == 1){
            order.setInvoiceId(orderCreateVo.getInvoiceId());
        }
        order.setOrderType(0);
        order.setRemark(remark);
        order.setOrderSn(generateOrderSn(order));
        order.setUpdateInfo(loginAuthDto);
        int rowCount = omsOrderMapper.insertSelective(order);
        if (rowCount > 0) {
            return order;
        }
        return null;
    }

    /**
     * 清空购物车
     * @param cartList
     */
    private void cleanCart(List<OmsCart> cartList, LoginAuthDto loginAuthDto) {
        cartList.forEach(cart -> {
            cart.setUpdateInfo(loginAuthDto);
            cart.setDeleteStatus(1);
            omsCartMapper.updateByPrimaryKeySelective(cart);
        });
    }

    /**
     * 生成18位订单编号:8位日期+2位平台号码+2位支付方式+6位以上自增id
     */
    private String generateOrderSn(OmsOrder order) {
        StringBuilder sb = new StringBuilder();
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String key = REDIS_KEY_PREFIX_ORDER_ID + date;
        Long increment = redisTemplate.opsForValue().increment(key, 1);
        sb.append(date);
        sb.append(String.format("%02d", order.getSourceType()));
        sb.append(String.format("%02d", order.getPayType()));
        String incrementStr = increment.toString();
        if (incrementStr.length() <= 6) {
            sb.append(String.format("%06d", increment));
        } else {
            sb.append(incrementStr);
        }
        return sb.toString();
    }

    /**
     * 锁定下单商品的所有库存
     */
    private synchronized void lockStock(List<OmsOrderItem> omcOrderDetailList) {
        for (OmsOrderItem omsOrderItem : omcOrderDetailList) {
            PmsSkuStock skuStock = skuStockMapper.selectByPrimaryKey(omsOrderItem.getProductSkuId());
            skuStock.setLockStock(skuStock.getLockStock() + omsOrderItem.getProductQuantity());
            skuStockMapper.updateByPrimaryKeySelective(skuStock);
        }
    }

    /**
     * 获取可用积分抵扣金额
     *
     * @param useIntegration 使用的积分数量
     * @param totalAmount    订单总金额
     * @param currentMember  使用的用户
     * @param hasCoupon      是否已经使用优惠券
     */
    private BigDecimal getUseIntegrationAmount(Integer useIntegration, BigDecimal totalAmount, UmsMember currentMember, boolean hasCoupon) {
        BigDecimal zeroAmount = new BigDecimal(0);
        //判断用户是否有这么多积分
        if (useIntegration.compareTo(currentMember.getIntegration()) > 0) {
            return zeroAmount;
        }
        //根据积分使用规则判断是否可用
        //是否可与优惠券共用
        UmsIntegrationRuleSetting integrationRuleSetting = integrationRuleSettingMapper.selectByPrimaryKey(1L);
        if (hasCoupon && integrationRuleSetting.getCouponStatus().equals(0)) {
            //不可与优惠券共用
            return zeroAmount;
        }
        //是否达到最低使用积分门槛
        if (useIntegration.compareTo(integrationRuleSetting.getUseUnit()) < 0) {
            return zeroAmount;
        }
        //是否超过订单抵用最高百分比
        BigDecimal integrationAmount = new BigDecimal(useIntegration).divide(new BigDecimal(integrationRuleSetting.getUseUnit()), 2, RoundingMode.HALF_EVEN);
        BigDecimal maxPercent = new BigDecimal(integrationRuleSetting.getMaxPercentPerOrder()).divide(new BigDecimal(100), 2, RoundingMode.HALF_EVEN);
        if (integrationAmount.compareTo(totalAmount.multiply(maxPercent)) > 0) {
            return zeroAmount;
        }
        return integrationAmount;
    }

}
