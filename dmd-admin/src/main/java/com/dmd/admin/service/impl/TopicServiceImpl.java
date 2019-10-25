package com.dmd.admin.service.impl;

import com.dmd.admin.mapper.TopicAdminMapper;
import com.dmd.admin.model.domain.SmsHomeAdvertiseExample;
import com.dmd.admin.model.domain.TopicBean;
import com.dmd.admin.service.TopicService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author ChenYanbing
 * @title: TopicServiceImpl
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/10/179:57
 */
@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicAdminMapper topicMapper;

    @Override
    public List<TopicBean> queryTopicPage(Integer pageNum, Integer pageSize, TopicBean topicBean) {

        PageHelper.startPage(pageNum, pageSize);
        /*SmsHomeAdvertiseExample example = new SmsHomeAdvertiseExample();
        SmsHomeAdvertiseExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(topicBean.getStratTime())) {
            String startStr = topicBean.getStratTime() + " 00:00:00";
            String endStr = topicBean.getStratTime() + " 23:59:59";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date start = null;
            try {
                start = sdf.parse(startStr);
                topicBean.setStratTime(start);
                System.out.println(start);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }*/

        return topicMapper.queryTopicPage(topicBean);
    }

    @Override
    public int addTopic(TopicBean topicBean) {
        //动态下数量默认为0
        topicBean.setTopicNum(0);
        //发布时间为当前时间
        topicBean.setCreateTime(new Date());
        return topicMapper.addTopic(topicBean);
    }

    @Override
    public int updateTopicById(TopicBean topicBean) {
        topicBean.setUpdateTime(new Date());
        return topicMapper.updateTopicById(topicBean);
    }

    @Override
    public TopicBean findTopicInfoById(Long id) {
        return topicMapper.findTopicInfoById(id);
    }

    @Override
    public int deleteTopicById(List<Long> ids) {
        return topicMapper.deleteTopicById(ids);
    }

}
