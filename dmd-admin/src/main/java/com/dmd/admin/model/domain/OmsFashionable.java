package com.dmd.admin.model.domain;

import com.dmd.core.mybatis.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 分账表 
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "oms_fashionable")
@Alias(value = "OmsFashionable")
@ApiModel
public class OmsFashionable extends BaseEntity {

private static final long serialVersionUID = 1L;


    @Column(name = "collecting_no")
    @ApiModelProperty("分账单号")
    private String collectingNo;

    @Column(name = "user_id")
    @ApiModelProperty("用户id")
    private Long userId;

    @Column(name = "transfer_amount")
    @ApiModelProperty("分账金额")
    private BigDecimal transferAmount;

    @Column(name = "order_no")
    @ApiModelProperty("订单编号")
    private String orderNo;

    @Column(name = "order_money")
    @ApiModelProperty("订单金额")
    private BigDecimal orderMoney;

    @Column(name = "handling_fee")
    @ApiModelProperty("手续费")
    private BigDecimal handlingFee;

    @Column(name = "close_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("订单交易关闭时间")
    private Date closeTime;

    @Column(name = "order_type")
    @ApiModelProperty("订单类型(1:潜水学证订单)")
    private Integer orderType;

    @Column(name = "payment_success_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("结账成功日期")
    private Date paymentSuccessTime;

    @Column(name = "payment_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("需要结账日期")
    private Date paymentTime;

    @Column(name = "checkout_destination")
    @ApiModelProperty("结账目的地(1:钱包 2:银行卡)")
    private Integer checkoutDestination;

    @ApiModelProperty("状态(1:未分账  2:已分账  3:异常待复核 4:取消)")
    private Integer status;

    @Column(name = "oid_paybill")
    @ApiModelProperty("订单支付成功单号")
    private String oidPaybill;


}
