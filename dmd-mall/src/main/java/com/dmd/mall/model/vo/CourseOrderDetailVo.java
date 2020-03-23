package com.dmd.mall.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/11/18 13:54
 * @Description 学证商品订单详情vo类
 */
@Data
public class CourseOrderDetailVo implements Serializable {

    private static final long serialVersionUID = 4412280747690786805L;
    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 订单详情id
     */
    private Long orderItemId;

    /**
     * 订单编号
     */
    private String orderSn;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userIcon;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 教练等级
     */
    private String coachGrade;

    /**
     * 电话
     */
    private String phone;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;

    /**
     * 实际支付金额
     */
    private BigDecimal payAmount;

    /**
     * 积分抵扣金额
     */
    private BigDecimal integrationAmount;

    /**
     * 支付方式: 1->支付宝；2->微信 3->积分支付
     */
    private Integer payType;

    /**
     * 支付时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date paymentTime;

    /**
     * 订单状态：0->待付款；1->已付款；2->进行中；3->已完成；4->已关闭；5->售后 6:取消
     */
    private Integer status;

    /**
     * 商品id
     */
    private Long productId;
    /**
     * 商品图片
     */
    private String productPic;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品标题
     */
    private String productTitle;

    /**
     * 商品价格
     */
    private BigDecimal productPrice;

    /**
     * 商品类型
     */
    private Integer productType;

    /**
     * 订单创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;

    /**
     * 订单完成时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    /**
     * 订单关闭时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date closeTime;

    /**
     * 其它产品价格
     */
    private BigDecimal equipmentPrice;

    /**
     * 关联的产品
     */
    private String productCategoryPrice;

    /**
     * 商品数量
     */
    private Integer productQuantity;

    /**
     * 订单类型
     */
    private Integer orderType;

    private BigDecimal realAmount;

    private Integer returnStatus;

    /**
     * 退款申请时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date returnApplyTime;

}
