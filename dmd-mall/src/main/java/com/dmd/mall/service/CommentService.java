package com.dmd.mall.service;

import com.dmd.mall.model.domain.CommentBean;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: CommentService
 * @projectName dmd-master
 * @description: TODO
 * @date 2019/9/2413:35
 */
public interface CommentService {
    List<CommentBean> queryCommentAll(Long forDynamicId);

    int addComment(CommentBean commentBean);

    int addCommentReply(CommentBean commentBean, Long commentId, Long forUid);

    int updateCommentDelflag(Long commentId,Long userId);
}
