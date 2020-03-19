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
 * 财务设置表
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "oms_finance_setting")
@Alias(value = "OmsFinanceSetting")
@ApiModel
public class OmsFinanceSetting extends BaseEntity {

private static final long serialVersionUID = 1L;


    @Column(name = "coach_commission_ratio")
    @ApiModelProperty("教练佣金比例")
    private Double coachCommissionRatio;

    @Column(name = "platform_rate")
    @ApiModelProperty("平台收取比例")
    private Double platformRate;


}
