package com.dmd.mall.model.domain;

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
import javax.persistence.Transient;
import java.util.Date;

/**
 * <p>
 * 积分变化历史记录表
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "ums_integration_change_log")
@Alias(value = "UmsIntegrationChangeLog")
@ApiModel
public class UmsIntegrationChangeLog extends BaseEntity {

private static final long serialVersionUID = 1L;


    @Column(name = "member_id")
    @ApiModelProperty("用户id")
    private Long memberId;

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

    @ApiModelProperty("更新前的积分数量")
    private Integer integration;
    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Transient
    private Date startTime;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Transient
    private Date endTime;

}
