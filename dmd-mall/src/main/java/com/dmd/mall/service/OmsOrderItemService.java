package com.dmd.mall.service;

import com.dmd.mall.model.domain.OmsOrderItem;
import com.dmd.core.support.IService;

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

    void batchInsertOrderDetail(List<OmsOrderItem> omcOrderDetailList);
}
