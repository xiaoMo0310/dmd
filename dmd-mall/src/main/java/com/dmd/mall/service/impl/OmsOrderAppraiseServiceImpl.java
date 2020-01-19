package com.dmd.mall.service.impl;

import com.dmd.BeanUtils;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.base.enums.ErrorCodeEnum;
import com.dmd.core.support.BaseService;
import com.dmd.mall.constant.OmsApiConstant;
import com.dmd.mall.exceptions.OmsBizException;
import com.dmd.mall.mapper.OmsOrderAppraiseMapper;
import com.dmd.mall.model.domain.OmsOrder;
import com.dmd.mall.model.domain.OmsOrderAppraise;
import com.dmd.mall.model.dto.OrderAppraiseDto;
import com.dmd.mall.model.vo.ProductAppraiseVo;
import com.dmd.mall.service.OmsOrderAppraiseService;
import com.dmd.mall.service.OmsOrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-22
 */
@Service
public class OmsOrderAppraiseServiceImpl extends BaseService<OmsOrderAppraise> implements OmsOrderAppraiseService {

    @Autowired
    private OmsOrderAppraiseMapper omsOrderAppraiseMapper;
    @Autowired
    private OmsOrderService omsOrderService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertAppraiseMessage(LoginAuthDto loginAuthDto, OrderAppraiseDto orderAppraiseDto) {
        OmsOrder order = omsOrderService.selectByKey(orderAppraiseDto.getOrderId());
        if (order.getStatus() != OmsApiConstant.OrderStatusEnum.ORDER_SUCCESS.getCode()) {
            throw new OmsBizException(ErrorCodeEnum.OMS10031023);
        }
        OmsOrderAppraise omsOrderAppraise = new OmsOrderAppraise();
        BeanUtils.copyProperties(orderAppraiseDto, omsOrderAppraise);
        omsOrderAppraise.setUserId(loginAuthDto.getUserId());
        omsOrderAppraise.setUpdateInfo(loginAuthDto);
        //修改订单状态为关闭
        omsOrderService.updateOrderStatus(loginAuthDto, order.getOrderSn(), OmsApiConstant.OrderStatusEnum.ORDER_CLOSE.getCode());
        return omsOrderAppraiseMapper.insertSelective(omsOrderAppraise);
    }

    @Override
    public PageInfo findAppraiseMessage(String productId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProductAppraiseVo> productAppraiseVos = omsOrderAppraiseMapper.selectAppraiseMessageByProductId(productId);
        return new PageInfo(productAppraiseVos);
    }
}
