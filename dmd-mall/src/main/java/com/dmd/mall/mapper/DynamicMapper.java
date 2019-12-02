package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.DynamicBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: DynamicMapper
 * @projectName dmd-master
 * @description: TODO
 * @date 2019/9/239:44
 */
public interface DynamicMapper {
    List<DynamicBean> queryDynamic(Long userId);

    Integer queryPraise(Long id);

    Integer queryShare(Long id);

    int updateLikePraise(Long id);

    int updateCancelPraise(Long id);

    int updateDynamicrShare(Long id);

    int updateDynamicDelflag(Long id);

    List<DynamicBean> queryTopicByDynamicTime(Integer id);

    List<DynamicBean> selectTopicByDynamicHeat(Integer id);

    List<DynamicBean> queryDynamicById(Long id);

    int addDynamic(DynamicBean dynamicBean);

    int addrCommentNum(Long forDynamicId);

    int reduceCommentNum(Long dynamicId);

    List<DynamicBean> queryDynamicTime();

    List<DynamicBean> queryDynamicHeat();

    List<DynamicBean> queryDynamicByContent(@Param("content") String content);

    Integer queryDynamicCount(Long userId);

    Integer selectFavorites(@Param("userId")Long userId,@Param("userId1")Long userId1);

    void insertUserPraise(@Param("dynamicId")Long id,@Param("userId")Long userId);

    void updateUserPraise(@Param("dynamicId")Long id,@Param("userId") Long userId);

    Integer selectFavoritespraise(@Param("userId")Long userId,@Param("id") Long id);

    Integer selectFavoritespraiseTopic(@Param("userId")Long userId,@Param("dynamicId") Long dynamicId);

    Integer selectUserIdBypraise(@Param("id")Long id,@Param("userId")Long userId);

    void updateUserPraiseLike(@Param("id")Long id,@Param("userId") Long userId);

    DynamicBean selectDynamicById(Long id);
}
