package com.dmd.mall.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 *@Author YangAnsheng
 *@Date  2020/03/18 11:30
 *@Describle 教练店铺vo类
 */
@Data
public class UmsCoachShopVo implements Serializable{
    private static final long serialVersionUID = -4153535361804625062L;

    private Long id;

    @ApiModelProperty("教练id")
    private Long coachId;

    @ApiModelProperty("店铺名称")
    private String name;

    @ApiModelProperty("店铺logo")
    private String logo;

    @ApiModelProperty("店铺电话")
    private String telephone;

    @ApiModelProperty("店铺级别")
    private String level;

    @ApiModelProperty("店铺介绍")
    private String introduction;

    @ApiModelProperty("店铺公告")
    private String announcement;

    @ApiModelProperty("营业执照")
    private String qualification;

    @ApiModelProperty("是否开启自动回复(1:开启 2:未开启)")
    private Integer isAutoReply;

    @ApiModelProperty("自动回复ID")
    private Long replyId;

    @ApiModelProperty("状态(1:正常 2:关闭)")
    private Integer status;

    /**
     * 粉丝数量
     */
    private Long fansNumber;
}
