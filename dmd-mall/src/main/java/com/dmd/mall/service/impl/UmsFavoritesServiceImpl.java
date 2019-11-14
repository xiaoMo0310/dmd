package com.dmd.mall.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dmd.base.dto.BaseQuery;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.base.enums.ErrorCodeEnum;
import com.dmd.core.support.BaseService;
import com.dmd.mall.exceptions.UmsBizException;
import com.dmd.mall.mapper.UmsFavoritesMapper;
import com.dmd.mall.model.domain.UmsCoach;
import com.dmd.mall.model.domain.UmsFavorites;
import com.dmd.mall.model.domain.UmsMember;
import com.dmd.mall.model.dto.UmsFavoritesDto;
import com.dmd.mall.model.vo.UmsFavoritesVo;
import com.dmd.mall.service.UmsCoachService;
import com.dmd.mall.service.UmsFavoritesService;
import com.dmd.mall.service.UmsMemberService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

    @Autowired
    private UmsMemberService umsMemberService;

    @Autowired
    private UmsCoachService umsCoachService;
    @Override
    public int saveAttentionMessage(LoginAuthDto loginAuthDto, UmsFavoritesDto umsFavoritesDto) {
        UmsFavorites umsFavorites = new UmsFavorites();
        BeanUtils.copyProperties(umsFavoritesDto, umsFavorites);
        umsFavorites.setUpdateInfo(loginAuthDto);
        umsFavorites.setUserId(loginAuthDto.getUserId());
        //todo 教练类型待完善
        umsFavorites.setUserType(1);
        //查询是否关注
        if(checkAttention(loginAuthDto, umsFavorites.getTargetId(), umsFavorites.getFavoriteType())){
            throw new UmsBizException("已关注");
        }
        //查询是否有关注的信息
        UmsFavorites favorites = umsFavoritesMapper.selectAttentionMessage(umsFavorites);
        int resultInt = 0;
        if(favorites == null){
            resultInt = umsFavoritesMapper.insertSelective(umsFavorites);
        }else {
            resultInt = this.updateAttentionStatus(loginAuthDto, umsFavoritesDto);
        }

        return resultInt;
    }

    @Override
    public int updateAttentionStatus(LoginAuthDto loginAuthDto, UmsFavoritesDto umsFavoritesDto) {
        UmsFavorites umsFavorites = new UmsFavorites();
        BeanUtils.copyProperties(umsFavoritesDto, umsFavorites);
        umsFavorites.setUpdateInfo(loginAuthDto);
        umsFavorites.setUserId(loginAuthDto.getUserId());
        //todo 教练类型待完善
        umsFavorites.setUserType(1);
        int resultInt = umsFavoritesMapper.updateAttentionStatus(loginAuthDto.getUserId(), loginAuthDto.getUserName(), umsFavorites);
        return resultInt;
    }

    @Override
    public Boolean checkAttention(LoginAuthDto loginAuthDto, Long targetId, Integer favoriteType) {
        UmsFavorites umsFavorites = new UmsFavorites();
        umsFavorites.setUserId(loginAuthDto.getUserId());
        //todo 教练类型待完善
        umsFavorites.setUserType(1);
        umsFavorites.setTargetId(targetId);
        umsFavorites.setFavoriteType(favoriteType);
        umsFavorites.setStatus(1);
        int count = umsFavoritesMapper.selectCount(umsFavorites);
        return count > 0;
    }

    @Override
    public JSONObject queryAttention(Long userId, BaseQuery baseQuery) {
        PageHelper.startPage(baseQuery.getPageNum(), baseQuery.getPageSize());
        List<UmsFavorites> umsFavorites = umsFavoritesMapper.queryAttention(userId);
        PageInfo<UmsFavorites> favoritesPageInfo = new PageInfo<>(umsFavorites);
        List<UmsFavoritesVo> umsFavoritesVos = favoritesPageInfo.getList().stream().map(favoritePageInfo -> {
            UmsFavoritesVo umsFavoritesVo = new UmsFavoritesVo();
            BeanUtils.copyProperties(favoritePageInfo, umsFavoritesVo);
            if (favoritePageInfo.getFavoriteType() == 1) {
                UmsMember umsMember = umsMemberService.getById(favoritePageInfo.getTargetId());
                BeanUtils.copyProperties(umsMember, umsFavoritesVo);
            }
            if (favoritePageInfo.getFavoriteType() == 2) {
                UmsCoach umsCoach = umsCoachService.selectByKey(favoritePageInfo.getTargetId());
                BeanUtils.copyProperties(umsCoach, umsFavoritesVo);
            }
            return umsFavoritesVo;
        }).collect(Collectors.toList());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list", umsFavoritesVos);
        jsonObject.put("total", favoritesPageInfo.getTotal());
        return jsonObject;
    }


}
