package com.dmd.admin.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author ChenYanbing
 * @title: CommentByDynamicIdVo
 * @projectName dmd
 * @description:
 * @date 2019/12/189:40
 */
public class CommentByDynamicIdVo {

    /**
     * 评论人昵称：登录人
     */
    private String commentName;
    /**
     * 评论人头像
     */
    private String commentHeadPortrait;
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
     * 用户类型
     */
    private Integer userType;

    @ApiModelProperty(value = "文章内容")
    private String dynamicContent;

    @ApiModelProperty(value = "发布时间为当前时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTimeByDynamic;

    private Long dynamicId;

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

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getDynamicContent() {
        return dynamicContent;
    }

    public void setDynamicContent(String dynamicContent) {
        this.dynamicContent = dynamicContent;
    }

    public Date getCreateTimeByDynamic() {
        return createTimeByDynamic;
    }

    public void setCreateTimeByDynamic(Date createTimeByDynamic) {
        this.createTimeByDynamic = createTimeByDynamic;
    }

    public Long getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(Long dynamicId) {
        this.dynamicId = dynamicId;
    }

    @Override
    public String toString() {
        return "CommentByDynamicIdVo{" +
                "commentName='" + commentName + '\'' +
                ", commentHeadPortrait='" + commentHeadPortrait + '\'' +
                ", createTime=" + createTime +
                ", content='" + content + '\'' +
                ", type=" + type +
                ", ipAddress='" + ipAddress + '\'' +
                ", status=" + status +
                ", userType=" + userType +
                ", dynamicContent='" + dynamicContent + '\'' +
                ", createTimeByDynamic=" + createTimeByDynamic +
                ", dynamicId=" + dynamicId +
                '}';
    }
}
