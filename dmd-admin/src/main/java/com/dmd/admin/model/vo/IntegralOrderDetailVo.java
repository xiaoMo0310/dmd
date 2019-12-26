package com.dmd.admin.model.vo;

import com.dmd.admin.model.domain.OmsIntegralOrder;
import com.dmd.admin.model.domain.OmsOrderOperateHistory;
import lombok.Data;

import java.util.List;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/11/21 17:11
 * @Description 积分订单详情
 */
@Data
public class IntegralOrderDetailVo extends OmsIntegralOrder {


    private List<OmsOrderOperateHistory> historyList;

}
