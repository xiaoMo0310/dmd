package com.dmd.mall.model.domain;

import java.util.Date;

/**
 * @author ChenYanbing
 * @title: IntegralGiftsBean
 * @projectName dmd-master
 * @description: TODO 积分好礼实体类
 * @date 2019/10/910:18
 */
public class IntegralGiftsBean {
    /**
     * 主键ID
     */
    private Long id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品展示的图片
     */
    private String picture;
    /**
     * 兑换商品所需要的积分
     */
    private String integral;
    /**
     * 商品的介绍，长图展示
     */
    private String introduce;
    /**
     * 发布时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    @Override
    public String toString() {
        return "IntegralGiftsBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", picture='" + picture + '\'' +
                ", integral='" + integral + '\'' +
                ", introduce='" + introduce + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
