package com.dmd.mall.service.impl;

import com.dmd.mall.mapper.DynamicAmdinMappper;
import com.dmd.mall.mapper.TopicAdminMapper;
import com.dmd.mall.model.domain.DynamicBean;
import com.dmd.mall.model.domain.TopicBean;
import com.dmd.mall.service.DynamicService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: DynamicServiceImpl
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/10/1716:39
 */
@Service
public class DynamicServiceImpl implements DynamicService{

    @Autowired
    private DynamicAmdinMappper dynamicAmdinMappper;

    @Autowired
    private TopicAdminMapper topicAdminMapper;

    @Override
    public List<DynamicBean> queryDynamicPage(Integer pageNum, Integer pageSize, DynamicBean dynamicBean) {
        PageHelper.startPage(pageNum, pageSize);
        return dynamicAmdinMappper.queryDynamicPage(dynamicBean);
    }

    @Override
    public List<TopicBean> queryTopic() {
        return topicAdminMapper.queryTopic();
    }
}
