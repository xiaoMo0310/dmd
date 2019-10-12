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
 * 购物车表
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "oms_cart")
@Alias(value = "OmsCart")
@ApiModel
public class OmsCart extends BaseEntity {

private static final long serialVersionUID = 1L;


    @Column(name = "product_id")
    @ApiModelProperty("商品id")
    private Long productId;

    @Column(name = "product_sku_id")
    @ApiModelProperty("商品skuid")
    private Long productSkuId;

    @Column(name = "member_id")
    @ApiModelProperty("用户id")
    private Long memberId;

    @Column(name = "shop_id")
    @ApiModelProperty("商铺id")
    private Long shopId;

    @ApiModelProperty("购买数量")
    private Integer quantity;

    @ApiModelProperty("添加到购物车的价格")
    private BigDecimal price;

    @Column(name = "product_pic")
    @ApiModelProperty("商品主图")
    private String productPic;

    @Column(name = "product_name")
    @ApiModelProperty("商品名称")
    private String productName;

    @Column(name = "product_sub_title")
    @ApiModelProperty("商品副标题（卖点）")
    private String productSubTitle;

    @Column(name = "product_sku_code")
    @ApiModelProperty("商品sku编码")
    private String productSkuCode;

    @Column(name = "member_nickname")
    @ApiModelProperty("会员昵称")
    private String memberNickname;

    @Column(name = "delete_status")
    @ApiModelProperty("是否删除")
    private Integer deleteStatus;

    @Column(name = "product_category_id")
    @ApiModelProperty("商品分类")
    private Long productCategoryId;

    @Column(name = "product_brand")
    @ApiModelProperty("商品品牌")
    private String productBrand;

    @Column(name = "product_sn")
    @ApiModelProperty("商品编号")
    private String productSn;

    @Column(name = "product_attr")
    @ApiModelProperty("商品销售属性:[{\"key\":\"颜色\",\"value\":\"颜色\"},{\"key\":\"容量\",\"value\":\"4G\"}]")
    private String productAttr;


}
