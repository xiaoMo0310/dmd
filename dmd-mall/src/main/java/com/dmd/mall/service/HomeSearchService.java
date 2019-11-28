package com.dmd.mall.service;

import com.dmd.mall.model.domain.*;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: HomeSearchService
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/10/1818:17
 */
public interface HomeSearchService {
    List<DynamicBean> queryDynamic(Long userId, String content, Integer searchType,Integer pageNum,Integer pageSize);

    List<PmsProduct> queryPmsProduct(Long userId, String content, Integer searchType,Integer pageNum,Integer pageSize);

    List<TopicBean> queryTopic(Long userId, String content, Integer searchType,Integer pageNum,Integer pageSize);

    List<HomeSearchRecordBean> queryHistory(Long userId);

    int deleteHistoryByUserid(Long userid);

    List<PmsCourseProduct> queryPmsCourseProduct(Long userId, String content, Integer searchType, Integer pageNum, Integer pageSize);

    List<DynamicBean> queryDynamicContent(Long userId, String content, Integer searchType, Integer pageNum, Integer pageSize);

    List<PmsCourseProduct> queryPmsCourseProductContent(Long userId, String content, Integer searchType, Integer pageNum, Integer pageSize);

    List<TopicBean> queryTopicContent(Long userId, String content, Integer searchType, Integer pageNum, Integer pageSize);

    /*List<PmsProduct> queryPmsCourseProductByType(Long userId, String content, Integer searchType, Integer pageNum, Integer pageSize);

    List<PmsProduct> queryPmsCourseProductByTypeContent(Long userId, String content, Integer searchType, Integer pageNum, Integer pageSize);*/
}
