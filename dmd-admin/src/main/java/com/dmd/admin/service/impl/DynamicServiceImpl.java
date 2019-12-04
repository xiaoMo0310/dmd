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

import java.util.List;

/**
 * @author ChenYanbing
 * @title: DynamicServiceImpl
 * @projectName dmd-masters
 * @description: TODO
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
        return dynamicAmdinMappper.queryDynamicPage(dynamicBean);
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
    public DynamicBean selectDynamicById(Long id) {
        return dynamicAmdinMappper.selectDynamicById(id);
    }
}
