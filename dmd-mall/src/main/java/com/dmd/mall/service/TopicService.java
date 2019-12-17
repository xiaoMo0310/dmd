package com.dmd.mall.service;

import com.dmd.mall.model.domain.TopicBean;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: TopicService
 * @projectName dmd-master
 * @description:
 * @date 2019/9/2910:46
 */
public interface TopicService {
    List<TopicBean> queryTopicPage(Integer pageNum, Integer pageSize);

    List<TopicBean> queryTopicById(Integer id);

    List<TopicBean> queryTopic();

    List<TopicBean> queryTopicName(String topicName);
}
