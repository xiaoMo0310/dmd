package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.DynamicAlbumTimeBean;
import com.dmd.mall.model.domain.DynamicBean;
import com.dmd.mall.model.vo.UserDetailsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: DynamicMapper
 * @projectName dmd-master
 * @description:
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

    List<DynamicAlbumTimeBean> queryDynamicAlbumTimeBean(Long userId);

    List<DynamicAlbumTimeBean> queryDynamicAlbumTimePicture(@Param("months")String months,@Param("userId") Long userId);

    UserDetailsVo queryUserDetails(Long userId);

    List<DynamicBean> queryDynamicCoach(Long userId2);

    Integer selectFavoritespraiseCoach(@Param("userId")Long userId,@Param("id") Long id);

    Integer selectFavoritesCoach(@Param("userId")Long userId,@Param("userId1")Long userId1);


    List<DynamicBean> queryDynamicCoachById(Long id);

    Integer selectFavoritesByCoach(@Param("userId")Long userId,@Param("userId1")Long userId1);

    /**
     * 用户发布教练登陆
     * @param userId
     * @param id
     * @return
     */
    Integer selectFavoritespraiseByCoach(@Param("userId")Long userId,@Param("id") Long id);

    Integer selectFavoritespraiseCoach2(@Param("userId")Long userId,@Param("id") Long id);

    List<DynamicBean> queryDynamicByCoachById(Long id);

    Integer selectFavoritesCoach2(@Param("userId")Long userId,@Param("userId1") Long userId1);

    Integer queryDynamicCountByCoach(Long userId);

    Integer selectUserIdBypraiseCoach(@Param("id")Long id,@Param("userId")Long userId);

    void insertUserPraiseCoach(@Param("dynamicId")Long id,@Param("userId")Long userId);

    void updateUserPraiseLikeCoach(@Param("id")Long id,@Param("userId") Long userId);

    Integer selectUserIdBypraiseByCoach(@Param("id")Long id,@Param("userId")Long userId);

    void insertUserPraiseByCoach(@Param("dynamicId")Long id,@Param("userId")Long userId);

    void updateUserPraiseLikeByCoach(@Param("id")Long id,@Param("userId") Long userId);

    Integer selectUserIdBypraiseCoach2(@Param("id")Long id,@Param("userId")Long userId);

    void insertUserPraiseCoach2(@Param("dynamicId")Long id,@Param("userId")Long userId);

    void updateUserPraiseLikeCoach2(@Param("id")Long id,@Param("userId") Long userId);

    void updateUserPraiseCoach(@Param("dynamicId")Long id,@Param("userId") Long userId);

    void updateUserPraise2(@Param("dynamicId")Long id,@Param("userId") Long userId);

    void updateUserPraiseCoach2(@Param("dynamicId")Long id,@Param("userId") Long userId);

    List<DynamicAlbumTimeBean> queryDynamicAlbumTimeByCoachBean(Long userId);

    List<DynamicAlbumTimeBean> queryDynamicAlbumTimePictureByCoach(@Param("months")String months,@Param("userId") Long userId);

    UserDetailsVo queryUserDetailsByCoach(Long userId);

    List<DynamicBean> queryTopicByDynamicForCoachTime(Integer id);

    List<DynamicBean> selectTopicByDynamicByCoachHeat(Integer id);

    List<DynamicBean> queryDynamicByContentByCoach(@Param("content")String content);

    List<DynamicBean> queryDynamicByIdDelflag(Long id);
}
