package com.dmd.admin.service;

import com.dmd.admin.model.domain.DynamicBean;
import com.dmd.admin.model.domain.TopicBean;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: DynamicService
 * @projectName dmd-masters
 * @description:
 * @date 2019/10/1716:38
 */
public interface DynamicService {

    List<DynamicBean> queryDynamicPage(Integer pageNum, Integer pageSize, DynamicBean dynamicBean);

    List<TopicBean> queryTopic();

    int updateDynamicDelflagById(List<Long> ids);

    DynamicBean selectDynamicById(Long id,Integer userType);
}
