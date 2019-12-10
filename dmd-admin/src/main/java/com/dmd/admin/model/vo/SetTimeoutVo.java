package com.dmd.admin.model.vo;

import io.swagger.models.auth.In;

import java.math.BigDecimal;

/**
 * @author ChenYanbing
 * @title: SetTimeoutVo
 * @projectName dmd
 * @description: TODO
 * @date 2019/12/917:03
 */
public class SetTimeoutVo {

    /**
     * 时间
     */
    private String date;
    /**
     * 订单数量
     */
    private Integer orderCount;
    /**
     * 订单金额
     */
    private BigDecimal orderAmount;

    private String startTime;

    private String endTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    @Override
    public String toString() {
        return "SetTimeoutVo{" +
                "date='" + date + '\'' +
                ", orderCount=" + orderCount +
                ", orderAmount=" + orderAmount +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
