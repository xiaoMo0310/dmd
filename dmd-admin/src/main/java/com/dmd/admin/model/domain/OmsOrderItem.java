package com.dmd.admin.model.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/10/31 14:01
 * @Description  订单详情实体类
 */
@Data
public class OmsOrderItem implements Serializable {

    private Long id;

    @ApiModelProperty(value = "订单id")
    private Long orderId;

    @ApiModelProperty(value = "订单编号")
    private String orderSn;

    private Long productId;

    private String productPic;

    private String productName;

    @ApiModelProperty(value = "销售价格")
    private BigDecimal productPrice;

    @ApiModelProperty(value = "其它装备价格")
    private BigDecimal equipmentPrice;

    @ApiModelProperty(value = "购买数量")
    private Integer productQuantity;

    @ApiModelProperty(value = "商品sku编号")
    private Long productSkuId;

    @ApiModelProperty(value = "商品分类价格商品分类价格(格式[{\"parentId\":22,id\":33,\"text\":\"眼镜\",\"price\":\"200\"}])")
    private String productCategoryPrice;

    @ApiModelProperty(value = "商品促销名称")
    private String promotionName;

    @ApiModelProperty(value = "商品促销分解金额")
    private BigDecimal promotionAmount;

    @ApiModelProperty(value = "优惠券优惠分解金额")
    private BigDecimal couponAmount;

    @ApiModelProperty(value = "积分优惠分解金额")
    private BigDecimal integrationAmount;

    @ApiModelProperty(value = "该商品经过优惠后的分解金额")
    private BigDecimal realAmount;
}