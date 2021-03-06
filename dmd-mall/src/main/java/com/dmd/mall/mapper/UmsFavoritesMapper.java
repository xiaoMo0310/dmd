package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.UmsFavorites;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * UmsFavoritesMapper Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-09-23
 */
@Mapper
@Component
public interface UmsFavoritesMapper extends MyMapper<UmsFavorites> {

    List<UmsFavorites> queryAttentionUser(@Param("userId") Long userId, @Param("userType") Integer userType);

    int updateAttentionStatus(@Param("userId") Long userId, @Param("userName") String userName,@Param("umsFavorites") UmsFavorites umsFavorites);

    UmsFavorites selectAttentionMessage(UmsFavorites umsFavorites);

    Integer queryFavoritesCount(Long userId);

    List<UmsFavorites> queryAttentionTopic(@Param("userId") Long userId, @Param("userType") Integer userType);

    Integer queryFavoritesCountByCoach(Long userId);
}
