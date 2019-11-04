package com.dmd.mall.service;

import com.dmd.mall.model.domain.DynamicBean;
import com.dmd.mall.model.domain.HomeSearchRecordBean;
import com.dmd.mall.model.domain.PmsProduct;
import com.dmd.mall.model.domain.TopicBean;

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
}
