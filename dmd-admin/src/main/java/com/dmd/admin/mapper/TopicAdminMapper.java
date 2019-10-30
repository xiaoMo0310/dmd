package com.dmd.admin.mapper;


import com.dmd.admin.model.domain.TopicBean;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

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

    int deleteTopicById(List<Long> ids);

    List<TopicBean> queryTopic();

    void reduceTopicNum(@Param("list")List<Long> dynamicTopicId,@Param("size") Integer size);
}
