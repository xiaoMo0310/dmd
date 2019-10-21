package com.dmd.admin.model.domain;


import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author ChenYanbing
 * @title: TopicBean
 * @projectName dmd-master
 * @description: TODO 话题实体类，内容由后台管理员编辑
 * @date 2019/9/2316:10
 */
public class TopicBean {

    //话题id，唯一标识，绑定动态表话题id
    @ApiModelProperty(value = "话题id")
    private Integer id;

    @ApiModelProperty(value = "话题名称")
    private String topicName;

    @ApiModelProperty(value = "话题描述")
    private String topicDescribes;

    @ApiModelProperty(value = "动态数量")
    private Integer topicNum;

    @ApiModelProperty(value = "发布时间")
    private Date createTime;

    @ApiModelProperty(value = "话题展示图片")
    private String topicPicture;

    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date stratTime;

    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicDescribes() {
        return topicDescribes;
    }

    public void setTopicDescribes(String topicDescribes) {
        this.topicDescribes = topicDescribes;
    }

    public Integer getTopicNum() {
        return topicNum;
    }

    public void setTopicNum(Integer topicNum) {
        this.topicNum = topicNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTopicPicture() {
        return topicPicture;
    }

    public void setTopicPicture(String topicPicture) {
        this.topicPicture = topicPicture;
    }

    @Override
    public String toString() {
        return "TopicBean{" +
                "id=" + id +
                ", topicName='" + topicName + '\'' +
                ", topicDescribes='" + topicDescribes + '\'' +
                ", topicNum=" + topicNum +
                ", createTime=" + createTime +
                ", topicPicture='" + topicPicture + '\'' +
                ", stratTime=" + stratTime +
                ", endTime=" + endTime +
                '}';
    }
}
