package com.dmd.mall.service;

import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.IService;
import com.dmd.mall.model.domain.OmsOrderReturnApply;
import com.dmd.mall.model.dto.OrderReturnApplyDto;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单退货申请 服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-22
 */
public interface OmsOrderReturnApplyService extends IService<OmsOrderReturnApply> {

    /**
     * 退款申请
     * @param loginAuthDto
     * @param returnApplyDto
     */
     int insertOrderReturnMessage(LoginAuthDto loginAuthDto, OrderReturnApplyDto returnApplyDto);

    /**
     * 查询退款详情
     * @param orderSn
     * @return
     */
    OrderReturnApplyDto findOrderReturnApplyMessage(LoginAuthDto loginAuthDto, String orderSn);

    /**
     * 根据订单号查询退款详情
     * @param orderSn
     * @return
     */
    OmsOrderReturnApply findReturnApplyMessageByOrderSn(String orderSn);

    /**
     * 统计退款订单数量
     * @param userId
     * @param userType
     * @param status
     * @return
     */
    Map countReturnOrderNum(Long userId, String userType, Integer status);

    /**
     * 根据状态查询售后订单信息
     * @param i
     * @return
     */
    List<OmsOrderReturnApply> selectReturnOrderByStatus(Integer status);

    /**
     * 修改状态
     * @param loginAuthDto
     * @param i
     * @return
     */
    int updateOmsReturnStatus(LoginAuthDto loginAuthDto, Long returnOrderId, Integer status);
}
