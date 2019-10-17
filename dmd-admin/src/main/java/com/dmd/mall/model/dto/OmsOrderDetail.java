package com.dmd.mall.model.dto;

import com.dmd.mall.model.domain.OmsOrder;
import com.dmd.mall.model.domain.OmsOrderItem;
import com.dmd.mall.model.domain.OmsOrderOperateHistory;
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
