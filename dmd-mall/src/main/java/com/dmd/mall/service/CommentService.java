package com.dmd.mall.service;

import com.dmd.mall.model.domain.CommentBean;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: CommentService
 * @projectName dmd-master
 * @description:
 * @date 2019/9/2413:35
 */
public interface CommentService {
    List<CommentBean> queryCommentAll(Long forDynamicId, Integer pageNum, Integer pageSize);

    int addComment(CommentBean commentBean);

    int addCommentReply(CommentBean commentBean, Long commentId, Long forUid);

    int updateCommentDelflag(Long commentId,Long userId,Long DynamicId);
}
