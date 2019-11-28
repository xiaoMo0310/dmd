package com.dmd.mall.service.impl;

import com.dmd.BeanUtils;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.base.enums.ErrorCodeEnum;
import com.dmd.core.support.BaseService;
import com.dmd.mall.constant.OmsApiConstant;
import com.dmd.mall.exceptions.OmsBizException;
import com.dmd.mall.mapper.OmsOrderReturnApplyMapper;
import com.dmd.mall.model.domain.OmsOrderReturnApply;
import com.dmd.mall.model.dto.OrderReturnApplyDto;
import com.dmd.mall.model.vo.CourseOrderDetailVo;
import com.dmd.mall.model.vo.OrderReturnApplyVo;
import com.dmd.mall.service.OmsOrderReturnApplyService;
import com.dmd.mall.service.OmsOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * <p>
 * 订单退货申请 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-22
 */
@Service
public class OmsOrderReturnApplyServiceImpl extends BaseService<OmsOrderReturnApply> implements OmsOrderReturnApplyService {

    @Autowired
    private OmsOrderReturnApplyMapper omsOrderReturnApplyMapper;
    @Autowired
    private OmsOrderService omsOrderService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertOrderReturnMessage(LoginAuthDto loginAuthDto, OrderReturnApplyDto returnApplyDto) {
        OmsOrderReturnApply omsOrderReturnApply = omsOrderReturnApplyMapper.selectByOrderSn(returnApplyDto.getOrderSn());
        if(omsOrderReturnApply != null){
            throw new OmsBizException(ErrorCodeEnum.OMS10031025);
        }
        //查询订单详细信息
        CourseOrderDetailVo userOrderDetail = omsOrderService.getUserOrderDetail(loginAuthDto, returnApplyDto.getOrderSn());
        //判断订单的状态
        if (userOrderDetail.getStatus() == OmsApiConstant.OrderStatusEnum.AFTER_SALE.getCode()) {
            throw new OmsBizException(ErrorCodeEnum.OMS10031025);
        }
        if (userOrderDetail.getStatus() != OmsApiConstant.OrderStatusEnum.PAID.getCode()) {
            throw new OmsBizException(ErrorCodeEnum.OMS10031024);
        }
        //判断商品类型
        if(userOrderDetail.getProductType() == 4){
            throw new OmsBizException(ErrorCodeEnum.OMS10031027);
        }
        omsOrderReturnApply = new OmsOrderReturnApply();
        if(returnApplyDto != null && userOrderDetail != null){
            BeanUtils.copyProperties(returnApplyDto, omsOrderReturnApply);
            BeanUtils.copyProperties(userOrderDetail, omsOrderReturnApply);
        }
        omsOrderReturnApply.setMemberUsername(userOrderDetail.getUserName());
        omsOrderReturnApply.setReturnAmount(userOrderDetail.getPayAmount());
        omsOrderReturnApply.setReturnName(userOrderDetail.getUserName());
        omsOrderReturnApply.setReturnPhone(userOrderDetail.getPhone());
        omsOrderReturnApply.setStatus(0);
        omsOrderReturnApply.setProductCount(userOrderDetail.getProductQuantity());
        omsOrderReturnApply.setProductAttr(userOrderDetail.getSpec());
        omsOrderReturnApply.setProductRealPrice(userOrderDetail.getRealAmount());
        omsOrderReturnApply.setUpdateInfo(loginAuthDto);
        int data = omsOrderReturnApplyMapper.insertSelective(omsOrderReturnApply);
        //修改订单状态为售后
        int result = omsOrderService.updateOrderStatus(loginAuthDto, userOrderDetail.getOrderSn(), OmsApiConstant.OrderStatusEnum.AFTER_SALE.getCode());
        if(result <= 0){
            throw new OmsBizException(ErrorCodeEnum.OMS10031026);
        }
        return data;

    }

    @Override
    public OrderReturnApplyVo findOrderReturnApplyMessage(LoginAuthDto loginAuthDto, String orderSn) {
        CourseOrderDetailVo userOrderDetail = omsOrderService.getUserOrderDetail(loginAuthDto, orderSn);
        OrderReturnApplyVo orderReturnApplyVo = new OrderReturnApplyVo();
        if(userOrderDetail != null){
            BeanUtils.copyProperties(userOrderDetail, orderReturnApplyVo);
        }
        OmsOrderReturnApply omsOrderReturnApply = omsOrderReturnApplyMapper.selectByOrderSn(orderSn);
        OrderReturnApplyDto orderReturnApplyDto = new OrderReturnApplyDto();
        if(omsOrderReturnApply != null){
            BeanUtils.copyProperties(omsOrderReturnApply, orderReturnApplyDto);
            if(omsOrderReturnApply.getProofPics() != null){
                orderReturnApplyDto.setPicList(Arrays.asList(omsOrderReturnApply.getProofPics().split(",")));
            }
        }
        orderReturnApplyVo.setReturnApplyDto(orderReturnApplyDto);
        return orderReturnApplyVo;
    }
}
