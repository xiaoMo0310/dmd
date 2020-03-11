package com.dmd.mall.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

@Data
public class SmsActivityApplyDto implements Serializable{
    private static final long serialVersionUID = -1909622837649821988L;

    @ApiModelProperty("活动id")
    private Long activityId;

    @ApiModelProperty("参加用户名")
    private String userName;

    @ApiModelProperty("用户电话")
    private Integer mobile;
}
