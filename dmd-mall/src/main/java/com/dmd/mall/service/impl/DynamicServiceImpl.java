package com.dmd.mall.service.impl;

import com.dmd.WordFilter;
import com.dmd.core.utils.RequestUtil;
import com.dmd.mall.mapper.CommentMapper;
import com.dmd.mall.mapper.DynamicMapper;
import com.dmd.mall.mapper.TopicMapper;
import com.dmd.mall.model.domain.DynamicBean;
import com.dmd.mall.service.DynamicService;
import com.github.pagehelper.PageHelper;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
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
    public List<DynamicBean> queryTopicByDynamicTime(Integer id,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //当前登录人ID
        Long userId = RequestUtil.getLoginUser().getUserId();
        System.out.println(userId);
        //当前发布动态用户ID
        List<DynamicBean> dynamicBeanList = dynamicMapper.queryTopicByDynamicTime(id);
        for (int i = 0; i < dynamicBeanList.size(); i++) {
            Long userId1 = dynamicBeanList.get(i).getUserId();
            Integer biaoshifu =  dynamicMapper.selectFavorites(userId,userId1);
            System.out.println(biaoshifu+"====================================");
                for (int j = 0; j <dynamicBeanList.size() ; j++) {
                    dynamicBeanList.get(i).setIdentification(biaoshifu);
            }
        }
        //System.out.println(biaoshifu);

        return dynamicBeanList;
    }

    @Override
    public List<DynamicBean> selectTopicByDynamicHeat(Integer id,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //当前登录人ID
        Long userId = RequestUtil.getLoginUser().getUserId();
        System.out.println(userId);
        //当前发布动态用户ID
        List<DynamicBean> dynamicBeanList = dynamicMapper.queryTopicByDynamicTime(id);
        for (int i = 0; i < dynamicBeanList.size(); i++) {
            Long userId1 = dynamicBeanList.get(i).getUserId();
            Integer biaoshifu =  dynamicMapper.selectFavorites(userId,userId1);
            System.out.println(biaoshifu+"====================================");
            for (int j = 0; j <dynamicBeanList.size() ; j++) {
                dynamicBeanList.get(i).setIdentification(biaoshifu);
            }
        }
        //System.out.println(biaoshifu);

        return dynamicBeanList;
    }

    @Override
    public List<DynamicBean> queryDynamicById(Long id) {
        //当前登录人ID
        Long userId = RequestUtil.getLoginUser().getUserId();
        System.out.println(userId);
        //当前发布动态用户ID
        List<DynamicBean> dynamicBeanList = dynamicMapper.queryDynamicById(id);
            Long userId1 = dynamicBeanList.get(0).getUserId();
            Integer biaoshifu =  dynamicMapper.selectFavorites(userId,userId1);
            if (biaoshifu == 0){

                    dynamicBeanList.get(0).setIdentification(0);

            }if(biaoshifu !=0 ){
                for (int j = 0; j <dynamicBeanList.size() ; j++) {
                    dynamicBeanList.get(0).setIdentification(1);
                }
            }

        //System.out.println(biaoshifu);

        return dynamicBeanList;
    }

    @Override
    public int addDynamic(DynamicBean dynamicBean) {
        //判断用户是否选择话题类型,如果选择,话题下的动态数量+1
        if (dynamicBean.getTopicId() != null){
            topicMapper.addTopicNum(dynamicBean.getTopicId());
        }
        //发布时间为当前时间
        dynamicBean.setCreateTime(new Date());
        //点赞数默认为0
        dynamicBean.setDynamicPraise(0);
        //分享数默认为0
        dynamicBean.setDynamicSharenum(0);
        //评论数默认为0
        dynamicBean.setDynamicCommentnum(0);
        //逻辑删除默认为0不删除
        dynamicBean.setDelflag(0);
        //敏感词过滤*****
        String content = WordFilter.doFilter(dynamicBean.getDynamicContent());
        System.out.println(content);
        dynamicBean.setDynamicContent(content);
        return dynamicMapper.addDynamic(dynamicBean);
    }

    @Override
    public List<DynamicBean> queryDynamicTime(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //当前登录人ID
        Long userId = RequestUtil.getLoginUser().getUserId();
        System.out.println(userId);
        //当前发布动态用户ID
        Integer biaoshifu2 = null;
        List<DynamicBean> dynamicBeanList = dynamicMapper.queryDynamicTime();
        for (int i = 0; i < dynamicBeanList.size(); i++) {
            Long userId1 = dynamicBeanList.get(i).getUserId();
            Integer biaoshifu =  dynamicMapper.selectFavorites(userId,userId1);
            System.out.println(biaoshifu+"====================================");
            for (int j = 0; j <dynamicBeanList.size() ; j++) {
                dynamicBeanList.get(i).setIdentification(biaoshifu);
            }
        }
        //System.out.println(biaoshifu);

        return dynamicBeanList;
    }

    @Override
    public List<DynamicBean> queryDynamicHeat(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //当前登录人ID
        Long userId = RequestUtil.getLoginUser().getUserId();
        System.out.println(userId);
        //当前发布动态用户ID
        List<DynamicBean> dynamicBeanList = dynamicMapper.queryDynamicTime();
        for (int i = 0; i < dynamicBeanList.size(); i++) {
            Long userId1 = dynamicBeanList.get(i).getUserId();
            Integer biaoshifu =  dynamicMapper.selectFavorites(userId,userId1);
            System.out.println(biaoshifu+"====================================");
            for (int j = 0; j <dynamicBeanList.size() ; j++) {
                dynamicBeanList.get(i).setIdentification(biaoshifu);
            }
        }
        //System.out.println(biaoshifu);

        return dynamicBeanList;
    }

    @Override
    public Integer queryDynamicCount(Long userId) {
        return dynamicMapper.queryDynamicCount(userId);
    }
}
