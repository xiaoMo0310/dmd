package com.dmd.mall.service.impl;

import com.dmd.mall.mapper.*;
import com.dmd.mall.model.domain.*;
import com.dmd.mall.service.HomeSearchService;
import com.github.pagehelper.PageHelper;
import org.checkerframework.checker.units.qual.A;
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

    @Autowired
    private PmsCertificateMapper pmsCertificateMapper;

    @Autowired
    private PmsCourseProductMapper pmsCourseProductMapper;
    @Override
    public List<DynamicBean> queryDynamic(Long userId, String content, Integer searchType,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        homeSearchMapper.addHomeSearchRecord(userId,content,searchType);
        return dynamicMapper.queryDynamicByContent(content);
    }

    @Override
    public List<PmsProduct> queryPmsProduct(Long userId, String content, Integer searchType,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        homeSearchMapper.addHomeSearchRecord(userId,content,searchType);
        return pmsProductMapper.queryPmsProductByContent(content);
    }

    @Override
    public List<TopicBean> queryTopic(Long userId, String content, Integer searchType,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        homeSearchMapper.addHomeSearchRecord(userId,content,searchType);
        return topicMapper.queryTopicByContent(content);
    }

    @Override
    public List<HomeSearchRecordBean> queryHistory(Long userId) {
        return homeSearchMapper.queryHistory(userId);
    }

    @Override
    public int deleteHistoryByUserid(Long userid) {
        return homeSearchMapper.deleteHistoryByUserid(userid);
    }

    @Override
    public List<PmsCourseProduct> queryPmsCourseProduct(Long userId, String content, Integer searchType, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        homeSearchMapper.addHomeSearchRecord(userId,content,searchType);
        return pmsCourseProductMapper.queryPmsCourseProduct(content);
    }

    @Override
    public List<DynamicBean> queryDynamicContent(Long userId, String content, Integer searchType, Integer pageNum, Integer pageSize) {
        return dynamicMapper.queryDynamicByContent(content);
    }

    @Override
    public List<PmsCourseProduct> queryPmsCourseProductContent(Long userId, String content, Integer searchType, Integer pageNum, Integer pageSize) {
        return pmsCourseProductMapper.queryPmsCourseProduct(content);
    }

    @Override
    public List<TopicBean> queryTopicContent(Long userId, String content, Integer searchType, Integer pageNum, Integer pageSize) {
        return topicMapper.queryTopicByContent(content);
    }

    @Override
    public List<PmsCertificate> queryPmsCertificate(Long userId, String content, Integer searchType, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        homeSearchMapper.addHomeSearchRecord(userId,content,searchType);
        return pmsCertificateMapper.queryPmsCertificate(content);
    }

    /*@Override
    public List<PmsCourseProduct> queryPmsCourseProductByType(Long userId, String content, Integer searchType, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        homeSearchMapper.addHomeSearchRecord(userId,content,searchType);
        return pmsCourseProductMapper.queryPmsCourseProductByType(content);
    }

    @Override
    public List<PmsCourseProduct> queryPmsCourseProductByTypeContent(Long userId, String content, Integer searchType, Integer pageNum, Integer pageSize) {
        return pmsCourseProductMapper.queryPmsCourseProductByType(content);
    }*/


}
