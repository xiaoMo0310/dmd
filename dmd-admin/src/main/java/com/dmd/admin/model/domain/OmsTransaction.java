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
 * 订单交易表 
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "oms_transaction")
@Alias(value = "OmsTransaction")
@ApiModel
public class OmsTransaction extends BaseEntity {

private static final long serialVersionUID = 1L;


    @Column(name = "order_sn")
    @ApiModelProperty("交易单号(订单支付编号或提现编号)")
    private String orderSn;

    @Column(name = "user_id")
    @ApiModelProperty("交易用户ID(收支用户id)")
    private Long userId;

    @Column(name = "user_name")
    @ApiModelProperty("收支用户姓名")
    private String userName;

    @ApiModelProperty("收支用户手机号")
    private String phone;

    @Column(name = "pay_amount")
    @ApiModelProperty("交易金额")
    private BigDecimal payAmount;

    @Column(name = "oid_paybill")
    @ApiModelProperty("微服通单号")
    private String oidPaybill;

    @Column(name = "payment_type")
    @ApiModelProperty("支出账户类型(1:微信 2:支付宝 3:平台账号支付)")
    private Integer paymentType;

    @Column(name = "income_type")
    @ApiModelProperty("收入账户类型(1:平台账户 2:银行卡) ")
    private Integer incomeType;

    @ApiModelProperty("交易来源(wx app web wap)")
    private String source;

    @ApiModelProperty("操作状态 0:未处理 1:操作成功 2:操作异常 3:取消")
    private Integer status;

    @Column(name = "order_type")
    @ApiModelProperty("订单类型(1:潜水学证订单 2:分账订单)")
    private Integer orderType;

    @ApiModelProperty("类型(1:支出 2:收入)")
    private Integer type;

    @Column(name = "completion_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("完成时间")
    private Date completionTime;

    @ApiModelProperty("备注")
    private String remark;


}
