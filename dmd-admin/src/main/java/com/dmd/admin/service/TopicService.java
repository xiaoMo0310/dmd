package com.dmd.admin.service;

import com.dmd.admin.model.domain.TopicBean;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: TopicService
 * @projectName dmd-masters
 * @description:
 * @date 2019/10/179:57
 */
public interface TopicService {
    List<TopicBean> queryTopicPage(Integer pageNum, Integer pageSize, TopicBean topicBean);

    int addTopic(TopicBean topicBean);

    int updateTopicById(TopicBean topicBean);

    TopicBean findTopicInfoById(Long id);

    int deleteTopicById(List<Long> ids);
}
