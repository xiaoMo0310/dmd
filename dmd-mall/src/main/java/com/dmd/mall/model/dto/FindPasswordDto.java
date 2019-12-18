package com.dmd.mall.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/11/28 14:19
 * @Description 找回密码 dto
 */
@Data
public class FindPasswordDto implements Serializable {

    private static final long serialVersionUID = -8746685755424092968L;
    @ApiModelProperty("手机号")
    private String telephone;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("确认密码")
    private String confirmPassword;

    @ApiModelProperty("验证码")
    private String authCode;

    @ApiModelProperty("旧密码")
    private String oldPassword;

    @ApiModelProperty("新密码")
    private String newPassword;
}
