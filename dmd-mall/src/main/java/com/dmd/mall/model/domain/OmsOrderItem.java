package com.dmd.mall.model.domain;

import com.dmd.core.mybatis.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * <p>
 * 订单详情表
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "oms_order_item")
@Alias(value = "OmsOrderItem")
@ApiModel
public class OmsOrderItem extends BaseEntity {

private static final long serialVersionUID = 1L;


    @Column(name = "order_id")
    @ApiModelProperty("订单id")
    private Long orderId;

    @Column(name = "order_sn")
    @ApiModelProperty("订单编号")
    private String orderSn;

    @Column(name = "product_id")
    @ApiModelProperty("商品id")
    private Long productId;

    @Column(name = "product_pic")
    @ApiModelProperty("商品图片")
    private String productPic;

    @Column(name = "product_name")
    @ApiModelProperty("商品名称")
    private String productName;

    @Column(name = "product_brand")
    @ApiModelProperty("商品品牌")
    private String productBrand;

    @Column(name = "product_sn")
    @ApiModelProperty("商品编码")
    private String productSn;

    @Column(name = "product_type")
    @ApiModelProperty("商品类型(1:普通商品 2:潜水商品 3:学证商品)")
    private Integer productType;

    @Column(name = "product_price")
    @ApiModelProperty("销售价格")
    private BigDecimal productPrice;

    @Column(name = "product_quantity")
    @ApiModelProperty("购买数量")
    private Integer productQuantity;

    @Column(name = "total_price")
    @ApiModelProperty("总的价格")
    private BigDecimal totalPrice;

    @Column(name = "product_sku_id")
    @ApiModelProperty("商品sku编号")
    private Long productSkuId;

    @Column(name = "product_sku_code")
    @ApiModelProperty("商品sku条码")
    private String productSkuCode;

    @Column(name = "product_category_id")
    @ApiModelProperty("商品分类id")
    private Long productCategoryId;

    @Column(name = "integration_amount")
    @ApiModelProperty("积分优惠分解金额")
    private BigDecimal integrationAmount;

    @Column(name = "real_amount")
    @ApiModelProperty("该商品经过优惠后的分解金额")
    private BigDecimal realAmount;

    @Column(name = "product_attr")
    @ApiModelProperty("商品销售属性:[{\"key\":\"颜色\",\"value\":\"颜色\"},{\"key\":\"容量\",\"value\":\"4G\"}]")
    private String productAttr;


}
