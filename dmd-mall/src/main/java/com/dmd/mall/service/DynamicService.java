package com.dmd.mall.service;

import com.dmd.mall.model.domain.DynamicBean;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: DynamicService
 * @projectName dmd-master
 * @description: TODO
 * @date 2019/9/239:43
 */
public interface DynamicService {
    List<DynamicBean> queryDynamic(Long userId);

    List<DynamicBean> queryDynamicPage(Long userId, Integer pageNum, Integer pageSize);

    Integer queryPraise(Long id);

    Integer queryComment(Long id);

    Integer queryShare(Long id);

    int updateLikePraise(Long id);

    int updateCancelPraise(Long id);

    int updateDynamicrShare(Long id);

    int updateDynamicDelflag(Long id);

    List<DynamicBean> queryTopicByDynamicTime(Integer id,Integer pageNum,Integer pageSize);

    List<DynamicBean> selectTopicByDynamicHeat(Integer id,Integer pageNum,Integer pageSize);

    List<DynamicBean> queryDynamicById(Long id);

    int addDynamic(DynamicBean dynamicBean);

    List<DynamicBean> queryDynamicTime(Integer pageNum,Integer pageSize);

    List<DynamicBean> queryDynamicHeat(Integer pageNum,Integer pageSize);

    Integer queryDynamicCount(Long userId);
}
