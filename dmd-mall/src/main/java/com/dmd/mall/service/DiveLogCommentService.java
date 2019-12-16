package com.dmd.mall.service;

import com.dmd.mall.model.domain.CommentBean;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: DiveLogCommentService
 * @projectName dmd-masters
 * @description:
 * @date 2019/10/1110:52
 */
public interface DiveLogCommentService {

    List<CommentBean> queryCommentAll(Long forDiveLogId,Integer pageNum,Integer pageSize);

    int addComment(CommentBean commentBean);

    int addCommentReply(CommentBean commentBean, Long commentId, Long forUid);

    int updateCommentDelflag(Long commentId, Long userId, Long forDiveLogId);
}
