package com.dmd.mall.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * @author ChenYanbing
 * @title: UmsUserFeedbackBean
 * @projectName dmd
 * @description:
 * @date 2019/12/210:38
 */
public class UmsUserFeedbackBean {

    /**
     * 主键id
     */
    private Long id;
    /**
     * 问题/意见描述
     */
    private String problemDescription;
    /**
     * 用户Id
     */
    private Long userId;
    /**
     * 图片
     */
    private String picture;
    /**
     * 提交时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    /**
     * 问题类型
     */
    private Long problemId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    @Override
    public String toString() {
        return "UmsUserFeedbackBean{" +
                "id=" + id +
                ", problemDescription='" + problemDescription + '\'' +
                ", userId=" + userId +
                ", picture='" + picture + '\'' +
                ", createTime=" + createTime +
                ", problemId=" + problemId +
                '}';
    }
}
