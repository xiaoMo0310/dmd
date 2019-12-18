package com.dmd.admin.mapper;

import com.dmd.admin.model.domain.CommentBean;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: CommentMapper
 * @projectName dmd-masters
 * @description:
 * @date 2019/10/2115:31
 */
public interface CommentMapper {
    List<CommentBean> queryCommentAll(CommentBean commentBean);

    int updateCommentDelflag(List<Long> ids);

    void updateComment(List<Long> ids);

    int updateDiveLogDelflag(List<Long> ids);

    List<CommentBean> queryCommentAllByCoach(CommentBean commentBean);

    CommentBean selectCommentByIdMember(Long commentId);

    CommentBean selectCommentByIdCoach(Long commentId);

    List<CommentBean> queryCommentByDynamic(Long forDynamicId);

    List<CommentBean> queryCommentByDynamicCoach(Long forDynamicId);
}
