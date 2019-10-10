package com.dmd.mall.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/10/9 17:17
 * @Description 
 */
@Data
@ApiModel
public class PmsProductDto {

    @ApiModelProperty("品牌id")
    private Long brandId;

    @ApiModelProperty("店铺id")
    private Integer shopId;

    @ApiModelProperty("")
    private Long productCategoryId;

    @ApiModelProperty("品牌名称")
    private String brandName;

    @ApiModelProperty("商品分类名称")
    private String productCategoryName;

    @ApiModelProperty("")
    private String name;

    @ApiModelProperty("商品图片")
    private String pic;

    @ApiModelProperty("副标题")
    private String subTitle;

    @ApiModelProperty("商品描述")
    private String description;

    @ApiModelProperty("市场价")
    private BigDecimal originalPrice;

    @ApiModelProperty("库存")
    private Integer stock;

    @ApiModelProperty("库存预警值")
    private Integer lowStock;

    @ApiModelProperty("单位")
    private String unit;

    @ApiModelProperty("商品重量，默认为克")
    private BigDecimal weight;

    @ApiModelProperty("以逗号分割的产品服务：1->无忧退货；2->快速退款；3->免费包邮")
    private String serviceIds;

    @ApiModelProperty("")
    private String keywords;
    @ApiModelProperty("")
    private String note;

    @ApiModelProperty("画册图片，连产品图片限制为5张，以逗号分割")
    private String albumPics;

    @ApiModelProperty("")
    private String detailTitle;

    @ApiModelProperty("产品详情网页内容")
    private String detailHtml;

    @ApiModelProperty("移动端网页详情")
    private String detailMobileHtml;

    @ApiModelProperty("发货(出发)地址(商铺地址或手动输入)")
    private String shipAddress;
}
