/*
 *
 *
 * 类名称：OrderVo.java
 *
 *
 *
 *
 *
 */

package com.dmd.mall.model.vo;

import com.dmd.mall.model.dto.OrderParamDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/10/14 13:56
 * @Description 创建订单vo类
 */

@Data
@ApiModel
public class OrderCreateVo extends OrderParamDto implements Serializable {

    private static final long serialVersionUID = 592292780030349525L;

    private Long userId;
    @ApiModelProperty("商品总金额")
    private BigDecimal totalChoose;
    @ApiModelProperty("运费总金额")
    private BigDecimal totalPostage;
    @ApiModelProperty("付款总金额")
    private BigDecimal totalPayment;
    @ApiModelProperty("店铺订单集合")
    private List<OrderGroupByShop> orderGroupByShopList;

    @Data
    @NoArgsConstructor
    public static class OrderGroupByShop {
        @ApiModelProperty("卖家ID")
        private Long sellerId;
        @ApiModelProperty("店铺ID")
        private Long shopId;
        @ApiModelProperty("店铺名称")
        private String shopName;
        @ApiModelProperty("店铺logo")
        private String logo;
        @ApiModelProperty("店铺合计")
        private BigDecimal shopPayment;
        @ApiModelProperty("运费")
        private BigDecimal postage;
        @ApiModelProperty("买家留言")
        private String remark;
        @ApiModelProperty("订单的明细")
        private List<OrderDetailGroupByShop> orderDetailGroupByShop;
    }

    @Data
    @NoArgsConstructor
    public static class OrderDetailGroupByShop {
        @ApiModelProperty("购物车id")
        private Long cartId;
        @ApiModelProperty("店铺库存")
        private Long shopSkuId;
        @ApiModelProperty("商品属性")
        private String productAttribute;
        @ApiModelProperty("商品ID")
        private Long productId;
        @ApiModelProperty("商品名称")
        private String productName;
        @ApiModelProperty("商品图片地址")
        private String productImage;
        @ApiModelProperty("商品单价")
        private BigDecimal currentUnitPrice;
        @ApiModelProperty("商品数量")
        private Integer quantity;
        @ApiModelProperty("商品总价")
        private BigDecimal totalPrice;
    }
}
