package com.dmd.mall.service;

import com.alibaba.fastjson.JSONObject;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.IService;
import com.dmd.mall.model.domain.UmsFavorites;
import com.dmd.mall.model.dto.UmsFavoritesDto;

/**
 * <p>
 * UmsFavoritesService 服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-09-23
 */
public interface UmsFavoritesService extends IService<UmsFavorites> {

    /**
     * 编辑用户关注的信息
     * @param loginAuthDto
     * @param umsFavoritesDto
     * @return
     */
    int saveAttentionMessage(LoginAuthDto loginAuthDto,  UmsFavoritesDto umsFavoritesDto);

    /**
     * 修改用户的关注状态
     * @param loginAuthDto
     * @param umsFavoritesDto
     * @return
     */
    int updateAttentionStatus(LoginAuthDto loginAuthDto, UmsFavoritesDto umsFavoritesDto);

    /**
     * 判断用户时候关注用户 商品 商铺
     * @param userId
     * @param targetId
     * @return
     */
    Boolean checkAttention(LoginAuthDto loginAuthDto, Long targetId, Integer favoriteType);

    JSONObject queryAttention(Long userId, Integer pageNum, Integer pageSize, Integer favoriteType);

    Integer queryFavoritesCount(Long userId);
}
