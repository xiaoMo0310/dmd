package com.dmd.mall.service.impl;

import com.dmd.base.dto.LoginAuthDto;
import com.dmd.base.enums.ErrorCodeEnum;
import com.dmd.core.support.BaseService;
import com.dmd.mall.exceptions.UmsBizException;
import com.dmd.mall.mapper.UmsFavoritesMapper;
import com.dmd.mall.model.domain.UmsFavorites;
import com.dmd.mall.service.UmsFavoritesService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * UmsFavoritesServiceImpl 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-09-23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UmsFavoritesServiceImpl extends BaseService<UmsFavorites> implements UmsFavoritesService {

    @Autowired
    private UmsFavoritesMapper umsFavoritesMapper;

    @Override
    public int saveAttentionMessage(LoginAuthDto loginAuthDto, UmsFavorites umsFavorites) {
        Preconditions.checkArgument(loginAuthDto.getUserId().equals(umsFavorites.getUserId()), ErrorCodeEnum.UMS10011003.msg());
        //查询是否关注
        if(checkAttention(loginAuthDto.getUserId(), umsFavorites.getTargetId(), umsFavorites.getFavoriteType())){
            throw new UmsBizException("已关注");
        }
        int resultInt;
        umsFavorites.setUpdateInfo(loginAuthDto);
        if (umsFavorites.isNew()) {
            resultInt = umsFavoritesMapper.insertSelective(umsFavorites);
        } else {
            resultInt = umsFavoritesMapper.updateByPrimaryKeySelective(umsFavorites);
        }
        return resultInt;
    }

    @Override
    public Boolean checkAttention(Long userId, Long targetId, Integer favoriteType) {
        UmsFavorites umsFavorites = new UmsFavorites();
        umsFavorites.setUserId(userId);
        umsFavorites.setTargetId(targetId);
        umsFavorites.setFavoriteType(favoriteType);
        umsFavorites.setStatus(1);
        int count = umsFavoritesMapper.selectCount(umsFavorites);
        return count > 0;
    }
}
