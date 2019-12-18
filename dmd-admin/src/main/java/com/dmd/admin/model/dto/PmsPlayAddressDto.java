package com.dmd.admin.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/12/6 15:56
 * @Description 潜水地址 dto
 */
@Data
public class PmsPlayAddressDto implements Serializable {

    private static final long serialVersionUID = -6122503998590743045L;
    private Long id;

    @ApiModelProperty("地址名称")
    private String address;

    @ApiModelProperty("地址简介")
    private String addressIntroduction;

    @ApiModelProperty("图集")
    private String atlas;

    @ApiModelProperty("是否默认(0:非默认 1:默认)")
    private Integer isDefault;

    @ApiModelProperty("状态(1:启用 2:禁用)")
    private Integer status;

}
