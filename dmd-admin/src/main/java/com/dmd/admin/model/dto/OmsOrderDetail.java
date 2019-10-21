package com.dmd.admin.model.dto;

import com.dmd.admin.model.domain.OmsOrder;
import com.dmd.admin.model.domain.OmsOrderItem;
import com.dmd.admin.model.domain.OmsOrderOperateHistory;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 订单详情信息
 * Created by macro on 2018/10/11.
 */
public class OmsOrderDetail extends OmsOrder {
    @Getter
    @Setter
    private List<OmsOrderItem> orderItemList;
    @Getter
    @Setter
    private List<OmsOrderOperateHistory> historyList;
}
