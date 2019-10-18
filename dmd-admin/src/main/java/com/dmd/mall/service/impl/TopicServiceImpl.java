package com.dmd.mall.service.impl;

import com.dmd.mall.mapper.TopicAdminMapper;
import com.dmd.mall.model.domain.TopicBean;
import com.dmd.mall.service.TopicService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author ChenYanbing
 * @title: TopicServiceImpl
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/10/179:57
 */
@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicAdminMapper topicMapper;

    @Override
    public List<TopicBean> queryTopicPage(Integer pageNum, Integer pageSize, TopicBean topicBean) {
        PageHelper.startPage(pageNum, pageSize);
        return topicMapper.queryTopicPage(topicBean);
    }

    @Override
    public int addTopic(TopicBean topicBean) {
        //动态下数量默认为0
        topicBean.setTopicNum(0);
        //发布时间为当前时间
        topicBean.setCreateTime(new Date());
        return topicMapper.addTopic(topicBean);
    }

    @Override
    public int updateTopicById(TopicBean topicBean) {
        return topicMapper.updateTopicById(topicBean);
    }

    @Override
    public TopicBean findTopicInfoById(Long id) {
        return topicMapper.findTopicInfoById(id);
    }

    @Override
    public int deleteTopicById(String[] ids) {
        return topicMapper.deleteTopicById(ids);
    }

}
