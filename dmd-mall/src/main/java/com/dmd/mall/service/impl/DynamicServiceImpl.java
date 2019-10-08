package com.dmd.mall.service.impl;

import com.dmd.mall.mapper.CommentMapper;
import com.dmd.mall.mapper.DynamicMapper;
import com.dmd.mall.mapper.TopicMapper;
import com.dmd.mall.model.domain.DynamicBean;
import com.dmd.mall.service.DynamicService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ChenYanbing
 * @title: DynamicServiceImpl
 * @projectName dmd-master
 * @description: TODO
 * @date 2019/9/239:43
 */
@Service
public class DynamicServiceImpl implements DynamicService{

    @Autowired
    private DynamicMapper dynamicMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private TopicMapper topicMapper;

    @Override
    public List<DynamicBean> queryDynamic(Long userId) {
        return dynamicMapper.queryDynamic(userId);
    }

    @Override
    public List<DynamicBean> queryDynamicPage(Long userId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return dynamicMapper.queryDynamic(userId);
    }

    @Override
    public Integer queryPraise(Long id) {
        return dynamicMapper.queryPraise(id);
    }

    @Override
    public Integer queryComment(Long id) {
        return commentMapper.queryComment(id);
    }

    @Override
    public Integer queryShare(Long id) {
        return dynamicMapper.queryShare(id);
    }

    @Override
    public int updateLikePraise(Long id) {
        return dynamicMapper.updateLikePraise(id);
    }

    @Override
    public int updateCancelPraise(Long id) {
        return dynamicMapper.updateCancelPraise(id);
    }

    @Override
    public int updateDynamicrShare(Long id) {
        return dynamicMapper.updateDynamicrShare(id);
    }

    @Override
    public int updateDynamicDelflag(Long id) {
        //动态删除时对应的动态下评论也全部删除
        commentMapper.updateComment(id);
        //动态删除时判断是否有话题id，如果有话题id则话题下动态数量减1
        List<DynamicBean> dynamicBeanList = dynamicMapper.queryDynamicById(id);
        if (dynamicBeanList.get(0).getTopicId() != null){
            topicMapper.reduceTopicNum(dynamicBeanList.get(0).getTopicId());
        }
        return dynamicMapper.updateDynamicDelflag(id);
    }

    @Override
    public List<DynamicBean> queryTopicByDynamicTime(Integer id) {
        return dynamicMapper.queryTopicByDynamicTime(id);
    }

    @Override
    public List<DynamicBean> selectTopicByDynamicHeat(Integer id) {
        return dynamicMapper.selectTopicByDynamicHeat(id);
    }

    @Override
    public List<DynamicBean> queryDynamicById(Long id) {
        return dynamicMapper.queryDynamicById(id);
    }

    @Override
    public int addDynamic(DynamicBean dynamicBean) {
        //判断用户是否选择话题类型,如果选择,话题下动态数量+1
        if (dynamicBean.getTopicId() != null){
            topicMapper.addTopicNum(dynamicBean.getTopicId());
        }
        dynamicBean.setCreateTime(new Date());
        dynamicBean.setDynamicPraise(0);
        dynamicBean.setDynamicSharenum(0);
        dynamicBean.setDynamicCommentnum(0);
        dynamicBean.setDelflag(0);
        return dynamicMapper.addDynamic(dynamicBean);
    }


}
