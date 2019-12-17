package com.dmd.admin.service.impl;

import com.dmd.admin.mapper.PmsCommentMapper;
import com.dmd.admin.model.domain.PmsComment;
import com.dmd.admin.service.PmsCommentService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: PmsCommentServiceImpl
 * @projectName dmd-masters
 * @description:
 * @date 2019/10/229:21
 */
@Service
public class PmsCommentServiceImpl implements PmsCommentService{

    @Autowired
    private PmsCommentMapper pmsCommentMapper;

    @Override
    public List<PmsComment> queryCommentAll(Integer pageNum, Integer pageSize, PmsComment pmsComment) {
        PageHelper.startPage(pageNum, pageSize);
        return pmsCommentMapper.queryCommentAll(pmsComment);
    }

    @Override
    public int deleteComment(List<Long> ids) {
        return pmsCommentMapper.deleteComment(ids);
    }
}
