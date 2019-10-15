package com.dmd.mall.service.impl;

import com.dmd.BigDecimalUtil;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.base.enums.ErrorCodeEnum;
import com.dmd.core.support.BaseService;
import com.dmd.mall.constant.OmsApiConstant;
import com.dmd.mall.exceptions.OmsBizException;
import com.dmd.mall.mapper.OmsCartMapper;
import com.dmd.mall.mapper.OmsOrderMapper;
import com.dmd.mall.mapper.PmsSkuStockMapper;
import com.dmd.mall.model.domain.OmsCart;
import com.dmd.mall.model.domain.OmsOrder;
import com.dmd.mall.model.domain.OmsOrderItem;
import com.dmd.mall.model.domain.PmsSkuStock;
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
@Transactional(rollbackFor = Exception.class)
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
    private RedisTemplate redisTemplate;
    @Value("${redis.key.prefix.orderId}")
    private String REDIS_KEY_PREFIX_ORDER_ID;

    @Override
    public void createOrder(LoginAuthDto loginAuthDto, OrderCreateVo orderCreateVo) {
        Long userId = loginAuthDto.getUserId();
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
            List<OmsCart> cartList = omsCartMapper.selectCartByUserIdAndCartId(userId, cartIds);
            carts.addAll(cartList);

            //计算这个订单的总价
            List<OmsOrderItem> omcOrderDetailList = omsCartService.getCartOrderItem(userId, cartList);

            if (CollectionUtils.isEmpty(omcOrderDetailList)) {
                throw new OmsBizException(ErrorCodeEnum.OMS10031001, userId);
            }
            BigDecimal payment = this.getOrderTotalPrice(omcOrderDetailList);

            //生成订单
            OmsOrder order = this.assembleOrder(userId, orderCreateVo.getShippingId(), payment);
            if (order == null) {
                logger.error("生成订单失败, userId={}, shippingId={}, payment={}", userId, orderCreateVo.getShippingId(), payment);
                throw new OmsBizException(ErrorCodeEnum.OMS10031002);
            }
            order.setUpdateInfo(loginAuthDto);
            for (OmsOrderItem orderDetail : omcOrderDetailList) {
                orderDetail.setUpdateInfo(loginAuthDto);
                orderDetail.setOrderSn(order.getOrderSn());

                orderDetail.setId(super.generateId());
                orderDetail.setUpdateInfo(loginAuthDto);
            }
            //mybatis 批量插入
            omsOrderItemService.batchInsertOrderDetail(omcOrderDetailList);
            //进行库存锁定
            lockStock(omcOrderDetailList);
        };

        //清空一下购物车
        this.cleanCart(carts, loginAuthDto);
        //返回给前端数据
       // return assembleOrderVo(order, omcOrderDetailList);
    }

    private BigDecimal getOrderTotalPrice(List<OmsOrderItem> orderItemList) {
        BigDecimal payment = new BigDecimal("0");
        for (OmsOrderItem orderItem : orderItemList) {
            payment = BigDecimalUtil.add(payment.doubleValue(), orderItem.getTotalPrice().doubleValue());
        }
        return payment;
    }

    private OmsOrder assembleOrder(Long userId, Long shippingId, BigDecimal payment) {
        OmsOrder order = new OmsOrder();

        order.setStatus(OmsApiConstant.OrderStatusEnum.NO_PAY.getCode());
        //运费
        order.setFreightAmount(new BigDecimal(0));
        //订单总的金额
        order.setTotalAmount(payment);
        //todo 实际支付金额待定
        order.setPayAmount(new BigDecimal(0));
        order.setPayType(0);
        order.setSourceType(1);
        order.setMemberId(userId);
        order.setShippingId(shippingId);
        order.setId(super.generateId());
        order.setOrderSn(generateOrderSn(order));
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
    private void lockStock(List<OmsOrderItem> omcOrderDetailList) {
        for (OmsOrderItem omsOrderItem : omcOrderDetailList) {
            PmsSkuStock skuStock = skuStockMapper.selectByPrimaryKey(omsOrderItem.getProductSkuId());
            skuStock.setLockStock(skuStock.getLockStock() + omsOrderItem.getProductQuantity());
            skuStockMapper.updateByPrimaryKeySelective(skuStock);
        }
    }
}
