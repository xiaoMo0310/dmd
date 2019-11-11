package com.dmd.admin.model.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/11/4 15:28
 * @Description 收支明细vo类
 */
@Data
public class BillingDetailsVo {

    /**
     * 订单编号
     */
    private String orderSn;

    /**
     * 收支人名称
     */
    private String userName;

    /**
     * 收支人手机号手机号
     */
    private String phone;

    /**
     * 操作金额
     */
    private BigDecimal payAmount;

    /**
     * 平台订单号
     */
    private String oidPaybill;

    /**
     * 操作类型(收入 支出)
     */
    private Integer type;

    /**
     * 支出账户类型
     */
    private String expenseAccountType;

    /**
     * 收入账户类型
     */
    private String incomeAccountType;

    /**
     * 支付结算时间
     */
    private Date completionTime;
}
