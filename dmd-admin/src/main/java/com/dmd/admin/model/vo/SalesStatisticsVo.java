package com.dmd.admin.model.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author ChenYanbing
 * @title: SalesStatisticsVo
 * @projectName dmd
 * @description: TODO
 * @date 2019/12/416:46
 */
public class SalesStatisticsVo {


    /**
     * 日期
     */
    @Excel(name = "日期", exportFormat = "yyyy-MM-dd", orderNum = "0", width = 30)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy/MM/dd",timezone="GMT+8")
    private Date createTime;

    @Excel(name = "注册人数", orderNum = "1" )
    private Integer memberNum;
    /**
     * 订单量
     */
    @Excel(name = "订单量", orderNum = "2" )
    private Integer orderNum;
    /**
     * 已付款订单量
     */
    @Excel(name = "已付款订单量", orderNum = "3" )
    private Integer paidNum;
    /**
     * 订单付款金额
     */
    @Excel(name = "订单付款金额(单位/RMB)", orderNum = "4"  ,width = 30)
    private Integer payAmount;
    /**
     * 待完成订单数量
     */
    @Excel(name = "待完成订单数量", orderNum = "5" )
    private Integer completed;
    /**
     * 待完成订单金额
     */
    @Excel(name = "待完成订单金额(单位/RMB)", orderNum = "6"  ,width = 30)
    private Integer completedMoney;
    /**
     * 已完成订单数量
     */
    @Excel(name = "已完成订单数量", orderNum = "7" )
    private Integer over;
    /**
     * 已完成订单金额
     */
    @Excel(name = "已完成订单金额(单位/RMB)", orderNum = "8" ,width = 30)
    private Integer overMoney;
    /**
     * 订单类型标识符
     */
    private Integer orderType;

    /**
     * 日周月季年标识符(1==日，2==周，3==近三十天，4==本月，5==季，6==年)
     */
    private Integer identifier;


    /**
     * 付款完成开始时间
     */
    @ApiModelProperty(value = "日期开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date startTime;
    /**
     * 付款完成结束时间
     */
    @ApiModelProperty(value = "日期结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endTime;


    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getPaidNum() {
        return paidNum;
    }

    public void setPaidNum(Integer paidNum) {
        this.paidNum = paidNum;
    }

    public Integer getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Integer payAmount) {
        this.payAmount = payAmount;
    }

    public Integer getCompleted() {
        return completed;
    }

    public void setCompleted(Integer completed) {
        this.completed = completed;
    }

    public Integer getCompletedMoney() {
        return completedMoney;
    }

    public void setCompletedMoney(Integer completedMoney) {
        this.completedMoney = completedMoney;
    }

    public Integer getOver() {
        return over;
    }

    public void setOver(Integer over) {
        this.over = over;
    }

    public Integer getOverMoney() {
        return overMoney;
    }

    public void setOverMoney(Integer overMoney) {
        this.overMoney = overMoney;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Integer identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return "AnalysisReportVo{" +
                "createTime=" + createTime +
                ", orderNum=" + orderNum +
                ", paidNum=" + paidNum +
                ", payAmount=" + payAmount +
                ", completed=" + completed +
                ", completedMoney=" + completedMoney +
                ", over=" + over +
                ", overMoney=" + overMoney +
                ", orderType=" + orderType +
                ", identifier=" + identifier +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
