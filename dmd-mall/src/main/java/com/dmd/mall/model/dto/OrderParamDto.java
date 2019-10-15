package com.dmd.mall.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/10/14 9:57
 * @Description 创建订单需要接收的参数
 */
@Data
@ApiModel
public class OrderParamDto {

    @ApiModelProperty("收货地址id")
    private Long shippingId;

    @ApiModelProperty("是否使用积分")
    private Boolean isUserIntegration;

    @ApiModelProperty("是否开具发票")
    private Boolean isInvoice;

    @ApiModelProperty("订单备注")
    private String remark;

    @ApiModelProperty("下单使用的积分数")
    private Integer useIntegration;

    @ApiModelProperty("购物车ids")
    private List<Long> cartIds;
}
