package com.dmd.admin.service;

import com.dmd.admin.model.domain.PmsComment;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: PmsCommentService
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/10/229:20
 */
public interface PmsCommentService {
    List<PmsComment> queryCommentAll(Integer pageNum, Integer pageSize, PmsComment pmsComment);

    int deleteComment(List<Long> ids);
}
