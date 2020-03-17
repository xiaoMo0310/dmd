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
 * 积分好礼订单表
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-12-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "oms_integral_order")
@Alias(value = "OmsIntegralOrder")
@ApiModel
public class OmsIntegralOrder extends BaseEntity {

private static final long serialVersionUID = 1L;


    @Column(name = "order_sn")
    @ApiModelProperty("订单编号")
    private String orderSn;

    @Column(name = "member_id")
    @ApiModelProperty("用户id")
    private Long memberId;

    @Column(name = "user_type")
    @ApiModelProperty("用户类型(member-普通用户 coach-教练)")
    private String userType;

    @Column(name = "shop_id")
    @ApiModelProperty("商铺id(卖家id)")
    private Long shopId;

    @Column(name = "member_username")
    @ApiModelProperty("用户帐号")
    private String memberUsername;

    @Column(name = "product_id")
    @ApiModelProperty("商品id")
    private Long productId;

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

    @Column(name = "product_type")
    @ApiModelProperty("商品类型(1:积分商品)")
    private Integer productType;

    @Column(name = "product_price")
    @ApiModelProperty("商品积分")
    private BigDecimal productPrice;

    @Column(name = "product_quantity")
    @ApiModelProperty("购买数量")
    private Integer productQuantity;

    @Column(name = "product_sku_id")
    @ApiModelProperty("商品sku编号")
    private Long productSkuId;

    @Column(name = "product_category_id")
    @ApiModelProperty("商品分类id")
    private Long productCategoryId;

    @Column(name = "product_attr")
    @ApiModelProperty("商品销售属性:[{\"key\":\"颜色\",\"value\":\"颜色\"},{\"key\":\"容量\",\"value\":\"4G\"}]")
    private String productAttr;

    @Column(name = "total_amount")
    @ApiModelProperty("总的积分金额")
    private BigDecimal totalAmount;

    @Column(name = "pay_amount")
    @ApiModelProperty("实际支付积分")
    private BigDecimal payAmount;

    @Column(name = "freight_amount")
    @ApiModelProperty("运费金额")
    private BigDecimal freightAmount;

    @Column(name = "pay_type")
    @ApiModelProperty("支付方式: 3->积分支付")
    private Integer payType;

    @Column(name = "source_type")
    @ApiModelProperty("订单来源：1->app订单")
    private Integer sourceType;

    @ApiModelProperty("订单状态：1->待发货；2->已发货；3->已完成；4->已关闭；5->售后; 6->取消")
    private Integer status;

    @Column(name = "order_type")
    @ApiModelProperty("订单类型：1->积分商品订单")
    private Integer orderType;

    @Column(name = "delivery_company")
    @ApiModelProperty("物流公司(配送方式)")
    private String deliveryCompany;

    @Column(name = "delivery_sn")
    @ApiModelProperty("物流单号")
    private String deliverySn;

    @Column(name = "receiver_name")
    @ApiModelProperty("收货人名称")
    private String receiverName;

    @Column(name = "receiver_mobile_no")
    @ApiModelProperty("收货人手机号")
    private String receiverMobileNo;

    @Column(name = "province_name")
    @ApiModelProperty("省名称")
    private String provinceName;

    @Column(name = "city_name")
    @ApiModelProperty("市名称")
    private String cityName;

    @Column(name = "district_name")
    @ApiModelProperty("区县/县名称")
    private String districtName;

    @Column(name = "detail_address")
    @ApiModelProperty("详细地址")
    private String detailAddress;

    @Column(name = "receiver_zip_code")
    @ApiModelProperty("邮编")
    private String receiverZipCode;

    @ApiModelProperty("订单备注")
    private String remark;

    @Column(name = "confirm_status")
    @ApiModelProperty("确认收货状态：0->未确认；1->已确认")
    private Integer confirmStatus;

    @Column(name = "delete_status")
    @ApiModelProperty("删除状态：0->未删除；1->已删除")
    private Integer deleteStatus;

    @Column(name = "use_integration")
    @ApiModelProperty("下单时使用的积分")
    private Integer useIntegration;

    @Column(name = "payment_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("支付时间")
    private Date paymentTime;

    @Column(name = "delivery_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("发货时间")
    private Date deliveryTime;

    @Column(name = "receive_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("确认收货时间")
    private Date receiveTime;

    @Column(name = "end_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("订单完成时间")
    private Date endTime;

    @Column(name = "close_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("订单关闭时间")
    private Date closeTime;


}
