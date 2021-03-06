package com.dmd.admin.model.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author ChenYanbing
 * @title: WordsBean
 * @projectName dmd-master
 * @description: 评论留言表实体
 * @date 2019/9/2413:21
 */
public class CommentBean {

    /**
     * 主键id
     */
    private Long commentId;
    /**
     * 评论人昵称：登录人
     */
    private String commentName;
    /**
     * 评论人头像
     */
    private String commentHeadPortrait;

    /**
     * 教练昵称
     */
    private String coachName;
    /**
     * 教练头像
     */
    private String commentHeadPortraitCoach;
    /**
     * 评论时间为当前时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    /**
     * 评论内容
     */
    private String content;

    /**
     * 分类(0=评论 1=回复)
     */
    private Integer type;
    /**
     * ip地址
     */
    private String ipAddress;
    /**
     * 评论状态（0=正常 1=待审核 2=禁止）
     */
    private Integer status;
    /**
     * 是否删除（0=否 1=是）
     */
    private Integer delflag;
    /**
     * 被回复者id，无则为0
     */
    private Long forUid;
    /**
     * 在哪个动态下评论,关联动态表
     */
    private Long forDynamicId;
    /**
     * 在哪个日志下评论,关联日志表
     */
    private Long forDiveLogId;
    /**
     * 评论人id
     */
    private Long userId;

    /**
     * 关联评论主键id
     */
    private Long forPid;

    /**
     * 用户类型
     */
    private Integer userType;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date stratTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endTime;

    /**
     * 被回复者用户类型(1==用户,2==教练 )一级评论为动态发布者类型
     */
    private Integer forUserType;

    /**
     * 被回复者用户昵称
     */
    private String forUserTypeName;

    /**
     * 被回复者用户昵称
     */
    private String forUserTypeName2;


    public String getForUserTypeName2() {
        return forUserTypeName2;
    }

    public void setForUserTypeName2(String forUserTypeName2) {
        this.forUserTypeName2 = forUserTypeName2;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public String getCommentHeadPortraitCoach() {
        return commentHeadPortraitCoach;
    }

    public void setCommentHeadPortraitCoach(String commentHeadPortraitCoach) {
        this.commentHeadPortraitCoach = commentHeadPortraitCoach;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getCommentName() {
        return commentName;
    }

    public void setCommentName(String commentName) {
        this.commentName = commentName;
    }

    public String getCommentHeadPortrait() {
        return commentHeadPortrait;
    }

    public void setCommentHeadPortrait(String commentHeadPortrait) {
        this.commentHeadPortrait = commentHeadPortrait;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDelflag() {
        return delflag;
    }

    public void setDelflag(Integer delflag) {
        this.delflag = delflag;
    }

    public Long getForUid() {
        return forUid;
    }

    public void setForUid(Long forUid) {
        this.forUid = forUid;
    }

    public Long getForDynamicId() {
        return forDynamicId;
    }

    public void setForDynamicId(Long forDynamicId) {
        this.forDynamicId = forDynamicId;
    }

    public Long getForDiveLogId() {
        return forDiveLogId;
    }

    public void setForDiveLogId(Long forDiveLogId) {
        this.forDiveLogId = forDiveLogId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getForPid() {
        return forPid;
    }

    public void setForPid(Long forPid) {
        this.forPid = forPid;
    }

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

    public Integer getForUserType() {
        return forUserType;
    }

    public void setForUserType(Integer forUserType) {
        this.forUserType = forUserType;
    }

    public String getForUserTypeName() {
        return forUserTypeName;
    }

    public void setForUserTypeName(String forUserTypeName) {
        this.forUserTypeName = forUserTypeName;
    }

    @Override
    public String toString() {
        return "CommentBean{" +
                "commentId=" + commentId +
                ", commentName='" + commentName + '\'' +
                ", commentHeadPortrait='" + commentHeadPortrait + '\'' +
                ", coachName='" + coachName + '\'' +
                ", commentHeadPortraitCoach='" + commentHeadPortraitCoach + '\'' +
                ", createTime=" + createTime +
                ", content='" + content + '\'' +
                ", type=" + type +
                ", ipAddress='" + ipAddress + '\'' +
                ", status=" + status +
                ", delflag=" + delflag +
                ", forUid=" + forUid +
                ", forDynamicId=" + forDynamicId +
                ", forDiveLogId=" + forDiveLogId +
                ", userId=" + userId +
                ", forPid=" + forPid +
                ", userType=" + userType +
                ", stratTime=" + stratTime +
                ", endTime=" + endTime +
                ", forUserType=" + forUserType +
                ", forUserTypeName='" + forUserTypeName + '\'' +
                ", forUserTypeName2='" + forUserTypeName2 + '\'' +
                '}';
    }
}
