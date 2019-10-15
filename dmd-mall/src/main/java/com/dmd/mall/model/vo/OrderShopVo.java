package com.dmd.mall.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/10/15 14:20
 * @Description 商铺订单vo类
 */
@Data
public class OrderShopVo implements Serializable {
    private static final long serialVersionUID = 185060434133379411L;
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
    private List<OrderShopVo.OrderDetailGroupByShop> orderDetailGroupByShop;

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
