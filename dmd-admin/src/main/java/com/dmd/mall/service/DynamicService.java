package com.dmd.mall.service;

import com.dmd.mall.model.domain.DynamicBean;
import com.dmd.mall.model.domain.TopicBean;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: DynamicService
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/10/1716:38
 */
public interface DynamicService {

    List<DynamicBean> queryDynamicPage(Integer pageNum, Integer pageSize, DynamicBean dynamicBean);

    List<TopicBean> queryTopic();
}
