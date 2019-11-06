package com.dmd.mall.model.domain;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author ChenYanbing
 * @title: DiveCertificateBean
 * @projectName dmd-masters
 * @description: TODO 我的潜水证书申请实体类
 * @date 2019/11/411:05
 */
public class DiveCertificateBean {
    /**
     * 主键
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * OPEN WATER DIVER（开放水域初级潜水员）
     */
    private String owd;
    /**
     * ADVANCED OPEN WATER DIVER（开放水域进阶潜水员)
     */
    private String aowd;
    /**
     * EMERGENCY FIRST ACTION（第一紧急反应）
     */
    private String efa;
    /**
     * RESCUE DIVER（救援潜水员）
     */
    private String rd;
    /**
     * SPECIAL COURSES（潜水专长课程）
     */
    private String sc;
    /**
     * MASTER SCUBA DIVER（名仕潜水员）
     */
    private String msd;
    /**
     * DIVER MASTER（潜水长）
     */
    private String dm;
    /**
     * Open Water Scuba Instructor（开放水域水肺教练）
     */
    private String owsi;
    /**
     * Master Scuba Diver Trainer（名仕潜水员训练官）
     */
    private String msdt;
    /**
     * Instructor Development Course Staff Instructor（教练发展课程参谋教练）
     */
    private String idcsi;
    /**
     * MASTER INSTRUCTOR（教练长）
     */
    private String mi;
    /**
     * COURSE DIRECTOR（课程总监）
     */
    private String cd;
    /**
     * 更新时间
     */
    private Date createTime;
    /**
     * 审核中=0；审核通过=1；审核未通过=2
     */
    private Integer status;

    /**
     * 等级标识符
     */
    @ApiModelProperty("等级标识符")
    private Integer identifier;

    public Integer getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Integer identifier) {
        this.identifier = identifier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOwd() {
        return owd;
    }

    public void setOwd(String owd) {
        this.owd = owd;
    }

    public String getAowd() {
        return aowd;
    }

    public void setAowd(String aowd) {
        this.aowd = aowd;
    }

    public String getEfa() {
        return efa;
    }

    public void setEfa(String efa) {
        this.efa = efa;
    }

    public String getRd() {
        return rd;
    }

    public void setRd(String rd) {
        this.rd = rd;
    }

    public String getSc() {
        return sc;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }

    public String getMsd() {
        return msd;
    }

    public void setMsd(String msd) {
        this.msd = msd;
    }

    public String getDm() {
        return dm;
    }

    public void setDm(String dm) {
        this.dm = dm;
    }

    public String getOwsi() {
        return owsi;
    }

    public void setOwsi(String owsi) {
        this.owsi = owsi;
    }

    public String getMsdt() {
        return msdt;
    }

    public void setMsdt(String msdt) {
        this.msdt = msdt;
    }

    public String getIdcsi() {
        return idcsi;
    }

    public void setIdcsi(String idcsi) {
        this.idcsi = idcsi;
    }

    public String getMi() {
        return mi;
    }

    public void setMi(String mi) {
        this.mi = mi;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DiveCertificateBean{" +
                "id=" + id +
                ", userId=" + userId +
                ", owd='" + owd + '\'' +
                ", aowd='" + aowd + '\'' +
                ", efa='" + efa + '\'' +
                ", rd='" + rd + '\'' +
                ", sc='" + sc + '\'' +
                ", msd='" + msd + '\'' +
                ", dm='" + dm + '\'' +
                ", owsi='" + owsi + '\'' +
                ", msdt='" + msdt + '\'' +
                ", idcsi='" + idcsi + '\'' +
                ", mi='" + mi + '\'' +
                ", cd='" + cd + '\'' +
                ", createTime=" + createTime +
                ", status=" + status +
                ", identifier=" + identifier +
                '}';
    }
}
