package com.dmd.mall.model.domain;

import java.util.Date;

/**
 * @author ChenYanbing
 * @title: CoachApplyBean
 * @projectName dmd-master
 * @description: TODO 教练申请表
 * @date 2019/9/1914:43
 */
public class CoachApplyBean {

    //主键ID
    private Long id;
    //申请人姓名
    private String coachName;
    //申请人手机号
    private String coachPhone;
    //身份证正面照片地址
    private String coachIdentityCardFront;
    //身份证反面照片地址
    private String coachIdentityCardReveres;
    //资格证照片地址
    private String coachCredentials;
    //申请时间
    private Date createTime;
    //审核状态 1：审核通过 0：审核中 提交申请默认为0
    private Integer applyStatus;
    //用户登录id
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public String getCoachPhone() {
        return coachPhone;
    }

    public void setCoachPhone(String coachPhone) {
        this.coachPhone = coachPhone;
    }

    public String getCoachIdentityCardFront() {
        return coachIdentityCardFront;
    }

    public void setCoachIdentityCardFront(String coachIdentityCardFront) {
        this.coachIdentityCardFront = coachIdentityCardFront;
    }

    public String getCoachIdentityCardReveres() {
        return coachIdentityCardReveres;
    }

    public void setCoachIdentityCardReveres(String coachIdentityCardReveres) {
        this.coachIdentityCardReveres = coachIdentityCardReveres;
    }

    public String getCoachCredentials() {
        return coachCredentials;
    }

    public void setCoachCredentials(String coachCredentials) {
        this.coachCredentials = coachCredentials;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(Integer applyStatus) {
        this.applyStatus = applyStatus;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CoachApplyBean{" +
                "id=" + id +
                ", coachName='" + coachName + '\'' +
                ", coachPhone='" + coachPhone + '\'' +
                ", coachIdentityCardFront='" + coachIdentityCardFront + '\'' +
                ", coachIdentityCardReveres='" + coachIdentityCardReveres + '\'' +
                ", coachCredentials='" + coachCredentials + '\'' +
                ", createTime=" + createTime +
                ", applyStatus=" + applyStatus +
                ", userId=" + userId +
                '}';
    }
}
