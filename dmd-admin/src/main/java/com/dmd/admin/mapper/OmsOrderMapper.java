package com.dmd.admin.mapper;


import com.dmd.admin.model.domain.OmsOrder;
import com.dmd.admin.model.dto.OmsOrderDeliveryParam;
import com.dmd.admin.model.dto.OmsOrderDetail;
import com.dmd.admin.model.dto.OmsOrderQueryParam;
import com.dmd.admin.model.vo.SetTimeoutVo;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public interface OmsOrderMapper  extends MyMapper<OmsOrder> {
    /**
     * 条件查询订单
     */
    List<OmsOrder> getList(@Param("queryParam") OmsOrderQueryParam queryParam);

    /**
     * 批量发货
     */
    int delivery(@Param("list") List<OmsOrderDeliveryParam> deliveryParamList);

    /**
     * 获取订单详情
     */
    OmsOrderDetail getDetail(@Param("id") Long id);

    /**
     *根据订单编号查询订单信息
     * @param orderSn
     * @return
     */
    OmsOrder selectByOrderSn(String orderSn);

    Integer queryOrderNumtoDay();

    BigDecimal queryOrderMoneyToDay();

    BigDecimal queryOrderMoneyToYesterday();

    BigDecimal queryOrderMoneyToSeven();

    Integer querySubstitutePayment();

    Integer queryCompleted();

    Integer queryReceiptConfirmed();

    Integer queryShipped();

    Integer queryAfterSale();

    Integer queryConfirmReceipt();

    Integer queryOrderMonthNum();

    Integer queryOrderLastMonthNum();

    Integer queryOrderWeek();

    Integer queryOrderLastWeek();

    BigDecimal querySalesMonth();

    BigDecimal querySalesLastMonth();

    BigDecimal querySalesWeek();

    BigDecimal querySalesLastWeek();

    List<SetTimeoutVo> setTimeout(SetTimeoutVo setTimeoutVo);
}