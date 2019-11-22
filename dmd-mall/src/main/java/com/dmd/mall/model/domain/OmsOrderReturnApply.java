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
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 订单退货申请
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "oms_order_return_apply")
@Alias(value = "OmsOrderReturnApply")
@ApiModel
public class OmsOrderReturnApply extends BaseEntity {

private static final long serialVersionUID = 1L;


    @Column(name = "order_id")
    @ApiModelProperty("订单id")
    private Long orderId;

    @Column(name = "shipping_id")
    @ApiModelProperty("收货地址表id")
    private Long shippingId;

    @Column(name = "product_id")
    @ApiModelProperty("退货商品id")
    private Long productId;

    @Column(name = "order_sn")
    @ApiModelProperty("订单编号")
    private String orderSn;

    @Column(name = "member_username")
    @ApiModelProperty("会员用户名")
    private String memberUsername;

    @Column(name = "return_amount")
    @ApiModelProperty("退款金额")
    private BigDecimal returnAmount;

    @Column(name = "return_name")
    @ApiModelProperty("退货人姓名")
    private String returnName;

    @Column(name = "return_phone")
    @ApiModelProperty("退货人电话")
    private String returnPhone;

    @ApiModelProperty("申请状态：0->待处理；1->退款(货)中；2->已完成；3->已拒绝")
    private Integer status;

    @Column(name = "product_pic")
    @ApiModelProperty("商品图片")
    private String productPic;

    @Column(name = "product_name")
    @ApiModelProperty("商品名称")
    private String productName;

    @Column(name = "product_title")
    @ApiModelProperty("商品标题")
    private String productTitle;

    @Column(name = "product_brand")
    @ApiModelProperty("商品品牌")
    private String productBrand;

    @Column(name = "product_attr")
    @ApiModelProperty("商品销售属性：颜色：红色；尺码：xl;")
    private String productAttr;

    @Column(name = "product_count")
    @ApiModelProperty("退货数量")
    private Integer productCount;

    @Column(name = "product_price")
    @ApiModelProperty("商品单价")
    private BigDecimal productPrice;

    @Column(name = "product_real_price")
    @ApiModelProperty("商品实际支付单价")
    private BigDecimal productRealPrice;

    @ApiModelProperty("原因")
    private String reason;

    @ApiModelProperty("描述")
    private String description;

    @Column(name = "proof_pics")
    @ApiModelProperty("凭证图片，以逗号隔开")
    private String proofPics;

    @Column(name = "handle_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("处理时间")
    private Date handleTime;

    @Column(name = "handle_note")
    @ApiModelProperty("处理备注")
    private String handleNote;

    @Column(name = "handle_man")
    @ApiModelProperty("处理人员")
    private String handleMan;

    @Column(name = "receive_man")
    @ApiModelProperty("收货人")
    private String receiveMan;

    @Column(name = "receive_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("收货时间")
    private Date receiveTime;

    @Column(name = "receive_note")
    @ApiModelProperty("收货备注")
    private String receiveNote;


}
