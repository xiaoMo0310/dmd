package com.dmd.mall.service;

import com.dmd.mall.model.domain.DynamicAlbumTimeBean;
import com.dmd.mall.model.domain.DynamicBean;
import com.dmd.mall.model.vo.UserDetailsVo;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: DynamicService
 * @projectName dmd-master
 * @description:
 * @date 2019/9/239:43
 */
public interface DynamicService {
    List<DynamicBean> queryDynamic(Long userId);

    List<DynamicBean> queryDynamicPage(Long userId, Integer pageNum, Integer pageSize,Integer userType);

    Integer queryPraise(Long id);

    Integer queryComment(Long id);

    Integer queryShare(Long id);

    int updateLikePraise(Long id,Integer userType);

    int updateCancelPraise(Long id,Integer userType);

    int updateDynamicrShare(Long id);

    int updateDynamicDelflag(Long id);

    List<DynamicBean> queryTopicByDynamicTime(Integer id,Integer pageNum,Integer pageSize);

    List<DynamicBean> selectTopicByDynamicHeat(Integer id,Integer pageNum,Integer pageSize);

    List<DynamicBean> queryDynamicById(Long id,Integer userType);

    int addDynamic(DynamicBean dynamicBean);

    List<DynamicBean> queryDynamicTime(Integer pageNum,Integer pageSize);

    List<DynamicBean> queryDynamicHeat(Integer pageNum,Integer pageSize);

    Integer queryDynamicCount(Long userId);

    List<DynamicAlbumTimeBean> queryDynamicAlbumTimeBean(Long userId, Integer pageNum, Integer pageSize,Integer userType);

    UserDetailsVo queryUserDetails(Long userId,Integer userType);
}
