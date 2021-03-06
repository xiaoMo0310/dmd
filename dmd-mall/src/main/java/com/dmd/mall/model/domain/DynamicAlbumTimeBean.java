package com.dmd.mall.model.domain;

/**
 * @author ChenYanbing
 * @title: DynamicAlbumTimeBean
 * @projectName dmd
 * @description:
 * @date 2019/12/615:20
 */
public class DynamicAlbumTimeBean {

    /**
     * 用户Id
     */
    private Long userId;
    /**
     * 图片张数
     */
    private Integer pictureNum;
    /**
     * 用户月份所发的所有图片
     */
    private String picture;
    /**
     * 日期月份
     */
    private String months;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getPictureNum() {
        return pictureNum;
    }

    public void setPictureNum(Integer pictureNum) {
        this.pictureNum = pictureNum;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months;
    }

    @Override
    public String toString() {
        return "DynamicAlbumTimeBean{" +
                "userId=" + userId +
                ", pictureNum=" + pictureNum +
                ", picture='" + picture + '\'' +
                ", months='" + months + '\'' +
                '}';
    }


}
