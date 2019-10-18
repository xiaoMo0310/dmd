package com.dmd.mall.service.impl;

import com.dmd.mall.mapper.DynamicMapper;
import com.dmd.mall.mapper.HomeSearchMapper;
import com.dmd.mall.mapper.PmsProductMapper;
import com.dmd.mall.mapper.TopicMapper;
import com.dmd.mall.model.domain.DynamicBean;
import com.dmd.mall.model.domain.HomeSearchRecordBean;
import com.dmd.mall.model.domain.PmsProduct;
import com.dmd.mall.model.domain.TopicBean;
import com.dmd.mall.service.HomeSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: HomeSearchServiceImpl
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/10/1818:17
 */
@Service
public class HomeSearchServiceImpl implements HomeSearchService{

    @Autowired
    private HomeSearchMapper homeSearchMapper;

    @Autowired
    private DynamicMapper dynamicMapper;

    @Autowired
    private PmsProductMapper pmsProductMapper;

    @Autowired
    private TopicMapper topicMapper;

    @Override
    public List<DynamicBean> queryDynamic(Long userId, String content, Integer searchType) {
        homeSearchMapper.addHomeSearchRecord(userId,content,searchType);
        return dynamicMapper.queryDynamicByContent(content);
    }

    @Override
    public List<PmsProduct> queryPmsProduct(Long userId, String content, Integer searchType) {
        homeSearchMapper.addHomeSearchRecord(userId,content,searchType);
        return pmsProductMapper.queryPmsProductByContent(content);
    }

    @Override
    public List<TopicBean> queryTopic(Long userId, String content, Integer searchType) {
        homeSearchMapper.addHomeSearchRecord(userId,content,searchType);
        return topicMapper.queryTopicByContent(content);
    }

    @Override
    public List<HomeSearchRecordBean> queryHistory(Long userId) {
        return homeSearchMapper.queryHistory(userId);
    }
}
