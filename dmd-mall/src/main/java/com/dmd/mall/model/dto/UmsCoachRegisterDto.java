package com.dmd.mall.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/12/16 9:22
 * @Description 用户注册 dto
 */
@Data
@ApiModel
public class UmsCoachRegisterDto implements Serializable {

    private static final long serialVersionUID = -4404465878632968988L;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("验证码")
    private String authCode;

    @ApiModelProperty("教练证书图片")
    private String certificatePic;


}
