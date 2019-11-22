package com.dmd.mall.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/11/22 16:24
 * @Description 订单申请退款 dto
 */
@Data
@ApiModel
public class OrderReturnApplyDto {

    @ApiModelProperty("订单编号")
    private String orderSn;

    @ApiModelProperty("原因")
    private String reason;

    @ApiModelProperty("说明")
    private String description;

    @ApiModelProperty("图片")
    private String proofPics;
}
