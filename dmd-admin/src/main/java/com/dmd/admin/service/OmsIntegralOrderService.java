package com.dmd.admin.service;

import com.dmd.admin.model.domain.OmsIntegralOrder;
import com.dmd.admin.model.dto.OmsOrderDeliveryParam;
import com.dmd.admin.model.dto.OmsOrderQueryParam;
import com.dmd.admin.model.dto.OmsReceiverInfoParam;
import com.dmd.admin.model.vo.IntegralOrderDetailVo;
import com.dmd.core.support.IService;

import java.util.List;

/**
 * <p>
 * 积分好礼订单表 服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-12-23
 */
public interface OmsIntegralOrderService extends IService<OmsIntegralOrder> {

    /**
     * 订单查询
     */
    List<OmsIntegralOrder> list(OmsOrderQueryParam queryParam, Integer pageSize, Integer pageNum);

    /**
     * 批量发货
     */
    int delivery(List<OmsOrderDeliveryParam> deliveryParamList);

    /**
     * 批量关闭订单
     */
    int close(List<Long> ids, String note);

    /**
     * 批量删除订单
     */
    int delete(List<Long> ids);

    /**
     * 获取指定订单详情
     */
    IntegralOrderDetailVo detail(Long id);

    /**
     * 修改订单收货人信息
     */
    int updateReceiverInfo(OmsReceiverInfoParam receiverInfoParam);

    /**
     * 修改订单备注
     */
    int updateNote(Long id, String note, Integer status);
}
