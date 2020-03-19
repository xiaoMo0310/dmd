package com.dmd.mall.model.domain;

import com.dmd.core.mybatis.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * <p>
 * 教练返佣表
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "ums_coach_rebate")
@Alias(value = "UmsCoachRebate")
@ApiModel
public class UmsCoachRebate extends BaseEntity {

private static final long serialVersionUID = 1L;


    @Column(name = "user_id")
    @ApiModelProperty("用户id")
    private Long userId;

    @Column(name = "coach_id")
    @ApiModelProperty("教练id")
    private Long coachId;

    @Column(name = "order_sn")
    @ApiModelProperty("订单编号")
    private String orderSn;

    @Column(name = "pay_amount")
    @ApiModelProperty("用户支付金额")
    private BigDecimal payAmount;

    @Column(name = "rebate_amount")
    @ApiModelProperty("返佣金额")
    private BigDecimal rebateAmount;

    @Column(name = "rebate_ratio")
    @ApiModelProperty("返佣比例")
    private Double rebateRatio;


}
