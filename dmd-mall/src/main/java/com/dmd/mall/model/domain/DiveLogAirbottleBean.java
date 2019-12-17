package com.dmd.mall.model.domain;

/**
 * @author ChenYanbing
 * @title: DiveLogAirbottleBean
 * @projectName dmd-masters
 * @description: 潜水日志气瓶表
 * @date 2019/10/1210:42
 */
public class DiveLogAirbottleBean {

    /**
     * 主键id
     */
    private Long id;
    /**
     * 开始气体总量
     */
    private Integer start;
    /**
     * 结束气体总量
     */
    private Integer end;
    /**
     * 剩余气体总量
     */
    private Integer residue;
    /**
     * 气体类型(0=bar/1=psi)
     */
    private Integer gasType;
    /**
     * 混合类型(0=空气/1=高氧/2=氦氧氮混合)
     */
    private Integer mixtureType;
    /**
     * 申明是第几个气瓶
     */
    private Integer airbottleNum;
    /**
     * 关联用户表 用户ID
     */
    private Long userId;
    /**
     * 关联潜水日志表，在哪个日志下
     */
    private Long diveLogId;
    /**
     * 逻辑删除，0=否 1=是
     */
    private Integer delflag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public Integer getResidue() {
        return residue;
    }

    public void setResidue(Integer residue) {
        this.residue = residue;
    }

    public Integer getGasType() {
        return gasType;
    }

    public void setGasType(Integer gasType) {
        this.gasType = gasType;
    }

    public Integer getMixtureType() {
        return mixtureType;
    }

    public void setMixtureType(Integer mixtureType) {
        this.mixtureType = mixtureType;
    }

    public Integer getAirbottleNum() {
        return airbottleNum;
    }

    public void setAirbottleNum(Integer airbottleNum) {
        this.airbottleNum = airbottleNum;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDiveLogId() {
        return diveLogId;
    }

    public void setDiveLogId(Long diveLogId) {
        this.diveLogId = diveLogId;
    }

    public Integer getDelflag() {
        return delflag;
    }

    public void setDelflag(Integer delflag) {
        this.delflag = delflag;
    }

    @Override
    public String toString() {
        return "DiveLogAirbottleBean{" +
                "id=" + id +
                ", start=" + start +
                ", end=" + end +
                ", residue=" + residue +
                ", gasType=" + gasType +
                ", mixtureType=" + mixtureType +
                ", airbottleNum=" + airbottleNum +
                ", userId=" + userId +
                ", diveLogId=" + diveLogId +
                ", delflag=" + delflag +
                '}';
    }
}
