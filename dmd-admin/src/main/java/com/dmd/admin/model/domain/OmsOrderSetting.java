package com.dmd.admin.model.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

@Data
public class OmsOrderSetting implements Serializable {
    private Long id;

    @Column(name = "return_order_overtime")
    @ApiModelProperty("售后订单审核超时关闭时间(分)")
    private Integer returnOrderOvertime;

    @ApiModelProperty(value = "正常订单超时时间(分)")
    private Integer normalOrderOvertime;

    @ApiModelProperty(value = "发货后自动确认收货时间（天）")
    private Integer confirmOvertime;

    @ApiModelProperty(value = "自动完成交易时间，不能申请售后（天）")
    private Integer finishOvertime;

    @ApiModelProperty(value = "订单完成后自动好评时间（天）")
    private Integer commentOvertime;

    private static final long serialVersionUID = 1L;

}