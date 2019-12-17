package com.dmd.mall.service.impl;

import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.utils.RequestUtil;
import com.dmd.mall.mapper.TopicMapper;
import com.dmd.mall.model.domain.DynamicBean;
import com.dmd.mall.model.domain.TopicBean;
import com.dmd.mall.service.TopicService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYanbing
 * @title: TopicServiceImpl
 * @projectName dmd-master
 * @description: TODO
 * @date 2019/9/2910:46
 */
@Service
public class TopicServiceImpl implements TopicService{

    @Autowired
    private TopicMapper topicMapper;

    @Override
    public List<TopicBean> queryTopicPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return topicMapper.queryTopicPage();
    }

    @Override
    public List<TopicBean> queryTopicById(Integer id) {
        //登陆信息
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        //当前登录人
        Long userId = loginAuthDto.getUserId();
        //根据登陆类型查询动态详情1==普通用户 2==教练
        String userTypes = loginAuthDto.getUserType();
        //当前话题
        List<TopicBean> topicBeans = topicMapper.queryTopicById(id);
        //当前话题Id
        Integer topicId = topicBeans.get(0).getId();
        //当前登录人角色是用户
        if(userTypes.equals("member")){
            Integer biaoshifu =  topicMapper.selectFavorites(userId,topicId);
            if (biaoshifu == 0){
                topicBeans.get(0).setIdentification(0);
            }if(biaoshifu !=0 ){
                topicBeans.get(0).setIdentification(1);
            }
        }else if(userTypes.equals("coach")){
            Integer biaoshifu =  topicMapper.selectFavoritesByCoach(userId,topicId);
            if (biaoshifu == 0){
                topicBeans.get(0).setIdentification(0);
            }if(biaoshifu !=0 ){
                topicBeans.get(0).setIdentification(1);
            }
        }
        return topicBeans;
    }

    @Override
    public List<TopicBean> queryTopic() {

        return topicMapper.queryTopic();
    }

    @Override
    public List<TopicBean> queryTopicName(String topicName) {
        return topicMapper.queryTopicName(topicName);
    }
}
