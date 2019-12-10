package com.dmd.mall.model.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.persistence.*;

/**
 * <p>
 * 订单设置表
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-12-10
 */
@Data
@Table(name = "oms_order_setting")
@Alias(value = "OmsOrderSetting")
@ApiModel
public class OmsOrderSetting {

private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "return_order_overtime")
    @ApiModelProperty("售后订单审核超时关闭时间(分)")
    private Integer returnOrderOvertime;

    @Column(name = "normal_order_overtime")
    @ApiModelProperty("正常订单超时时间(分)")
    private Integer normalOrderOvertime;

    @Column(name = "confirm_overtime")
    @ApiModelProperty("发货后自动确认收货时间（天）")
    private Integer confirmOvertime;

    @Column(name = "finish_overtime")
    @ApiModelProperty("自动完成交易时间，不能申请售后（天）")
    private Integer finishOvertime;

    @Column(name = "comment_overtime")
    @ApiModelProperty("订单完成后自动好评时间（天）")
    private Integer commentOvertime;

}
