package com.dmd.mall.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/12/16 13:20
 * @Description 教练信息 dto
 */
@Data
@ApiModel
public class UmsCoachDto implements Serializable {

    private static final long serialVersionUID = -7747139748798068002L;
    @Column(name = "coach_name")
    @ApiModelProperty("教练名称")
    private String coachName;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("头像")
    private String icon;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("性别：0->未知；1->男；2->女")
    private Integer gender;

    @ApiModelProperty("个人影集")
    private String personalAlbum;

    @ApiModelProperty("个人简介")
    private String personalProfile;

}
