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
 * 订单表
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "oms_order")
@Alias(value = "OmsOrder")
@ApiModel
public class OmsOrder extends BaseEntity {

private static final long serialVersionUID = 1L;


    @Column(name = "order_sn")
    @ApiModelProperty("订单编号")
    private String orderSn;

    @Column(name = "member_id")
    @ApiModelProperty("用户id")
    private Long memberId;

    @Column(name = "shop_id")
    @ApiModelProperty("商铺id")
    private Long shopId;

    @Column(name = "member_username")
    @ApiModelProperty("用户帐号")
    private String memberUsername;

    @Column(name = "total_amount")
    @ApiModelProperty("订单总金额")
    private BigDecimal totalAmount;

    @Column(name = "pay_amount")
    @ApiModelProperty("应付金额（实际支付金额）")
    private BigDecimal payAmount;

    @Column(name = "freight_amount")
    @ApiModelProperty("运费金额")
    private BigDecimal freightAmount;

    @Column(name = "promotion_amount")
    @ApiModelProperty("促销优化金额（促销价、满减、阶梯价）")
    private BigDecimal promotionAmount;

    @Column(name = "integration_amount")
    @ApiModelProperty("积分抵扣金额")
    private BigDecimal integrationAmount;

    @Column(name = "discount_amount")
    @ApiModelProperty("管理员后台调整订单使用的折扣金额")
    private BigDecimal discountAmount;

    @Column(name = "pay_type")
    @ApiModelProperty("支付方式：0->未支付；1->支付宝；2->微信")
    private Integer payType;

    @Column(name = "source_type")
    @ApiModelProperty("订单来源：0->PC订单；1->app订单")
    private Integer sourceType;

    @ApiModelProperty("订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单")
    private Integer status;

    @Column(name = "order_type")
    @ApiModelProperty("订单类型：0->正常订单；1->秒杀订单")
    private Integer orderType;

    @Column(name = "delivery_company")
    @ApiModelProperty("物流公司(配送方式)")
    private String deliveryCompany;

    @Column(name = "delivery_sn")
    @ApiModelProperty("物流单号")
    private String deliverySn;

    @Column(name = "auto_confirm_day")
    @ApiModelProperty("自动确认时间（天）")
    private Integer autoConfirmDay;

    @ApiModelProperty("可以获得的积分")
    private Integer integration;

    @Column(name = "promotion_info")
    @ApiModelProperty("活动信息")
    private String promotionInfo;

    @Column(name = "is_invoice")
    @ApiModelProperty("是否开具发票(0:不开具  1:开具)")
    private Integer isInvoice;

    @Column(name = "invoice_id")
    @ApiModelProperty("发票id")
    private Long invoiceId;

    @Column(name = "shipping_id")
    @ApiModelProperty("收货地址id")
    private Long shippingId;

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
