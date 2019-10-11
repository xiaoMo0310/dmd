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
 * 商品信息
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "pms_product")
@Alias(value = "PmsProduct")
@ApiModel
public class PmsProduct extends BaseEntity {

private static final long serialVersionUID = 1L;


    @Column(name = "product_sn")
    @ApiModelProperty("商品编号")
    private String productSn;

    @Column(name = "brand_id")
    @ApiModelProperty("品牌id")
    private Long brandId;

    @Column(name = "brand_name")
    @ApiModelProperty("品牌名称")
    private String brandName;

    @Column(name = "shop_id")
    @ApiModelProperty("店铺id")
    private Integer shopId;

    @Column(name = "product_category_id")
    @ApiModelProperty("商品分类id")
    private Long productCategoryId;

    @Column(name = "product_category_name")
    @ApiModelProperty("商品分类名称")
    private String productCategoryName;

    @ApiModelProperty("商品名称")
    private String name;

    @ApiModelProperty("商品图片(多个使用,隔开)")
    private String pic;

    @Column(name = "delete_status")
    @ApiModelProperty("删除状态：0->未删除；1->已删除")
    private Integer deleteStatus;

    @Column(name = "publish_status")
    @ApiModelProperty("上架状态：0->下架；1->上架")
    private Integer publishStatus;

    @Column(name = "new_status")
    @ApiModelProperty("新品状态:0->不是新品；1->新品")
    private Integer newStatus;

    @Column(name = "recommand_status")
    @ApiModelProperty("推荐状态；0->不推荐；1->推荐")
    private Integer recommandStatus;

    @Column(name = "verify_status")
    @ApiModelProperty("审核状态：0->未审核；1->审核通过")
    private Integer verifyStatus;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("销量")
    private Integer sale;

    @ApiModelProperty("价格")
    private BigDecimal price;

    @Column(name = "gift_growth")
    @ApiModelProperty("赠送的成长值")
    private Integer giftGrowth;

    @Column(name = "gift_point")
    @ApiModelProperty("赠送的积分")
    private Integer giftPoint;

    @Column(name = "use_point_limit")
    @ApiModelProperty("限制使用的积分数")
    private Integer usePointLimit;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("商品描述")
    private String description;

    @ApiModelProperty("库存")
    private Integer stock;

    @Column(name = "low_stock")
    @ApiModelProperty("库存预警值")
    private Integer lowStock;

    @ApiModelProperty("单位")
    private String unit;

    @ApiModelProperty("商品重量，默认为克")
    private BigDecimal weight;

    @Column(name = "service_ids")
    @ApiModelProperty("以逗号分割的产品服务：1->无忧退货；2->快速退款；3->免费包邮")
    private String serviceIds;

    @Column(name = "detail_html")
    @ApiModelProperty("产品详情网页内容")
    private String detailHtml;

    @Column(name = "ship_address")
    @ApiModelProperty("发货(出发)地址(商铺地址或手动输入)")
    private String shipAddress;


}
