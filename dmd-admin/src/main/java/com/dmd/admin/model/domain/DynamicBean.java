package com.dmd.admin.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author ChenYanbing
 * @title: dynamicBean
 * @projectName dmd-master
 * @description: 我的动态/发布动态 实体类
 * @date 2019/9/239:50
 */
public class DynamicBean {

    @ApiModelProperty(value = "动态ID主键")
    private Long id;
    @ApiModelProperty(value = "发布人/作者/登录人")
    private String dynamicAuthor;
    @ApiModelProperty(value = "用户头像")
    private String dynamicHeadPortrait;
    @ApiModelProperty(value = "文章内容")
    private String dynamicContent;
    @ApiModelProperty(value = "发布时间为当前时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    @ApiModelProperty(value = "发布地址")
    private String dynamicAddress;
    @ApiModelProperty(value = "展示图片")
    private String dynamicPicture;
    @ApiModelProperty(value = "点赞数")
    private Integer dynamicPraise;
    @ApiModelProperty(value = "分享数")
    private Integer dynamicSharenum;
    @ApiModelProperty(value = "评论数")
    private Integer dynamicCommentnum;
    @ApiModelProperty(value = "关联用户ID")
    private Long userId;
    @ApiModelProperty(value = "关联话题表,话题主键")
    private Integer topicId;
    @ApiModelProperty(value = "所在话题的名称")
    private String topicName;
    @ApiModelProperty(value = "是否删除(0=否 1=是)")
    private Integer delflag;

    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date stratTime;

    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endTime;
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

    public Date getStratTime() {
        return stratTime;
    }

    public void setStratTime(Date stratTime) {
        this.stratTime = stratTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
                ", stratTime=" + stratTime +
                ", endTime=" + endTime +
                ", userType=" + userType +
                '}';
    }
}
