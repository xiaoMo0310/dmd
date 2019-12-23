package com.dmd.mall.model.domain;

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
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 课程商品表
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-01
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
    @ApiModelProperty("店铺id(卖家id)")
    private Long shopId;

    @Column(name = "shop_name")
    @ApiModelProperty("店铺名称")
    private String shopName;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("产品的价格")
    private BigDecimal price;

    @Column(name = "product_type")
    @ApiModelProperty("产品类型(1:学证 2:潜水)")
    private Integer productType;

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

    @ApiModelProperty("库存")
    private Integer stock;

    @Column(name = "cost_includes")
    @ApiModelProperty("费用包含")
    private String costIncludes;

    @Column(name = "cost_not_includes")
    @ApiModelProperty("费用不含")
    private String costNotIncludes;

    @Column(name = "purchase_notes")
    @ApiModelProperty("购买须知")
    private String purchaseNotes;

    @Column(name = "product_description")
    @ApiModelProperty("产品介绍")
    private String productDescription;

    @ApiModelProperty("产品图片(多个使用 , 隔开)")
    private String image;

    @Column(name = "content_arrangement")
    @ApiModelProperty("内容安排(数据格式 [{\"date\":\"第1天\",\"message\":\"内容\"},{\"date\":\"第2天\",\"message\":\"内容\"}]")
    private String contentArrangement;

    @ApiModelProperty("状态(1-在售 2-下架 3-删除 4-停止销售 5-已售完)")
    private Integer status;

    @Column(name = "approval_status")
    @ApiModelProperty("审核状态(1:待审核 2:审核通过 3:审核未通过)")
    private Integer approvalStatus;

    @ApiModelProperty("销量")
    private Integer sales;

    @ApiModelProperty("排序")
    private Integer sort;

    @Column(name = "certificate_id")
    @ApiModelProperty("证书id")
    private Long certificateId;

    @Column(name = "address_id")
    @ApiModelProperty("地址id")
    private Long addressId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty("开始搜索时间")
    @Transient
    private String searchStartTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty("结束搜索时间")
    @Transient
    private String searchEndTime;


    @ApiModelProperty("教练头像")
    @Transient
    private String coachIcon;

    @ApiModelProperty("教练昵称")
    @Transient
    private String coachName;

    @ApiModelProperty("教练等级")
    @Transient
    private String coachGrade;

    @ApiModelProperty("报名人数")
    @Transient
    private Integer peopleNum;


}
