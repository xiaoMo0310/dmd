package com.dmd.admin.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import cn.afterturn.easypoi.excel.annotation.Excel;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ChenYanbing
 * @title: UmsOrderStatisticsVo
 * @projectName dmd
 * @description: TODO 客户端--订单明细信息统计Vo类
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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date userCreateTime;
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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date orderCreatedTime;
    /**
     * 付款时间
     */
    @Excel(name = "付款时间", exportFormat = "yyyy-MM-dd HH:mm:ss", orderNum = "6", width = 20)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date orderPaymentTime;
    /**
     * 完成时间
     */
    @Excel(name = "完成时间", exportFormat = "yyyy-MM-dd HH:mm:ss", orderNum = "7", width = 20)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date orderEndTime;
    /**
     * 商品名称
     */
    @Excel(name = "商品名称", orderNum = "8", width = 20)
    private String productName;
    /**
     * 订单金额
     */
    @Excel(name = "订单金额(单位/RMN)", orderNum = "9",isStatistics = true, width = 20)
    private BigDecimal total_amount;
    /**
     * 付款金额
     */
    @Excel(name = "付款金额(单位/RMN)", orderNum = "10",isStatistics = true, width = 20)
    private BigDecimal payAmount;
    /**
     * 积分抵扣金额
     */
    @Excel(name = "积分抵扣金额(单位/RMN)", orderNum = "11",isStatistics = true, width = 30)
    private BigDecimal integrationAmount;
    /**
     * 用户积分奖励
     */
    @Excel(name = "用户积分奖励", orderNum = "12",isStatistics = true)
    private Integer integration;

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

    public Date getOrderPaymentTime() {
        return orderPaymentTime;
    }

    public void setOrderPaymentTime(Date orderPaymentTime) {
        this.orderPaymentTime = orderPaymentTime;
    }

    public Date getOrderEndTime() {
        return orderEndTime;
    }

    public void setOrderEndTime(Date orderEndTime) {
        this.orderEndTime = orderEndTime;
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

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
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

    @Override
    public String toString() {
        return "UmsOrderStatisticsVo{" +
                "memberId=" + memberId +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", userCreateTime=" + userCreateTime +
                ", invitationCode='" + invitationCode + '\'' +
                ", orderCreatedTime=" + orderCreatedTime +
                ", orderPaymentTime=" + orderPaymentTime +
                ", orderEndTime=" + orderEndTime +
                ", productName='" + productName + '\'' +
                ", total_amount=" + total_amount +
                ", payAmount=" + payAmount +
                ", integrationAmount=" + integrationAmount +
                ", integration=" + integration +
                '}';
    }
}
