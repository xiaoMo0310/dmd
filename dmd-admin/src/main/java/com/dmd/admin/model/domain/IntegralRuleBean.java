package com.dmd.admin.model.domain;

import java.util.Date;

/**
 * @author ChenYanbing
 * @title: IntegralRuleBean
 * @projectName dmd-masters
 * @description: TODO 积分规则说明实体类
 * @date 2019/10/2213:56
 */
public class IntegralRuleBean {

    /**
     * 主键
     */
    private Integer id;
    /**
     * 规则说明
     */
    private String ruledescription;
    /**
     * 图片示例
     */
    private String picturesample;
    /**
     * 更新时间
     */
    private Date createTime;
    /**
     * 操作人
     */
    private String operationName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRuledescription() {
        return ruledescription;
    }

    public void setRuledescription(String ruledescription) {
        this.ruledescription = ruledescription;
    }

    public String getPicturesample() {
        return picturesample;
    }

    public void setPicturesample(String picturesample) {
        this.picturesample = picturesample;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    @Override
    public String toString() {
        return "IntegralRuleBean{" +
                "id=" + id +
                ", ruledescription='" + ruledescription + '\'' +
                ", picturesample='" + picturesample + '\'' +
                ", createTime=" + createTime +
                ", operationName='" + operationName + '\'' +
                '}';
    }
}
