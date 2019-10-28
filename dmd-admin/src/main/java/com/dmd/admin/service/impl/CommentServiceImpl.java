package com.dmd.admin.service.impl;

import com.dmd.admin.mapper.CommentMapper;
import com.dmd.admin.model.domain.CommentBean;
import com.dmd.admin.service.CommentService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: CommentServiceImpl
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/10/2115:30
 */
@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<CommentBean> queryCommentAll(Integer pageNum, Integer pageSize, CommentBean commentBean) {
        PageHelper.startPage(pageNum, pageSize);
        return commentMapper.queryCommentAll(commentBean);
    }

    @Override
    public int updateCommentDelflag(List<Long> ids) {
        return commentMapper.updateCommentDelflag(ids);
    }
}
