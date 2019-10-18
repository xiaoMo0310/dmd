package com.dmd.mall.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/10/14 9:57
 * @Description 创建订单需要接收的参数
 */
@Data
@ApiModel
public class OrderParamDto {

    @ApiModelProperty("商品id")
    private Long productId;

    @ApiModelProperty("商品数量")
    private Integer quantity;

    @ApiModelProperty("收货人信息ID")
    private Long shippingId;

    @ApiModelProperty("收货人发票信息ID")
    private Long invoiceId;

    @ApiModelProperty("是否使用积分")
    private Boolean isUserIntegration = false;

    @ApiModelProperty("下单使用的积分数")
    private Integer useIntegration = 0;

    @ApiModelProperty("是否开具发票")
    private Boolean isInvoice = false;

}
