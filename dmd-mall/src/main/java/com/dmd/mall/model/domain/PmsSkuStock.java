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
 * sku的库存
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "pms_sku_stock")
@Alias(value = "PmsSkuStock")
@ApiModel
public class PmsSkuStock extends BaseEntity {

private static final long serialVersionUID = 1L;


    @Column(name = "product_id")
    @ApiModelProperty("商品id")
    private Long productId;

    @Column(name = "sku_code")
    @ApiModelProperty("sku编码")
    private String skuCode;

    @ApiModelProperty("价格")
    private BigDecimal price;

    @ApiModelProperty("库存")
    private Integer stock;

    @Column(name = "low_stock")
    @ApiModelProperty("预警库存")
    private Integer lowStock;

    @ApiModelProperty("规格数据")
    private String spec;

    @ApiModelProperty("展示图片")
    private String pic;

    @ApiModelProperty("销量")
    private Integer sale;

    @Column(name = "promotion_price")
    @ApiModelProperty("单品促销价格")
    private BigDecimal promotionPrice;

    @Column(name = "lock_stock")
    @ApiModelProperty("锁定库存")
    private Integer lockStock;

    @ApiModelProperty("商品状态.1-在售 2-下架 3-删除")
    private Integer status;

    @ApiModelProperty("sku标题")
    private String title;

    @Column(name = "is_default")
    @ApiModelProperty("是否是默认(0:非默认 1:默认)")
    private String isDefault;


}
