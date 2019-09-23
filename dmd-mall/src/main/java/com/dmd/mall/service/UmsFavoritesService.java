package com.dmd.mall.service;

import com.dmd.base.dto.LoginAuthDto;
import com.dmd.mall.model.domain.UmsFavorites;
import com.dmd.core.support.IService;

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
     * @param umsFavorites
     * @return
     */
    int saveAttentionMessage(LoginAuthDto loginAuthDto, UmsFavorites umsFavorites);

    /**
     * 判断用户时候关注用户 商品 商铺
     * @param userId
     * @param targetId
     * @return
     */
    Boolean checkAttention(Long userId,Long targetId);
}
