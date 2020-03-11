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
 * 活动报名表
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "sms_activity_apply")
@Alias(value = "SmsActivityApply")
@ApiModel
public class SmsActivityApply extends BaseEntity {

private static final long serialVersionUID = 1L;


    @Column(name = "user_id")
    @ApiModelProperty("用户id")
    private Long userId;

    @Column(name = "user_type")
    @ApiModelProperty("用户类型(member:普通用户 coach:教练)")
    private String userType;

    @Column(name = "activity_id")
    @ApiModelProperty("活动id")
    private Long activityId;

    @Column(name = "user_name")
    @ApiModelProperty("参加用户名")
    private String userName;

    @ApiModelProperty("用户电话")
    private Integer mobile;

    @ApiModelProperty("状态(1:正常 2:取消)")
    private Integer status;


}
