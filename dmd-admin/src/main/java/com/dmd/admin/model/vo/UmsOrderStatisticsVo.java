package com.dmd.admin.model.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ChenYanbing
 * @title: UmsOrderStatisticsVo
 * @projectName dmd
 * @description: 订单明细信息统计Vo类
 * @date 2019/11/2515:30
 */
public class UmsOrderStatisticsVo {

    /**
     * 用户id
     */
    @Excel(name = "用户Id", orderNum = "0")
    private Long memberId;
    /**
     * 用户名
     */
    @Excel(name = "用户名", orderNum = "1" , width = 20 )
    private String userName;
    /**
     * 注册手机号
     */
    @Excel(name = "注册手机号", orderNum = "2" , width = 20)
    private String phone;
    /**
     * 注册时间
     */
    @Excel(name = "注册时间", exportFormat = "yyyy-MM-dd HH:mm:ss", orderNum = "3", width = 20)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone="GMT+8")
    private Date userCreateTime;

    /**
     * 注册查询开始时间
     */
    @ApiModelProperty(value = "注册查询开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date startUserCreateTime;
    /**
     * 注册查询结束时间
     */
    @ApiModelProperty(value = "注册查询结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endUserCreateTime;

    /**
     * 邀请码
     */
    @Excel(name = "邀请码", orderNum = "4")
    private String invitationCode;
    /**
     * 下单时间
     */
    @Excel(name = "下单时间", exportFormat = "yyyy-MM-dd HH:mm:ss", orderNum = "5", width = 20)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone="GMT+8")
    private Date orderCreatedTime;

    /**
     * 邀请码
     */
    @Excel(name = "订单类型", orderNum = "6",replace = {"普通订单_0", "潜水学证订单_1","积分商品订单_2"})
    private Integer orderType;

    /**
     * 下单查询开始时间
     */
    @ApiModelProperty(value = "下单查询开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date startOrderCreatedTime;
    /**
     * 下单查询结束时间
     */
    @ApiModelProperty(value = "下单查询结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endOrderCreatedTime;

    /**
     * 付款时间
     */
    @Excel(name = "付款时间", exportFormat = "yyyy-MM-dd HH:mm:ss", orderNum = "7", width = 20)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone="GMT+8")
    private Date orderPaymentTime;

    /**
     * 付款查询开始时间
     */
    @ApiModelProperty(value = "付款查询开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date startOrderPaymentTime;
    /**
     * 付款查询结束时间
     */
    @ApiModelProperty(value = "付款查询结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endOrderPaymentTime;

    /**
     * 完成时间
     */
    @Excel(name = "完成时间", exportFormat = "yyyy-MM-dd HH:mm:ss", orderNum = "8", width = 20)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone="GMT+8")
    private Date orderEndTime;

    /**
     * 付款完成开始时间
     */
    @ApiModelProperty(value = "付款完成开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date startOrderEndTime;
    /**
     * 付款完成结束时间
     */
    @ApiModelProperty(value = "付款完成结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endOrderEndTime;

    /**
     * 商品名称
     */
    @Excel(name = "商品名称", orderNum = "9", width = 20)
    private String productName;
    /**
     * 订单金额
     */
    @Excel(name = "订单金额(单位/RMN)", orderNum = "10",isStatistics = true, width = 20)
    private BigDecimal total_amount;

    /**
     * 订单金额区间查询(开始)
     */
    private BigDecimal startTotalAmount;
    /**
     * 订单金额区间查询(结束)
     */
    private BigDecimal endTotalAmount;
    /**
     * 付款金额
     */
    @Excel(name = "付款金额(单位/RMN)", orderNum = "11",isStatistics = true, width = 20)
    private BigDecimal payAmount;

    /**
     * 付款金额区间查询(开始)
     */
    private BigDecimal startPayAmount;
    /**
     * 付款金额区间查询(结束)
     */
    private BigDecimal endPayAmount;
    /**
     * 积分抵扣金额
     */
    @Excel(name = "积分抵扣金额(单位/RMN)", orderNum = "12",isStatistics = true, width = 30)
    private BigDecimal integrationAmount;
    /**
     * 用户积分奖励
     */
    @Excel(name = "用户积分奖励", orderNum = "13",isStatistics = true)
    private Integer integration;

    /**
     * 日周月季年标识符(1==日，2==周，3==近三十天，4==本月，5==季，6==年)
     */
    private Integer identifier;

    /**
     * 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->售后; 6->取消
     */
    @Excel(name = "订单状态", orderNum = "14",replace = {"待付款_0", "待发货_1","已发货_2","已完成_3","已关闭_4","售后_5","取消_6"})
    private Integer status;


    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(Date userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    public Date getStartUserCreateTime() {
        return startUserCreateTime;
    }

    public void setStartUserCreateTime(Date startUserCreateTime) {
        this.startUserCreateTime = startUserCreateTime;
    }

    public Date getEndUserCreateTime() {
        return endUserCreateTime;
    }

    public void setEndUserCreateTime(Date endUserCreateTime) {
        this.endUserCreateTime = endUserCreateTime;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public Date getOrderCreatedTime() {
        return orderCreatedTime;
    }

    public void setOrderCreatedTime(Date orderCreatedTime) {
        this.orderCreatedTime = orderCreatedTime;
    }

    public Date getStartOrderCreatedTime() {
        return startOrderCreatedTime;
    }

    public void setStartOrderCreatedTime(Date startOrderCreatedTime) {
        this.startOrderCreatedTime = startOrderCreatedTime;
    }

    public Date getEndOrderCreatedTime() {
        return endOrderCreatedTime;
    }

    public void setEndOrderCreatedTime(Date endOrderCreatedTime) {
        this.endOrderCreatedTime = endOrderCreatedTime;
    }

    public Date getOrderPaymentTime() {
        return orderPaymentTime;
    }

    public void setOrderPaymentTime(Date orderPaymentTime) {
        this.orderPaymentTime = orderPaymentTime;
    }

    public Date getStartOrderPaymentTime() {
        return startOrderPaymentTime;
    }

    public void setStartOrderPaymentTime(Date startOrderPaymentTime) {
        this.startOrderPaymentTime = startOrderPaymentTime;
    }

    public Date getEndOrderPaymentTime() {
        return endOrderPaymentTime;
    }

    public void setEndOrderPaymentTime(Date endOrderPaymentTime) {
        this.endOrderPaymentTime = endOrderPaymentTime;
    }

    public Date getOrderEndTime() {
        return orderEndTime;
    }

    public void setOrderEndTime(Date orderEndTime) {
        this.orderEndTime = orderEndTime;
    }

    public Date getStartOrderEndTime() {
        return startOrderEndTime;
    }

    public void setStartOrderEndTime(Date startOrderEndTime) {
        this.startOrderEndTime = startOrderEndTime;
    }

    public Date getEndOrderEndTime() {
        return endOrderEndTime;
    }

    public void setEndOrderEndTime(Date endOrderEndTime) {
        this.endOrderEndTime = endOrderEndTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(BigDecimal total_amount) {
        this.total_amount = total_amount;
    }

    public BigDecimal getStartTotalAmount() {
        return startTotalAmount;
    }

    public void setStartTotalAmount(BigDecimal startTotalAmount) {
        this.startTotalAmount = startTotalAmount;
    }

    public BigDecimal getEndTotalAmount() {
        return endTotalAmount;
    }

    public void setEndTotalAmount(BigDecimal endTotalAmount) {
        this.endTotalAmount = endTotalAmount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getStartPayAmount() {
        return startPayAmount;
    }

    public void setStartPayAmount(BigDecimal startPayAmount) {
        this.startPayAmount = startPayAmount;
    }

    public BigDecimal getEndPayAmount() {
        return endPayAmount;
    }

    public void setEndPayAmount(BigDecimal endPayAmount) {
        this.endPayAmount = endPayAmount;
    }

    public BigDecimal getIntegrationAmount() {
        return integrationAmount;
    }

    public void setIntegrationAmount(BigDecimal integrationAmount) {
        this.integrationAmount = integrationAmount;
    }

    public Integer getIntegration() {
        return integration;
    }

    public void setIntegration(Integer integration) {
        this.integration = integration;
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Integer identifier) {
        this.identifier = identifier;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UmsOrderStatisticsVo{" +
                "memberId=" + memberId +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", userCreateTime=" + userCreateTime +
                ", startUserCreateTime=" + startUserCreateTime +
                ", endUserCreateTime=" + endUserCreateTime +
                ", invitationCode='" + invitationCode + '\'' +
                ", orderCreatedTime=" + orderCreatedTime +
                ", orderType=" + orderType +
                ", startOrderCreatedTime=" + startOrderCreatedTime +
                ", endOrderCreatedTime=" + endOrderCreatedTime +
                ", orderPaymentTime=" + orderPaymentTime +
                ", startOrderPaymentTime=" + startOrderPaymentTime +
                ", endOrderPaymentTime=" + endOrderPaymentTime +
                ", orderEndTime=" + orderEndTime +
                ", startOrderEndTime=" + startOrderEndTime +
                ", endOrderEndTime=" + endOrderEndTime +
                ", productName='" + productName + '\'' +
                ", total_amount=" + total_amount +
                ", startTotalAmount=" + startTotalAmount +
                ", endTotalAmount=" + endTotalAmount +
                ", payAmount=" + payAmount +
                ", startPayAmount=" + startPayAmount +
                ", endPayAmount=" + endPayAmount +
                ", integrationAmount=" + integrationAmount +
                ", integration=" + integration +
                ", identifier=" + identifier +
                ", status=" + status +
                '}';
    }
}
