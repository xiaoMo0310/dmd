package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.TopicBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: TopicMapper
 * @projectName dmd-master
 * @description:
 * @date 2019/9/2910:48
 */
public interface TopicMapper {
    List<TopicBean> queryTopicPage();

    List<TopicBean> queryTopicById(Integer id);

    List<TopicBean> queryTopic();

    void addTopicNum(Integer topicId);

    void reduceTopicNum(Integer topicId);

    List<TopicBean> queryTopicName(@Param("topicName") String topicName);

    List<TopicBean> queryTopicByContent(@Param("content") String content);

    Integer selectFavorites(@Param("userId")Long userId,@Param("topicId") Integer topicId);
}
