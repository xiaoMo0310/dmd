package com.dmd.mall.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dmd.BigDecimalUtil;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.base.enums.ErrorCodeEnum;
import com.dmd.core.support.BaseService;
import com.dmd.mall.constant.OmsApiConstant;
import com.dmd.mall.exceptions.OmsBizException;
import com.dmd.mall.exceptions.PmsBizException;
import com.dmd.mall.mapper.*;
import com.dmd.mall.model.domain.*;
import com.dmd.mall.model.dto.OrderParamDto;
import com.dmd.mall.model.dto.PmsCourseOrderDto;
import com.dmd.mall.model.vo.*;
import com.dmd.mall.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
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
    private DmdIntegralGiftService integralGiftService;
    @Autowired
    private UmsMemberService umsMemberService;
    @Autowired
    private OmsShippingMapper omsShippingMapper;
    @Autowired
    private UmsCoachService umsCoachService;
    @Autowired
    private DmdIntegralGiftSpeService integralGiftSpeService;
    @Autowired
    private PmsCourseProductService courseProductService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Value("${redis.key.prefix.orderId}")
    private String REDIS_KEY_PREFIX_ORDER_ID;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderCreateResultVo createOrder(LoginAuthDto loginAuthDto, OrderCreateVo orderCreateVo) {
        Long userId = loginAuthDto.getUserId();
        //查询用户的信息
        UmsMember umsMember = umsMemberService.getById(userId);
        //查询购物车的信息
        List<OrderCreateVo.OrderGroupByShop> orderGroupByShopList = orderCreateVo.getOrderGroupByShopList();
        if (CollectionUtils.isEmpty(orderGroupByShopList)) {
            throw new OmsBizException(ErrorCodeEnum.OMS10031001, userId);
        }
        //总的购物车id
        List<OmsCart> carts = new ArrayList<>(0);
        //总的价格
        BigDecimal totalPayment = new BigDecimal("0.00");
        //要支付订单
        List<OmsOrder> orders = new ArrayList<>(0);
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
            if (orderCreateVo.getIsUserIntegration() == 0) {
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

            //todo 促销待做
            BigDecimal promotionAmount =  new BigDecimal("0.00");
            //生成订单
            OmsOrder order = assembleOrder(umsMember, loginAuthDto.getUserType(), orderGroupByShop.getShopId(), orderCreateVo, payment, postage, integrationAmount,promotionAmount, loginAuthDto, orderGroupByShop.getRemark(), false, false);
            if (order == null) {
                logger.error("生成订单失败, userId={}, shippingId={}, payment={}", userId, orderCreateVo.getShippingId(), payment);
                throw new OmsBizException(ErrorCodeEnum.OMS10031002);
            }
            //全部需要支付的价格
            totalPayment = totalPayment.add(order.getPayAmount());
            orders.add(order);
            //封装订单详情中订单的信息
            for (OmsOrderItem orderItem : orderItemList) {
                orderItem.setOrderId(order.getId());
                orderItem.setOrderSn(order.getOrderSn());
                orderItem.setUpdateInfo(loginAuthDto);
            }
            //保存订单详情数据
            omsOrderItemService.batchInsertOrderDetail(orderItemList);
            //进行库存锁定
            lockStock(orderItemList);
        };

        //清空购物车
        this.cleanCart(carts, loginAuthDto);
        //返回给前端数据
        return assembleOrderVo(totalPayment, orders);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createIntegralOrder(LoginAuthDto loginAuthDto, OrderParamDto orderParamDto) {
        //查询用户的信息
        UmsMember umsMember = umsMemberService.getById(loginAuthDto.getUserId());
        //查询积分商品信息
        DmdIntegralGift integralGift = integralGiftService.selectByKey(orderParamDto.getProductId());
        if(integralGift == null){
            throw new PmsBizException(ErrorCodeEnum.PMS10021026);
        }

        //封装积分好礼订单详情数据
        OmsOrderItem orderItem = integralGiftService.createIntegralOrderItem(integralGift, orderParamDto.getProductSkuId(), orderParamDto.getQuantity());
        //查询积分规则信息
        UmsIntegrationRuleSetting integrationRuleSetting = integrationRuleSettingMapper.selectByPrimaryKey(1L);
        //订单积分抵扣的钱数
        BigDecimal integrationAmount = new BigDecimal("0.00");
        //判断是否使用积分
        if (orderParamDto.getIsUserIntegration() == 1) {
            //判单商品的积分和使用的商品积分相同
            if(!orderParamDto.getUseIntegration().equals(integralGift.getIntegral() * orderParamDto.getQuantity())){
                throw new OmsBizException(ErrorCodeEnum.OMS10031015);
            }
            //判断用户是否有这么多积分
            if (orderParamDto.getUseIntegration().compareTo(umsMember.getIntegration()) > 0) {
                throw new OmsBizException(ErrorCodeEnum.OMS10031015);
            }
            integrationAmount = new BigDecimal(orderParamDto.getUseIntegration()).divide(new BigDecimal(integrationRuleSetting.getUseUnit()), 2, RoundingMode.HALF_EVEN);
        }else {
            throw new OmsBizException(ErrorCodeEnum.OMS10031015);
        }
        //该订单需要支付的运费 todo 运费计算逻辑待开发
        BigDecimal postage = new BigDecimal("0.00");
        //todo 促销待做
        BigDecimal promotionAmount =  new BigDecimal("0.00");
        OrderCreateVo orderCreateVo = new OrderCreateVo();
        BeanUtils.copyProperties(orderParamDto, orderCreateVo);
        //封装订单的信息
        OmsOrder order = assembleOrder(umsMember, loginAuthDto.getUserType(), 0L, orderCreateVo, integrationAmount,
                postage, integrationAmount, promotionAmount, loginAuthDto, orderParamDto.getRemark(), true, false);
        if (order == null) {
            logger.error("生成订单失败, userId={}, shippingId={}, payment={}", umsMember.getId(), orderCreateVo.getShippingId(),  integrationAmount);
            throw new OmsBizException(ErrorCodeEnum.OMS10031002);
        }
        //保存订单详情数据
        orderItem.setOrderId(order.getId());
        orderItem.setOrderSn(order.getOrderSn());
        orderItem.setUpdateInfo(loginAuthDto);
        omsOrderItemService.save(orderItem);
        //扣减积分
        if(orderParamDto.getIsUserIntegration() == 1){
            umsMemberService.updateIntegration(umsMember, orderParamDto.getUseIntegration(), "积分兑换商品扣减积分", 1);
        }
        //减库存
        reduceIntegralProductInventory(loginAuthDto,orderItem.getProductSkuId(), orderItem.getProductQuantity());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject createCourseProductOrder(LoginAuthDto loginAuthDto, PmsCourseOrderDto orderParamDto) {
        //查询用户的信息
        UmsMember umsMember = umsMemberService.getById(loginAuthDto.getUserId());
        //查询潜水学证商品信息
        PmsCourseProduct pmsProduct = courseProductService.selectByKey(orderParamDto.getProductId());
        if(pmsProduct == null){
            throw new PmsBizException(ErrorCodeEnum.PMS10021004);
        }
        //判断商品的信息
        //产品类型(1:学证 2:潜水)
        Integer productType = pmsProduct.getProductType();
        //封装商品订单详情数据
        OmsOrderItem orderItem = courseProductService.createOrderItem(pmsProduct);
        //订单积分抵扣的钱数
        BigDecimal integrationAmount = new BigDecimal("0.00");
        //判断是否使用积分
        if (orderParamDto.getIsUserIntegration() == 0) {
            //不使用积分
            orderItem.setIntegrationAmount(new BigDecimal(0));
        } else {
            //使用积分
            integrationAmount = getUseIntegrationAmount(orderParamDto.getUseIntegration(), orderItem.getTotalPrice(), umsMember, false);
            if (integrationAmount.compareTo(new BigDecimal(0)) == 0) {
                throw new OmsBizException(ErrorCodeEnum.OMS10031015);
            } else {
                //分摊到可用商品中
                BigDecimal perAmount = orderItem.getProductPrice().divide(orderItem.getTotalPrice() , 3, RoundingMode.HALF_EVEN).multiply(integrationAmount);
                orderItem.setIntegrationAmount(perAmount);
            }
        }
        //该订单需要支付的运费 todo 运费计算逻辑待开发
        BigDecimal postage = new BigDecimal("0.00");
        if(productType == 3){
            postage = new BigDecimal("0.00");
        }
        //todo 促销待做
        BigDecimal promotionAmount =  new BigDecimal("0.00");
        OrderCreateVo orderCreateVo = new OrderCreateVo();
        orderCreateVo.setShippingId(0L);
        BeanUtils.copyProperties(orderParamDto, orderCreateVo);
        //封装订单的信息
        OmsOrder order;
        if(productType == 1){
            order = assembleOrder(umsMember, loginAuthDto.getUserType(), pmsProduct.getShopId(), orderCreateVo, orderItem.getTotalPrice(),
                    postage, integrationAmount, promotionAmount, loginAuthDto, orderParamDto.getRemark(), false, true);
        }else {
            order = assembleOrder(umsMember, loginAuthDto.getUserType(), pmsProduct.getShopId(), orderCreateVo, orderItem.getTotalPrice(),
                    postage, integrationAmount, promotionAmount, loginAuthDto, orderParamDto.getRemark(), false, true);
        }
        if (order == null) {
            logger.error("生成订单失败, userId={}, shippingId={}, payment={}", umsMember.getId(), orderCreateVo.getShippingId(),  orderItem.getTotalPrice());
            throw new OmsBizException(ErrorCodeEnum.OMS10031002);
        }
        //保存订单详情数据
        orderItem.setOrderId(order.getId());
        orderItem.setOrderSn(order.getOrderSn());
        orderItem.setUpdateInfo(loginAuthDto);
        omsOrderItemService.save(orderItem);
        //扣减库存
        reduceInventory(pmsProduct.getId(), loginAuthDto);
        //封装返回数据
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("totalPayment", order.getPayAmount());
        jsonObject.put("orderSn", order.getOrderSn());
        return jsonObject;
    }

    @Override
    public int cancelOrderDoc(LoginAuthDto loginAuthDto, String orderSn) {
        OmsOrder order = getOmsOrderByOrderSn(loginAuthDto, orderSn);
        if (order.getStatus() != OmsApiConstant.OrderStatusEnum.NO_PAY.getCode()) {
            throw new OmsBizException(ErrorCodeEnum.OMS10031004);
        }
        order.setUpdateInfo(loginAuthDto);
        order.setStatus(OmsApiConstant.OrderStatusEnum.CANCELED.getCode());
        return omsOrderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public CourseOrderDetailVo getUserOrderDetail(LoginAuthDto loginAuthDto, String orderSn) {
        //获取当前登录人信息
        UmsMember umsMember = umsMemberService.selectByLoginAuthDto(loginAuthDto);
        CourseOrderDetailVo courseOrderDetailVo = omsOrderMapper.selectUserOrderByOrderSn(umsMember.getId(), orderSn);
        if(courseOrderDetailVo == null){
            throw new OmsBizException(ErrorCodeEnum.OMS10031020);
        }
        courseOrderDetailVo.setProductPic(courseOrderDetailVo.getProductPic().split(",")[0]);
        courseOrderDetailVo.setPhone(umsMember.getPhone());
        return courseOrderDetailVo;
    }

    @Override
    public CourseOrderDetailVo getSellerOrderDetail(LoginAuthDto loginAuthDto, String orderSn) {
        UmsCoach umsCoach = umsCoachService.selectByLoginAuthDto(loginAuthDto);
        CourseOrderDetailVo courseOrderDetailVo = omsOrderMapper.selectSellerOrderByOrderSn(umsCoach.getId(), orderSn);
        if(courseOrderDetailVo == null){
            throw new OmsBizException(ErrorCodeEnum.OMS10031020);
        }
        courseOrderDetailVo.setProductPic(courseOrderDetailVo.getProductPic().split(",")[0]);
        return courseOrderDetailVo;
    }

    @Override
    public PageInfo queryUserOrderList(LoginAuthDto loginAuthDto, Integer pageNum, Integer pageSize, Integer status) {
        //获取当前登录人信息
        UmsMember umsMember = umsMemberService.selectByLoginAuthDto(loginAuthDto);
        //查询该用户下的的订单
        PageHelper.startPage(pageNum, pageSize);
        List<CourseOrderDetailVo> courseOrderDetailVos = omsOrderMapper.selectUserOrderByStatus(umsMember.getId(), loginAuthDto.getUserType(), status);
        //截取图片
        courseOrderDetailVos.forEach(courseOrderDetailVo -> courseOrderDetailVo.setProductPic(courseOrderDetailVo.getProductPic().split(",")[0]));
        PageInfo<CourseOrderDetailVo> orderDetailVoPageInfo = new PageInfo<>(courseOrderDetailVos);
        return orderDetailVoPageInfo;
    }

    @Override
    public PageInfo querySellerOrderListWithPage(LoginAuthDto loginAuthDto, Integer pageNum, Integer pageSize, Integer status) {
        UmsCoach umsCoach = umsCoachService.selectByLoginAuthDto(loginAuthDto);
        //查询该卖家下的的订单
        PageHelper.startPage(pageNum, pageSize);
        List<CourseOrderDetailVo> courseOrderDetailVos = omsOrderMapper.selectSellerOrderByStatus(umsCoach.getId(), loginAuthDto.getUserType(), status);
        courseOrderDetailVos.forEach(courseOrderDetailVo -> courseOrderDetailVo.setProductPic(courseOrderDetailVo.getProductPic().split(",")[0]));
        PageInfo<CourseOrderDetailVo> orderDetailVoPageInfo = new PageInfo<>(courseOrderDetailVos);
        return orderDetailVoPageInfo;
    }

    @Override
    public int confirmationCompletedOrder(LoginAuthDto loginAuthDto, String orderSn) {
        OmsOrder order = getOmsOrderByOrderSn(loginAuthDto, orderSn);
        if (order.getStatus() != OmsApiConstant.OrderStatusEnum.SHIPPED.getCode()) {
            throw new OmsBizException(ErrorCodeEnum.OMS10031022);
        }
        order.setUpdateInfo(loginAuthDto);
        order.setEndTime(new Date());
        order.setConfirmStatus(1);
        order.setStatus(OmsApiConstant.OrderStatusEnum.ORDER_SUCCESS.getCode());
        return omsOrderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public OmsOrder getOmsOrderByOrderSn(LoginAuthDto loginAuthDto, String orderSn) {
        //查询订单
        Long userId = loginAuthDto.getUserId();
        OmsOrder order = omsOrderMapper.selectByUserIdAndOrderNo(userId, loginAuthDto.getUserType(), orderSn);
        if (order == null) {
            logger.error("该用户此订单不存在, userId={}, orderNo={}", userId, orderSn);
            throw new OmsBizException(ErrorCodeEnum.OMS10031003);
        }
        return order;
    }

    @Override
    public OmsOrder getOmsOrderByOrderId(LoginAuthDto loginAuthDto, Long orderId) {
        //查询订单
        Long userId = loginAuthDto.getUserId();
        OmsOrder order = omsOrderMapper.selectByUserIdAndOrderId(userId, loginAuthDto.getUserType(), orderId);
        if (order == null) {
            logger.error("该用户此订单不存在, userId={}, orderNo={}", userId, orderId);
            throw new OmsBizException(ErrorCodeEnum.OMS10031003);
        }
        return order;
    }

    /**
     *计算每个商铺订单总的金额
     * @param orderItemList
     * @return
     */
    private BigDecimal getOrderTotalPrice(List<OmsOrderItem> orderItemList) {
        BigDecimal payment = new BigDecimal("0.00");
        for (OmsOrderItem orderItem : orderItemList) {
            payment = BigDecimalUtil.add(payment.doubleValue(), orderItem.getTotalPrice().doubleValue());
        }
        return payment;
    }

    /**
     * 封装订单信息并保存
     */
    private OmsOrder assembleOrder(UmsMember umsMember, String userType, Long shopId, OrderCreateVo orderCreateVo, BigDecimal payment, BigDecimal postage, BigDecimal integrationAmount, BigDecimal promotionAmount, LoginAuthDto loginAuthDto, String remark,Boolean isIntegralProduct, Boolean isCourseProduct) {
        OmsOrder order = new OmsOrder();
        order.setStatus(OmsApiConstant.OrderStatusEnum.NO_PAY.getCode());
        order.setDeleteStatus(0);
        order.setUserType(userType);
        order.setFreightAmount(postage);
        order.setIntegrationAmount(integrationAmount);
        order.setPromotionAmount(promotionAmount);
        //todo 订单生成的积分数逻辑规则待做
        order.setIntegration(0);
        order.setUseIntegration(orderCreateVo.getUseIntegration());
        //订单总的金额
        order.setTotalAmount(payment);
        //实际需要支付的金额
        order.setPayAmount(payment.add(postage).subtract(integrationAmount).subtract(promotionAmount));
        order.setPayType(0);
        order.setOrderType(0);
        order.setApprovalStatus(2);
        order.setShippingId(orderCreateVo.getShippingId());
        order.setSourceType(1);
        order.setMemberId(umsMember.getId());
        order.setShopId(shopId);
        order.setMemberUsername(umsMember.getUsername());
        order.setIsInvoice(orderCreateVo.getIsInvoice());
        order.setConfirmStatus(0);
        if(orderCreateVo.getIsInvoice() == 1){
            order.setInvoiceId(orderCreateVo.getInvoiceId());
        }else {
            order.setInvoiceId(0L);
        }
        if(isCourseProduct){
            order.setOrderType(1);
        }
        if(isIntegralProduct){
            order.setTotalAmount(new BigDecimal(0.00));
            order.setStatus(OmsApiConstant.OrderStatusEnum.SHIPPED.getCode());
            order.setPayType(3);
            order.setPaymentTime(new Date());
            order.setOrderType(2);
        }
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
        omcOrderDetailList.forEach(omsOrderItem -> {
            PmsSkuStock skuStock = skuStockMapper.selectByPrimaryKey(omsOrderItem.getProductSkuId());
            if (omsOrderItem.getProductQuantity() > (skuStock.getStock() - skuStock.getLockStock())) {
                logger.error("商品库存不足, productId={}", omsOrderItem.getProductId());
                throw new OmsBizException(ErrorCodeEnum.PMS10021016, omsOrderItem.getProductId());
            }
            skuStock.setLockStock(skuStock.getLockStock() + omsOrderItem.getProductQuantity());
            int result = skuStockMapper.updateByPrimaryKeySelective(skuStock);
            if(result <= 0){
                throw new PmsBizException(ErrorCodeEnum.PMS10021028);
            }
        });
    }

    /**
     * 减潜水学证商品的库存
     * @param productId
     */
    public synchronized void reduceInventory(Long productId, LoginAuthDto loginAuthDto){
        //查询商品信息
        PmsCourseProduct courseProduct = courseProductService.selectByKey(productId);
        if(courseProduct.getStock() == 0){
            logger.error("商品已售完");
            throw new PmsBizException(ErrorCodeEnum.PMS10021016, courseProduct.getId());
        }
        //减库存
        courseProduct.setStock(courseProduct.getStock() -1);
        if(courseProduct.getStock() == 0){
            courseProduct.setStatus(5);
        }
        courseProduct.setUpdateInfo(loginAuthDto);
        int result = courseProductService.update(courseProduct);
        if(result <= 0){
            throw new PmsBizException(ErrorCodeEnum.PMS10021028);
        }
    }

    /**
     * 减少积分商品的库存
     * @param loginAuthDto
     * @param skuId
     */
    public synchronized void reduceIntegralProductInventory(LoginAuthDto loginAuthDto, Long skuId, Integer quantity){
        DmdIntegralGiftSpe dmdIntegralGiftSpe = integralGiftSpeService.selectByKey(skuId);
        if(dmdIntegralGiftSpe.getSpecStock() == 0 || quantity > dmdIntegralGiftSpe.getSpecStock()){
            logger.error("商品已售完");
            throw new PmsBizException(ErrorCodeEnum.PMS10021016, dmdIntegralGiftSpe.getId());
        }
        dmdIntegralGiftSpe.setSpecStock(dmdIntegralGiftSpe.getSpecStock() - quantity);
        int result = integralGiftSpeService.update(dmdIntegralGiftSpe);
        if(result <= 0){
            throw new PmsBizException(ErrorCodeEnum.PMS10021028);
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

    /**
     * 创建订单成功后需要返回的数据
     */
    public OrderCreateResultVo assembleOrderVo(BigDecimal totalPayemnt, List<OmsOrder> order){
        OrderCreateResultVo orderVo = new OrderCreateResultVo();
        List<String> orderSns = order.stream().map(OmsOrder::getOrderSn).collect(Collectors.toList());
        orderVo.setTotalPayment(totalPayemnt);
        orderVo.setOrderSns(orderSns);
        return orderVo;
    };

    private OrderVo assembleOrderVo(OmsOrder order, List<OmsOrderItem> orderItemList) {
        OrderVo orderVo = new OrderVo();
        BeanUtils.copyProperties(order, orderVo);
        orderVo.setStatusDesc(OmsApiConstant.OrderStatusEnum.codeOf(order.getStatus()).getValue());
        OmsShipping shipping = omsShippingMapper.selectByPrimaryKey(order.getShippingId());
        if (shipping != null) {
            orderVo.setShippingVo(assembleShippingVo(shipping));
        }
        List<OrderItemVo> orderItemVoList = orderItemList.stream().map(orderItem -> assembleOrderItemVo(orderItem)).collect(Collectors.toList());
        orderVo.setOrderItemVoList(orderItemVoList);
        return orderVo;
    }

    /**
     * 收货地址数据封装
     */
    private ShippingVo assembleShippingVo(OmsShipping shipping) {
        ShippingVo shippingVo = new ShippingVo();
        BeanUtils.copyProperties(shipping, shippingVo);
        return shippingVo;
    }

    /**
     * 订单详情信息的返回
     * @param orderItem
     * @return
     */
    private OrderItemVo assembleOrderItemVo(OmsOrderItem orderItem) {
        logger.info("订单信息 orderItem={}", orderItem);
        OrderItemVo orderItemVo = new OrderItemVo();
        BeanUtils.copyProperties(orderItem, orderItemVo);
        return orderItemVo;
    }

}
