package com.dmd.admin.service.impl;

import com.dmd.admin.mapper.CommentMapper;
import com.dmd.admin.mapper.DynamicAmdinMappper;
import com.dmd.admin.mapper.TopicAdminMapper;
import com.dmd.admin.model.domain.CommentBean;
import com.dmd.admin.model.domain.DynamicBean;
import com.dmd.admin.model.domain.TopicBean;
import com.dmd.admin.service.DynamicService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYanbing
 * @title: DynamicServiceImpl
 * @projectName dmd-masters
 * @description:
 * @date 2019/10/1716:39
 */
@Service
public class DynamicServiceImpl implements DynamicService {

    @Autowired
    private DynamicAmdinMappper dynamicAmdinMappper;

    @Autowired
    private TopicAdminMapper topicAdminMapper;

    @Autowired
    private CommentMapper commentMapper;
    @Override
    public List<DynamicBean> queryDynamicPage(Integer pageNum, Integer pageSize, DynamicBean dynamicBean) {
        PageHelper.startPage(pageNum, pageSize);
        //用户发布
        List<DynamicBean> dynamicBeans = dynamicAmdinMappper.queryDynamicPage(dynamicBean);
        //教练发布
        List<DynamicBean> dynamicBeansByCoach = dynamicAmdinMappper.queryDynamicPageByCoach(dynamicBean);
        //数据合并
        dynamicBeans.addAll(dynamicBeansByCoach);
        dynamicBeans.sort((o1, o2) -> o2.getCreateTime().compareTo(o1.getCreateTime()));
        return dynamicBeans;
    }

    @Override
    public List<TopicBean> queryTopic() {
        return topicAdminMapper.queryTopic();
    }

    @Override
    public int updateDynamicDelflagById(List<Long> ids) {
        //动态删除时对应的动态下评论也全部删除
        commentMapper.updateComment(ids);
        //动态删除时判断是否有话题id，如果有话题id则话题下动态数量减1。批量修改
        List<Long> dynamicTopicId = dynamicAmdinMappper.queryDynamicById(ids);
        Integer size = dynamicTopicId.size();
        if (dynamicTopicId != null){
            topicAdminMapper.reduceTopicNum(dynamicTopicId,size);
        }
        return dynamicAmdinMappper.updateDynamicDelflag(ids);
    }

    @Override
    public DynamicBean selectDynamicById(Long id,Integer userType) {
        DynamicBean dynamicBean1 = new DynamicBean();
        if(userType == 1){
            DynamicBean dynamicBean = dynamicAmdinMappper.selectDynamicById(id);
            dynamicBean1 = dynamicBean;
        }else if(userType == 2){
            DynamicBean dynamicBean = dynamicAmdinMappper.selectDynamicByIdByCoach(id);
            dynamicBean1 = dynamicBean;
        }
        return dynamicBean1;
    }
}
