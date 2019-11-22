package com.dmd.mall.service;

import com.dmd.core.support.IService;
import com.dmd.mall.model.domain.OmsOrderItem;

import java.util.List;

/**
 * <p>
 * 订单详情表 服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-14
 */
public interface OmsOrderItemService extends IService<OmsOrderItem> {

    /**
     * 批量的插入订单详情数据
     * @param omcOrderDetailList
     */
    void batchInsertOrderDetail(List<OmsOrderItem> omcOrderDetailList);

    /**
     * 根据订单编号查询订单详情的信息
     * @param orderSn
     * @return
     */
    List<OmsOrderItem> getListByOrderNoUserId(String orderSn);

}
