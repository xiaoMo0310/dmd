package com.dmd.admin.mapper;

import com.dmd.admin.model.domain.CommentBean;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: CommentMapper
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/10/2115:31
 */
public interface CommentMapper {
    List<CommentBean> queryCommentAll(CommentBean commentBean);

    int updateCommentDelflag(List<Long> ids);

    void updateComment(String[] ids);
}