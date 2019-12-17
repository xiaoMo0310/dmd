package com.dmd.mall.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
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
    //是否删除
    private Integer delflag;
    //登录人是否关注动态发布用户关注人(0==未关注)(1==已关注)
    private Integer Identification;

    //登录人是否点赞动态发布者的动态(0==已点赞)(1==未点赞)
    private Integer identificationPraise;

    /**
     * 图片宽
     */
    private Integer width;

    /**
     * 图片高
     */
    private Integer height;

    /**
     * 用户类型
     */
    private Integer userType;

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getIdentificationPraise() {
        return identificationPraise;
    }

    public void setIdentificationPraise(Integer identificationPraise) {
        this.identificationPraise = identificationPraise;
    }

    public Integer getIdentification() {
        return Identification;
    }

    public void setIdentification(Integer identification) {
        Identification = identification;
    }

    public Integer getDelflag() {
        return delflag;
    }

    public void setDelflag(Integer delflag) {
        this.delflag = delflag;
    }

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
                ", delflag=" + delflag +
                ", Identification=" + Identification +
                ", identificationPraise=" + identificationPraise +
                ", width=" + width +
                ", height=" + height +
                ", userType=" + userType +
                '}';
    }
}
