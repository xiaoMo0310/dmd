package com.dmd.mall.service;

import com.dmd.mall.model.domain.TopicBean;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: TopicService
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/10/179:57
 */
public interface TopicService {
    List<TopicBean> queryTopicPage(Integer pageNum, Integer pageSize, TopicBean topicBean);

    int addTopic(TopicBean topicBean);

    int updateTopicById(TopicBean topicBean);

    TopicBean findTopicInfoById(Long id);

    int deleteTopicById(String[] ids);
}