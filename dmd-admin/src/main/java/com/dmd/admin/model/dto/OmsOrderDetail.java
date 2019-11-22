package com.dmd.admin.model.dto;

import com.dmd.admin.model.domain.OmsOrder;
import com.dmd.admin.model.domain.OmsOrderItem;
import com.dmd.admin.model.domain.OmsOrderOperateHistory;
import com.dmd.admin.model.domain.OmsShipping;
import lombok.Data;

import java.util.List;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/11/21 17:11
 * @Description 订单详情
 */
@Data
public class OmsOrderDetail extends OmsOrder {

    private List<OmsOrderItem> orderItemList;

    private List<OmsOrderOperateHistory> historyList;

    private OmsShipping omsShipping;
}
