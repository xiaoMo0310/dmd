package com.dmd.mall.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.dmd.IdWorker;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.base.enums.ErrorCodeEnum;
import com.dmd.core.support.BaseService;
import com.dmd.mall.constant.OmsApiConstant;
import com.dmd.mall.exceptions.OmsBizException;
import com.dmd.mall.exceptions.PmsBizException;
import com.dmd.mall.exceptions.UmsBizException;
import com.dmd.mall.mapper.OmsIntegralOrderMapper;
import com.dmd.mall.model.domain.*;
import com.dmd.mall.model.dto.OrderParamDto;
import com.dmd.mall.model.vo.IntegralOrderDetailVo;
import com.dmd.mall.model.vo.OrderCreateVo;
import com.dmd.mall.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * <p>
 * 积分好礼订单表 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-12-23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OmsIntegralOrderServiceImpl extends BaseService<OmsIntegralOrder> implements OmsIntegralOrderService {

    @Autowired
    private OmsIntegralOrderMapper omsIntegralOrderMapper;
    @Autowired
    private UmsMemberService umsMemberService;
    @Autowired
    private UmsCoachService umsCoachService;
    @Autowired
    private DmdIntegralGiftService integralGiftService;
    @Autowired
    private DmdIntegralGiftSpeService integralGiftSpeService;
    @Autowired
    private OmsShippingService shippingService;
    @Autowired
    private IdWorker idWorker;
    @Override
    public void createIntegralOrder(LoginAuthDto loginAuthDto, OrderParamDto orderParamDto) {
        UmsMember umsMember = null;
        UmsCoach umsCoach = null;
        if(loginAuthDto.getUserType().equals("member")){
            //查询用户信息
            umsMember = umsMemberService.getById(loginAuthDto.getUserId());
            Optional.ofNullable(umsMember).orElseThrow(() -> new UmsBizException("用户信息不存在"));
        }else if(loginAuthDto.getUserType().equals("coach")){
            //查询教练信息
            umsCoach = umsCoachService.selectByLoginAuthDto(loginAuthDto);
            Optional.ofNullable(umsCoach).orElseThrow(() -> new UmsBizException("教练信息不存在"));
        }
        //查询积分商品信息
        DmdIntegralGift integralGift = integralGiftService.selectByKey(orderParamDto.getProductId());
        if(integralGift == null){
            throw new PmsBizException(ErrorCodeEnum.PMS10021026);
        }

        /*//封装积分好礼订单详情数据
        OmsOrderItem orderItem = integralGiftService.createIntegralOrderItem(integralGift, orderParamDto.getProductSkuId(), orderParamDto.getQuantity());*/

        //判单商品的积分和使用的商品积分相同
        /*if(!orderParamDto.getUseIntegration().equals(integralGift.getIntegral() * orderParamDto.getQuantity())){
            throw new OmsBizException(ErrorCodeEnum.OMS10031015);
        }*/
        //判断用户是否有这么多积分
        Integer useIntegration = integralGift.getIntegral() * orderParamDto.getQuantity();
        if(loginAuthDto.getUserType().equals("member")){
            if (useIntegration.compareTo(umsMember.getIntegration()) > 0) {
                throw new OmsBizException("积分不足");
            }
        }else if(loginAuthDto.getUserType().equals("coach")){
            if (useIntegration.compareTo(umsCoach.getIntegration()) > 0) {
                throw new OmsBizException("积分不足");
            }
        }

        //该订单需要支付的运费 todo 运费计算逻辑待开发
        BigDecimal postage = new BigDecimal("0.00");
        OrderCreateVo orderCreateVo = new OrderCreateVo();
        BeanUtils.copyProperties(orderParamDto, orderCreateVo);
        orderCreateVo.setUseIntegration(useIntegration);
        //封装订单的信息
        OmsIntegralOrder order = assembleIntegralOrder(loginAuthDto.getUserType(), 0L, orderCreateVo, postage, loginAuthDto, 
                                                    orderParamDto.getRemark(), orderParamDto.getProductSkuId(), integralGift, orderParamDto.getQuantity());
        if (order == null) {
            logger.error("生成订单失败, shippingId={}, payment={}", orderCreateVo.getShippingId());
            throw new OmsBizException(ErrorCodeEnum.OMS10031002);
        }
        //扣减积分
        if(loginAuthDto.getUserType().equals("member")){
            //扣减用户积分
            umsMemberService.updateIntegration(umsMember, -useIntegration, "积分兑换商品扣减积分", 1);
        }else if(loginAuthDto.getUserType().equals("coach")){
            //扣减教练积分
            umsCoachService.updateIntegration(umsCoach, -useIntegration, "积分兑换商品扣减积分", 1);
        }
        //减库存
        reduceIntegralProductInventory(loginAuthDto,order.getProductSkuId(), order.getProductQuantity());
    }

    @Override
    public PageInfo queryIntegralOrderListWithPage(LoginAuthDto loginAuthDto, Integer pageNum, Integer pageSize, Integer status) {
        //查询该用户下的的订单
        PageHelper.startPage(pageNum, pageSize);
        List<IntegralOrderDetailVo> integralOrderDetailVos = null;
        if(status == 2){
            List<Integer> statusList = Arrays.asList(1, 2);
            integralOrderDetailVos = omsIntegralOrderMapper.selectIntegralOrderByStatus(loginAuthDto.getUserId(), loginAuthDto.getUserType(), statusList);
        }else {
            integralOrderDetailVos = omsIntegralOrderMapper.selectUserOrderByStatus(loginAuthDto.getUserId(), loginAuthDto.getUserType(), status);
        }
        //截取图片 获取尺码数据
        /*if(!CollectionUtils.isEmpty(integralOrderDetailVos)){
            integralOrderDetailVos.forEach(integralOrderDetailVo -> {
                integralOrderDetailVo.setProductPic(integralOrderDetailVo.getProductPic().split(",")[0]);
                List<Map> maps = (List<Map>) JSONArray.parse(integralOrderDetailVo.getSpec());
                List<Map> sizeList = maps.stream().filter(map -> map.get("key").equals("尺码")).collect(Collectors.toList());
                if(!CollectionUtils.isEmpty(sizeList)){
                    integralOrderDetailVo.setSizeSpec((String) sizeList.get(0).get("value"));
                }
            });
        }*/
        PageInfo<IntegralOrderDetailVo> orderDetailVoPageInfo = new PageInfo<>(integralOrderDetailVos);
        return orderDetailVoPageInfo;
    }

    @Override
    public IntegralOrderDetailVo getUserIntegralOrderDetail(LoginAuthDto loginAuthDto, String orderSn) {
        //获取当前登录人信息
        UmsMember umsMember = umsMemberService.selectByLoginAuthDto(loginAuthDto);
        IntegralOrderDetailVo integralOrderDetailVo = omsIntegralOrderMapper.selectUserIntegralOrderByOrderSn(umsMember.getId(), orderSn);
        if(integralOrderDetailVo == null){
            throw new OmsBizException(ErrorCodeEnum.OMS10031020);
        }
        integralOrderDetailVo.setProductPic(integralOrderDetailVo.getProductPic().split(",")[0]);
        integralOrderDetailVo.setPhone(umsMember.getPhone());
        return integralOrderDetailVo;
    }

    @Override
    public IntegralOrderDetailVo getSellerIntegralOrderDetail(LoginAuthDto loginAuthDto, String orderSn) {
        UmsCoach umsCoach = umsCoachService.selectByLoginAuthDto(loginAuthDto);
        IntegralOrderDetailVo integralOrderDetailVo = omsIntegralOrderMapper.selectSellerIntegralOrderByOrderSn(umsCoach.getId(), orderSn);
        if(integralOrderDetailVo == null){
            throw new OmsBizException(ErrorCodeEnum.OMS10031020);
        }
        integralOrderDetailVo.setProductPic(integralOrderDetailVo.getProductPic().split(",")[0]);
        return integralOrderDetailVo;
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
     * 封装积分订单信息并保存
     */
    private OmsIntegralOrder assembleIntegralOrder(String userType, Long shopId, OrderCreateVo orderCreateVo, BigDecimal postage, LoginAuthDto loginAuthDto, String remark, Long skuId, DmdIntegralGift integralGift, Integer quantity) {
        OmsIntegralOrder order = new OmsIntegralOrder();
        order.setStatus(OmsApiConstant.OrderStatusEnum.PAID.getCode());
        order.setDeleteStatus(0);
        order.setUserType(userType);
        order.setFreightAmount(postage);
        order.setUseIntegration(orderCreateVo.getUseIntegration());
        order.setSourceType(1);
        order.setMemberId(loginAuthDto.getUserId());
        order.setShopId(shopId);
        order.setMemberUsername(loginAuthDto.getUserName());
        order.setConfirmStatus(0);
        order.setTotalAmount(new BigDecimal(orderCreateVo.getUseIntegration()));
        order.setPayAmount(new BigDecimal(orderCreateVo.getUseIntegration()));
        order.setStatus(OmsApiConstant.OrderStatusEnum.PAID.getCode());
        order.setPayType(3);
        order.setPaymentTime(new Date());
        order.setOrderType(1);
        //封装积分商品收货地址信息
        addOrderShippingMessage(order, orderCreateVo.getShippingId());
        order.setRemark(remark);
        order.setOrderSn(idWorker.nextId() + "");

        //查询积分商品规格数据
        DmdIntegralGiftSpe dmdIntegralGiftSpe = integralGiftSpeService.selectByKey(skuId);
        if(dmdIntegralGiftSpe == null){
            throw new PmsBizException(ErrorCodeEnum.PMS10021031, integralGift.getId());
        }
        if(integralGift.getStatus() == 1){
            logger.error("商品已下架, integralGift={}", integralGift.getId());
            throw new OmsBizException(ErrorCodeEnum.PMS10021017, integralGift.getId());
        }
        //校验库存
        //查询商品的库存
        if (quantity > dmdIntegralGiftSpe.getSpecStock()) {
            logger.error("商品库存不足, productId={}", integralGift.getId());
            throw new OmsBizException(ErrorCodeEnum.PMS10021016, integralGift.getId());
        }
        //封装商品的信息
        order.setProductPrice(new BigDecimal(integralGift.getIntegral()));
        order.setProductId(integralGift.getId());
        order.setProductPic(integralGift.getPicture());
        order.setProductName(integralGift.getName());
        order.setProductTitle(integralGift.getName());
        order.setProductType(1);
        order.setProductQuantity(quantity);
        //封装商品sku数据
        order.setProductSkuId(dmdIntegralGiftSpe.getId());
        List<Map> list = integralGiftService.packageSpecMessage(dmdIntegralGiftSpe);
        order.setProductAttr(JSONArray.toJSONString(list));
        order.setUpdateInfo(loginAuthDto);
        int rowCount = omsIntegralOrderMapper.insertSelective(order);
        if (rowCount > 0) {
            return order;
        }
        return null;
    }

    /**
     * 封装订单收货地址信息
     * @param order
     * @param shippingId
     */
    private void addOrderShippingMessage(OmsIntegralOrder order, Long shippingId) {
        OmsShipping omsShipping = shippingService.selectByKey(shippingId);
        order.setReceiverName(omsShipping.getReceiverName());
        order.setReceiverMobileNo(omsShipping.getReceiverMobileNo());
        order.setProvinceName(omsShipping.getProvinceName());
        order.setCityName(omsShipping.getCityName());
        order.setDistrictName(omsShipping.getDistrictName());
        order.setDetailAddress(omsShipping.getDetailAddress());
        order.setReceiverZipCode(omsShipping.getReceiverZipCode());
    }
}
