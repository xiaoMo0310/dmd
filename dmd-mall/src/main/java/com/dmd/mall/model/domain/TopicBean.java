package com.dmd.mall.model.domain;


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
    private Integer id;
    //话题名称
    private String topicName;
    //话题描述
    private String topicDescribes;
    //动态数量
    private Integer topicNum;
    //发布时间
    private Date createTime;
    //话题展示图片
    private String topicPicture;

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
                '}';
    }
}
