package com.dmd.mall.service.impl;

import com.dmd.mall.mapper.*;
import com.dmd.mall.model.domain.*;
import com.dmd.mall.model.vo.PmsCertificateVo;
import com.dmd.mall.service.HomeSearchService;
import com.dmd.mall.service.PmsCourseProductService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private PmsCourseProductService courseProductService;

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
        List<DynamicBean> dynamicBeanList = dynamicMapper.queryDynamicByContent(content);
        for (int i = 0; i < dynamicBeanList.size(); i++) {
            Long userId1 = dynamicBeanList.get(i).getUserId();
            Integer biaoshifu =  dynamicMapper.selectFavorites(userId,userId1);
            for (int j = 0; j <dynamicBeanList.size() ; j++) {
                dynamicBeanList.get(i).setIdentification(biaoshifu);
            }
        }

        for (int i = 0; i < dynamicBeanList.size(); i++) {
            Long dynamicId = dynamicBeanList.get(i).getId();
            Integer biaoshifuPraise =  dynamicMapper.selectFavoritespraiseTopic(userId,dynamicId);
            for (int j = 0; j <dynamicBeanList.size() ; j++) {
                dynamicBeanList.get(i).setIdentificationPraise(biaoshifuPraise);
            }
        }
        return dynamicBeanList;
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
    public List<PmsCertificateVo> queryPmsCertificate(Long userId, String content, Integer searchType, Integer pageNum, Integer pageSize) {
        List<PmsCertificate> pmsCertificates = pmsCertificateMapper.queryPmsCertificate(content);
        PageHelper.startPage(pageNum, pageSize);
        homeSearchMapper.addHomeSearchRecord(userId,content,searchType);
        return pmsCertificates.stream().map(pmsCertificate -> {
            PmsCertificateVo pmsCertificateVo = new PmsCertificateVo();
            BeanUtils.copyProperties(pmsCertificate, pmsCertificateVo);
            //查询是否有商品信息
            long count = courseProductService.findCertificateProductNum(1, pmsCertificate.getId());
            pmsCertificateVo.setProductNum(count);
            return pmsCertificateVo;
        }).collect(Collectors.toList());
    }

    @Override
    public List<PmsCertificateVo> queryPmsCertificateCount(Long userId, String content, Integer searchType, Integer pageNum, Integer pageSize) {
        List<PmsCertificate> pmsCertificates = pmsCertificateMapper.queryPmsCertificate(content);
        return pmsCertificates.stream().map(pmsCertificate -> {
            PmsCertificateVo pmsCertificateVo = new PmsCertificateVo();
            BeanUtils.copyProperties(pmsCertificate, pmsCertificateVo);
            //查询是否有商品信息
            long count = courseProductService.findCertificateProductNum(1, pmsCertificate.getId());
            pmsCertificateVo.setProductNum(count);
            return pmsCertificateVo;
        }).collect(Collectors.toList());
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
