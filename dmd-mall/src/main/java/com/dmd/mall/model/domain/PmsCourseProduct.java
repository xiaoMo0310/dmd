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
 * 课程商品表
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "pms_course_product")
@Alias(value = "PmsCourseProduct")
@ApiModel
public class PmsCourseProduct extends BaseEntity {

private static final long serialVersionUID = 1L;


    @Column(name = "shop_id")
    @ApiModelProperty("店铺id")
    private Long shopId;

    @Column(name = "shop_name")
    @ApiModelProperty("店铺名称")
    private String shopName;

    @ApiModelProperty("店铺电话")
    private String mobile;

    @ApiModelProperty("产品名称")
    private String name;

    @ApiModelProperty("产品图片(多个使用 , 隔开)")
    private String image;

    @ApiModelProperty("产品的价格")
    private BigDecimal price;

    @Column(name = "product_type")
    @ApiModelProperty("产品类型(1:入门 2:进阶 3:专业)")
    private String productType;

    @Column(name = "product_type_name")
    @ApiModelProperty("产品类型名称")
    private String productTypeName;

    @Column(name = "product_description")
    @ApiModelProperty("产品介绍")
    private String productDescription;

    @Column(name = "content_arrangement")
    @ApiModelProperty("课程内容安排")
    private String contentArrangement;

    @Column(name = "purchase_notes")
    @ApiModelProperty("购买须知")
    private String purchaseNotes;

    @ApiModelProperty("状态(1-在售 2-下架 3-删除)")
    private Integer status;

    @Column(name = "approval_status")
    @ApiModelProperty("审核状态(1:待审核 2:审核通过 3:审核未通过)")
    private Integer approvalStatus;

    @ApiModelProperty("销量")
    private String sales;

    @ApiModelProperty("排序")
    private String sort;


}
