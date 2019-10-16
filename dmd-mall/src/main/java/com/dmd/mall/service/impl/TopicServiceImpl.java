package com.dmd.mall.service.impl;

import com.dmd.mall.mapper.TopicMapper;
import com.dmd.mall.model.domain.TopicBean;
import com.dmd.mall.service.TopicService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: TopicServiceImpl
 * @projectName dmd-master
 * @description: TODO
 * @date 2019/9/2910:46
 */
@Service
public class TopicServiceImpl implements TopicService{

    @Autowired
    private TopicMapper topicMapper;

    @Override
    public List<TopicBean> queryTopicPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return topicMapper.queryTopicPage();
    }

    @Override
    public List<TopicBean> queryTopicById(Integer id) {
        return topicMapper.queryTopicById(id);
    }

    @Override
    public List<TopicBean> queryTopic() {

        return topicMapper.queryTopic();
    }

    @Override
    public List<TopicBean> queryTopicName(String topicName) {
        return topicMapper.queryTopicName(topicName);
    }
}
