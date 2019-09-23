package com.dmd.mall.model.domain;

import java.util.Date;

/**
 * @author ChenYanbing
 * @title: dynamicBean
 * @projectName dmd-master
 * @description: TODO 我的动态/发布动态 实体类
 * @date 2019/9/239:50
 */
public class DynamicBean {

    //主键ID
    private Long id;
    //发布人/作者/登录人
    private String dynamicAuthor;
    //用户头像
    private String dynamicHeadPortrait;
    //文章内容
    private String dynamicContent;
    //发布时间为当前时间
    private Date createTime;
    //发布地址
    private String dynamicAddress;
    //展示图片
    private String dynamicPicture;
    //点赞数
    private Integer dynamicPraise;
    //分享数
    private Integer dynamicSharenum;
    //评论数
    private Integer dynamicCommentnum;
    //关联用户ID
    private Long userId;
    //关联话题表
    private Integer topicId;
    //所在话题的名称
    private String topicName;

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDynamicAuthor() {
        return dynamicAuthor;
    }

    public void setDynamicAuthor(String dynamicAuthor) {
        this.dynamicAuthor = dynamicAuthor;
    }

    public String getDynamicHeadPortrait() {
        return dynamicHeadPortrait;
    }

    public void setDynamicHeadPortrait(String dynamicHeadPortrait) {
        this.dynamicHeadPortrait = dynamicHeadPortrait;
    }

    public String getDynamicContent() {
        return dynamicContent;
    }

    public void setDynamicContent(String dynamicContent) {
        this.dynamicContent = dynamicContent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDynamicAddress() {
        return dynamicAddress;
    }

    public void setDynamicAddress(String dynamicAddress) {
        this.dynamicAddress = dynamicAddress;
    }

    public String getDynamicPicture() {
        return dynamicPicture;
    }

    public void setDynamicPicture(String dynamicPicture) {
        this.dynamicPicture = dynamicPicture;
    }

    public Integer getDynamicPraise() {
        return dynamicPraise;
    }

    public void setDynamicPraise(Integer dynamicPraise) {
        this.dynamicPraise = dynamicPraise;
    }

    public Integer getDynamicSharenum() {
        return dynamicSharenum;
    }

    public void setDynamicSharenum(Integer dynamicSharenum) {
        this.dynamicSharenum = dynamicSharenum;
    }

    public Integer getDynamicCommentnum() {
        return dynamicCommentnum;
    }

    public void setDynamicCommentnum(Integer dynamicCommentnum) {
        this.dynamicCommentnum = dynamicCommentnum;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    @Override
    public String toString() {
        return "DynamicBean{" +
                "id=" + id +
                ", dynamicAuthor='" + dynamicAuthor + '\'' +
                ", dynamicHeadPortrait='" + dynamicHeadPortrait + '\'' +
                ", dynamicContent='" + dynamicContent + '\'' +
                ", createTime=" + createTime +
                ", dynamicAddress='" + dynamicAddress + '\'' +
                ", dynamicPicture='" + dynamicPicture + '\'' +
                ", dynamicPraise=" + dynamicPraise +
                ", dynamicSharenum=" + dynamicSharenum +
                ", dynamicCommentnum=" + dynamicCommentnum +
                ", userId=" + userId +
                ", topicId=" + topicId +
                ", topicName='" + topicName + '\'' +
                '}';
    }
}
