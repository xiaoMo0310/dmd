package com.dmd.mall.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/10/14 9:57
 * @Description 创建订单需要接收的参数
 */
@Data
@ApiModel
public class OrderParamDto implements Serializable {

    private static final long serialVersionUID = 9042812283792773359L;
    @ApiModelProperty("商品id")
    private Long productId;

    @ApiModelProperty("商品sku数据id")
    private Long productSkuId;

    @ApiModelProperty("商品数量")
    private Integer quantity;

    @ApiModelProperty("收货人信息ID")
    private Long shippingId;

    @ApiModelProperty("单个商家买家留言")
    private String remark;

    @ApiModelProperty("是否开具发票0:不开具 1:开具")
    private Integer isInvoice = 0;

    @ApiModelProperty("收货人发票信息ID")
    private Long invoiceId;

    @ApiModelProperty("是否使用积分 0:不使用 1:使用")
    private Integer isUserIntegration = 0;

    @ApiModelProperty("下单使用的积分数")
    private Integer useIntegration = 0;


}
