package com.dmd.admin.mapper;


import com.dmd.admin.model.domain.TopicBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: TopicAdminMapper
 * @projectName dmd-masters
 * @description:
 * @date 2019/10/1710:22
 */
public interface TopicAdminMapper {
    List<TopicBean> queryTopicPage(TopicBean topicBean);

    int addTopic(TopicBean topicBean);

    int updateTopicById(TopicBean topicBean);

    TopicBean findTopicInfoById(Long id);

    int deleteTopicById(List<Long> ids);

    List<TopicBean> queryTopic();

    void reduceTopicNum(@Param("list")List<Long> dynamicTopicId,@Param("size") Integer size);

    Long selectBeforeNumByTopicNum(Integer topicNum);

    Long selectSameNumByTopicNumAndId(@Param("id") Integer id, @Param("topicNum") Integer topicNum);
}
