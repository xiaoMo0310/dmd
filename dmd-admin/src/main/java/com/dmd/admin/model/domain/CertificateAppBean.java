package com.dmd.admin.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author ChenYanbing
 * @title: CertificateAppBean
 * @projectName dmd
 * @description:  用户上传证书实体类
 * @date 2019/11/2918:36
 */
public class CertificateAppBean {

    /**
     * id
     */
    private Long id;
    /**
     * 上传的证书图片
     */
    private String pictures;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 审核状态
     */
    private Integer status;
    /**
     * 证书id
     */
    private Integer certificateId;
    /**
     * 上传时间
     */
    @ApiModelProperty(value = "上传时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    /**
     * 审核通过时间
     */
    @ApiModelProperty(value = "审核通过时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date adopTime;
    /**
     * 操作人
     */
    private String operator;
    /**
     * 未通过原因
     */
    private String reason;

    /**
     * 证书名称
     */
    private String certificateName;

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
     * 用户类型
     */
    private Integer userType;

    /**
     * 教练昵称
     */
    private String coachName;

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
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

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Integer certificateId) {
        this.certificateId = certificateId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getAdopTime() {
        return adopTime;
    }

    public void setAdopTime(Date adopTime) {
        this.adopTime = adopTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "CertificateAppBean{" +
                "id=" + id +
                ", pictures='" + pictures + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", status=" + status +
                ", certificateId=" + certificateId +
                ", createTime=" + createTime +
                ", adopTime=" + adopTime +
                ", operator='" + operator + '\'' +
                ", reason='" + reason + '\'' +
                ", certificateName='" + certificateName + '\'' +
                ", stratTime=" + stratTime +
                ", endTime=" + endTime +
                ", userType=" + userType +
                ", coachName='" + coachName + '\'' +
                '}';
    }
}
