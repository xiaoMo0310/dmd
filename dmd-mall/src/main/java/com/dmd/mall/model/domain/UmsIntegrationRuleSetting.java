package com.dmd.mall.model.domain;

import com.dmd.core.mybatis.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * <p>
 * 积分消费设置
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "ums_integration_rule_setting")
@Alias(value = "UmsIntegrationRuleSetting")
@ApiModel
public class UmsIntegrationRuleSetting extends BaseEntity {

private static final long serialVersionUID = 1L;


    @Column(name = "deduction_per_amount")
    @ApiModelProperty("每一元需要抵扣的积分数量")
    private Integer deductionPerAmount;

    @Column(name = "max_percent_per_order")
    @ApiModelProperty("每笔订单最高抵用百分比")
    private Integer maxPercentPerOrder;

    @Column(name = "use_unit")
    @ApiModelProperty("每次使用积分最小单位100")
    private Integer useUnit;

    @Column(name = "coupon_status")
    @ApiModelProperty("是否可以和优惠券同用；0->不可以；1->可以")
    private Integer couponStatus;


}
