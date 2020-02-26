package com.dmd.mall.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/12/16 13:20
 * @Description 教练端店铺 dto
 */
@Data
@ApiModel
public class UmsCoachShopDto implements Serializable {
    private static final long serialVersionUID = 7375897083139469717L;
    @ApiModelProperty("店铺id")
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

    @ApiModelProperty("检验验证码")
    private String authCode;

}
