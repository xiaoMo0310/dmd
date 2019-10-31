package com.dmd.admin.service.impl;

import com.dmd.admin.mapper.CommentMapper;
import com.dmd.admin.mapper.DiveLogAdminMapper;
import com.dmd.admin.model.domain.DiveLogAirbottleBean;
import com.dmd.admin.model.domain.DiveLogBean;
import com.dmd.admin.service.DiveLogService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: DiveLogServiceImpl
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/10/3114:29
 */
@Service
public class DiveLogServiceImpl implements DiveLogService{
    @Autowired
    private DiveLogAdminMapper diveLogAdminMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<DiveLogBean> queryDiveLogPage(Integer pageNum, Integer pageSize, DiveLogBean diveLogBean) {
        PageHelper.startPage(pageNum, pageSize);
        return diveLogAdminMapper.queryDiveLogPage(diveLogBean);
    }

    @Override
    public List<DiveLogAirbottleBean> queryDiveLogAirbottleByDiveLogId(Long id) {
        return diveLogAdminMapper.queryDiveLogAirbottleByDiveLogId(id);
    }

    @Override
    public int updateDiveLogDelflag(List<Long> ids) {
        //删除潜水日志
        diveLogAdminMapper.updateDiveLogDelflag(ids);
        //删除潜水日志对应的气瓶消耗删除
        diveLogAdminMapper.updateDiveLogAirbottleDelflag(ids);
        //删除潜水日志对应日志下评论删除
        return commentMapper.updateDiveLogDelflag(ids);
    }
}
