package com.dmd.admin.service;

import com.dmd.admin.model.domain.CommentBean;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: CommentService
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/10/2115:30
 */
public interface CommentService {
    List<CommentBean> queryCommentAll(Integer pageNum, Integer pageSize, CommentBean commentBean);

    int updateCommentDelflag(String[] ids);
}
