package com.dmd.mall.service;

import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.IService;
import com.dmd.mall.model.domain.OmsOrderReturnApply;
import com.dmd.mall.model.dto.OrderReturnApplyDto;

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
}
