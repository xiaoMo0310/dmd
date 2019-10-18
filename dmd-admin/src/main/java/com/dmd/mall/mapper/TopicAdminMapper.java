package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.TopicBean;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: TopicAdminMapper
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/10/1710:22
 */
public interface TopicAdminMapper {
    List<TopicBean> queryTopicPage(TopicBean topicBean);

    int addTopic(TopicBean topicBean);

    int updateTopicById(TopicBean topicBean);

    TopicBean findTopicInfoById(Long id);

    int deleteTopicById(String[] ids);

    List<TopicBean> queryTopic();

    void reduceTopicNum(String[] dynamicTopicId);
}
