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

    @Column(name = "product_type")
    @ApiModelProperty("商品类型(1:普通商品 2:潜水商品 3:课程商品 4:积分商品)")
    private Integer productType;

    @Column(name = "product_price")
    @ApiModelProperty("销售价格")
    private BigDecimal productPrice;

    @Column(name = "equipment_price")
    @ApiModelProperty(value = "其它装备价格")
    private BigDecimal equipmentPrice;

    @Column(name = "product_quantity")
    @ApiModelProperty("购买数量")
    private Integer productQuantity;

    @Column(name = "total_price")
    @ApiModelProperty("总的价格")
    private BigDecimal totalPrice;

    @Column(name = "product_category_price")
    @ApiModelProperty("商品分类价格(格式[{\"parentId\":22,id\":33,\"text\":\"眼镜\",\"price\":\"200\"}])")
    private String productCategoryPrice;

    @Column(name = "integration_amount")
    @ApiModelProperty("积分优惠分解金额")
    private BigDecimal integrationAmount;

    @Column(name = "real_amount")
    @ApiModelProperty("该商品经过优惠后的分解金额")
    private BigDecimal realAmount;

    @Column(name = "product_title")
    @ApiModelProperty("商品标题")
    private String productTitle;


}
