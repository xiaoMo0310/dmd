package com.dmd.mall.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.BaseService;
import com.dmd.mall.exceptions.UmsBizException;
import com.dmd.mall.mapper.UmsFavoritesMapper;
import com.dmd.mall.model.domain.TopicBean;
import com.dmd.mall.model.domain.UmsCoach;
import com.dmd.mall.model.domain.UmsFavorites;
import com.dmd.mall.model.domain.UmsMember;
import com.dmd.mall.model.dto.UmsFavoritesDto;
import com.dmd.mall.model.vo.UmsFavoritesVo;
import com.dmd.mall.service.TopicService;
import com.dmd.mall.service.UmsCoachService;
import com.dmd.mall.service.UmsFavoritesService;
import com.dmd.mall.service.UmsMemberService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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
    @Autowired
    private TopicService topicService;
    @Override
    public int saveAttentionMessage(LoginAuthDto loginAuthDto, UmsFavoritesDto umsFavoritesDto) {
        UmsFavorites umsFavorites = new UmsFavorites();
        BeanUtils.copyProperties(umsFavoritesDto, umsFavorites);
        umsFavorites.setUpdateInfo(loginAuthDto);
        umsFavorites.setUserId(loginAuthDto.getUserId());
        if(loginAuthDto.getUserType().equals("member")){
            umsFavorites.setUserType(1);
            if(umsFavoritesDto.getFavoriteType() == 1 && loginAuthDto.getUserId() == umsFavoritesDto.getTargetId()){
                throw new UmsBizException("不允许关注自己");
            }
        }else if(loginAuthDto.getUserType().equals("coach")){
            umsFavorites.setUserType(2);
            if(umsFavoritesDto.getFavoriteType() == 2 && loginAuthDto.getUserId() == umsFavoritesDto.getTargetId()){
                throw new UmsBizException("不允许关注自己");
            }
        }
        //查询关注的信息
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
        if(loginAuthDto.getUserType().equals("member")){
            umsFavorites.setUserType(1);
        }else if(loginAuthDto.getUserType().equals("coach")){
            umsFavorites.setUserType(2);
        }
        int resultInt = umsFavoritesMapper.updateAttentionStatus(loginAuthDto.getUserId(), loginAuthDto.getUserName(), umsFavorites);
        return resultInt;
    }

    @Override
    public Boolean checkAttention(LoginAuthDto loginAuthDto, Long targetId, Integer favoriteType) {
        UmsFavorites umsFavorites = new UmsFavorites();
        umsFavorites.setUserId(loginAuthDto.getUserId());
        if(loginAuthDto.getUserType().equals("member")){
            umsFavorites.setUserType(1);
        }else if(loginAuthDto.getUserType().equals("coach")){
            umsFavorites.setUserType(2);
        }
        umsFavorites.setTargetId(targetId);
        umsFavorites.setFavoriteType(favoriteType);
        umsFavorites.setStatus(1);
        int count = umsFavoritesMapper.selectCount(umsFavorites);
        return count > 0;
    }

    @Override
    public JSONObject queryAttention(LoginAuthDto loginAuthDto, Integer pageNum, Integer pageSize, Integer favoriteType) {
        PageHelper.startPage(pageNum, pageSize);
        List<UmsFavorites> umsFavorites = null;
        if(favoriteType == 1){
            //查询普通用户和教练的
            if(loginAuthDto.getUserType().equals("member")){
                umsFavorites = umsFavoritesMapper.queryAttentionUser(loginAuthDto.getUserId(), 1);
            }else if(loginAuthDto.getUserType().equals("coach")){
                umsFavorites = umsFavoritesMapper.queryAttentionUser(loginAuthDto.getUserId(), 2);
            }
        }else if(favoriteType == 2){
            //查询话题
            if(loginAuthDto.getUserType().equals("member")){
                umsFavorites = umsFavoritesMapper.queryAttentionTopic(loginAuthDto.getUserId(), 1);
            }else if(loginAuthDto.getUserType().equals("coach")){
                umsFavorites = umsFavoritesMapper.queryAttentionTopic(loginAuthDto.getUserId(), 2);
            }
        }
        PageInfo<UmsFavorites> favoritesPageInfo = new PageInfo<>(umsFavorites);
        List<UmsFavoritesVo> umsFavoritesVos = favoritesPageInfo.getList().stream().map(favoritePageInfo -> {
            UmsFavoritesVo umsFavoritesVo = new UmsFavoritesVo();
            //封装普通用户信息
            if (favoritePageInfo.getFavoriteType() == 1) {
                UmsMember umsMember = umsMemberService.getById(favoritePageInfo.getTargetId());
                Optional.ofNullable(umsMember).orElseThrow(() ->new UmsBizException("未查询到关注的用户信息"));
                BeanUtils.copyProperties(umsMember, umsFavoritesVo);
                BeanUtils.copyProperties(favoritePageInfo, umsFavoritesVo);
                umsFavoritesVo.setUserId(umsMember.getId());
            }
            //封装教练用户信息
            if (favoritePageInfo.getFavoriteType() == 2) {
                UmsCoach umsCoach = umsCoachService.selectByKey(favoritePageInfo.getTargetId());
                Optional.ofNullable(umsCoach).orElseThrow(() ->new UmsBizException("未查询到关注的教练信息"));
                BeanUtils.copyProperties(umsCoach, umsFavoritesVo);
                BeanUtils.copyProperties(favoritePageInfo, umsFavoritesVo);
                umsFavoritesVo.setNickname(umsCoach.getNickName());
            }
            //封装话题信息
            if (favoritePageInfo.getFavoriteType() == 6) {
                List<TopicBean> topicBeans = topicService.queryTopicById(Integer.valueOf(favoritePageInfo.getTargetId() + ""));
                if(!CollectionUtils.isEmpty(topicBeans)){
                    TopicBean topicBean = topicBeans.get(0);
                    umsFavoritesVo.setTopicName(topicBean.getTopicName());
                    umsFavoritesVo.setTopicNum(topicBean.getTopicNum());
                    umsFavoritesVo.setTopicPicture(topicBean.getTopicPicture());
                    BeanUtils.copyProperties(favoritePageInfo, umsFavoritesVo);
                }else {
                    throw new UmsBizException("未查询到关注的话题信息");
                }
            }
            return umsFavoritesVo;
        }).collect(Collectors.toList());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list", umsFavoritesVos);
        jsonObject.put("total", favoritesPageInfo.getTotal());
        return jsonObject;
    }

    @Override
    public Integer queryFavoritesCount(Long userId, LoginAuthDto loginAuthDto) {
        int num = 0;
        //登陆角色
        if(loginAuthDto.getUserType().equals("member")){
            num = umsFavoritesMapper.queryFavoritesCount(userId);
        }else if(loginAuthDto.getUserType().equals("coach")){
            num = umsFavoritesMapper.queryFavoritesCountByCoach(userId);
        }
        return num;
    }
}
