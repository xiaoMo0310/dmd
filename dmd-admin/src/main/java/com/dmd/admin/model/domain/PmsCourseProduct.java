package com.dmd.admin.model.domain;

import com.dmd.core.mybatis.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 课程商品表
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "pms_course_product")
@Alias(value = "PmsCourseProduct")
@ApiModel
public class PmsCourseProduct extends BaseEntity {

private static final long serialVersionUID = 1L;


    @Column(name = "product_name")
    @ApiModelProperty("商品名称")
    private String productName;

    @Column(name = "user_id")
    @ApiModelProperty("卖家id")
    private Long userId;

    @Column(name = "shop_id")
    @ApiModelProperty("店铺id")
    private Long shopId;

    @Column(name = "shop_name")
    @ApiModelProperty("店铺名称")
    private String shopName;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("产品的价格")
    private BigDecimal price;

    @ApiModelProperty("其它产品的价格")
    @Column(name = "equipment_price")
    private BigDecimal equipmentPrice;

    @ApiModelProperty("产品总的价格")
    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "product_type")
    @ApiModelProperty("产品类型(1:学证 2:潜水)")
    private Integer productType;

    @Column(name = "is_group")
    @ApiModelProperty("是否是组团产品(0:否 1:是)")
    private Integer isGroup;

    @Column(name = "certificate_id")
    @ApiModelProperty("证书id")
    private Long certificateId;

    @Column(name = "address_id")
    @ApiModelProperty("地点Id")
    private Long addressId;

    @ApiModelProperty("地点")
    private String location;

    @Column(name = "length_play")
    @ApiModelProperty("游玩时长")
    private String lengthPlay;

    @Column(name = "is_time_limit")
    @ApiModelProperty("是否有时间限制(0:无 1:有)")
    private Integer isTimeLimit;

    @Column(name = "start_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("开始时间")
    private Date startTime;

    @Column(name = "end_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("结束时间")
    private Date endTime;

    @Column(name = "number_is_limit")
    @ApiModelProperty("人数是否有限制(0:无 1:有) ")
    private Integer numberIsLimit;

    @Column(name = "number_limit")
    @ApiModelProperty("人数限制")
    private Integer numberLimit;

    @Column(name = "related_product")
    @ApiModelProperty("关联产品(格式[{\"parentId\":22,id\":33,\"text\":\"眼镜\",\"price\":\"200\"}])")
    private String relatedProduct;

    @Column(name = "product_description")
    @ApiModelProperty("产品介绍")
    private String productDescription;

    @ApiModelProperty("产品图片(多个使用 , 隔开)")
    private String image;

    @ApiModelProperty("状态(1-在售 2-下架 3-删除)")
    private Integer status;

    @Column(name = "approval_status")
    @ApiModelProperty("审核状态(1:待审核 2:审核通过 3:审核未通过)")
    private Integer approvalStatus;

    @Column(name = "failure_reason")
    @ApiModelProperty("未审核通过原因")
    private String failureReason;

    @ApiModelProperty("销量")
    private Integer sales;

    @ApiModelProperty("排序")
    private Integer sort;

    @Column(name = "certificate_type")
    @ApiModelProperty("证书类型(1:水肺潜水 2:自由潜水)")
    private Integer certificateType;




}
