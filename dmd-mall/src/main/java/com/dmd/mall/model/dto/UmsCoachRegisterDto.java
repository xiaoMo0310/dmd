package com.dmd.mall.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/12/16 9:22
 * @Description 用户注册 dto
 */
@Data
@ApiModel
public class UmsCoachRegisterDto {

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("验证码")
    private String authCode;
}
