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
 * 教练积分变化历史记录表
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-12-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "ums_coach_integration_log")
@Alias(value = "UmsCoachIntegrationLog")
@ApiModel
public class UmsCoachIntegrationLog extends BaseEntity {

private static final long serialVersionUID = 1L;


    @Column(name = "coach_id")
    @ApiModelProperty("教练id")
    private Long coachId;

    @Column(name = "change_type")
    @ApiModelProperty("改变类型：0->收入；1->支出")
    private Integer changeType;

    @Column(name = "change_count")
    @ApiModelProperty("积分改变数量")
    private Integer changeCount;

    @Column(name = "operate_man")
    @ApiModelProperty("操作人员")
    private String operateMan;

    @Column(name = "operate_note")
    @ApiModelProperty("操作备注")
    private String operateNote;

    @Column(name = "source_type")
    @ApiModelProperty("积分来源：0->购物；1->动态或评论 2->管理员修改")
    private Integer sourceType;

    @Column(name = "integral_trend")
    @ApiModelProperty("积分动向说明")
    private String integralTrend;

    @ApiModelProperty("用户总积分")
    private Integer integration;

}
