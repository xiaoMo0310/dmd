package com.dmd.admin.service;

import com.dmd.admin.model.domain.CommentBean;
import com.dmd.admin.model.vo.CommentByDynamicIdVo;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: CommentService
 * @projectName dmd-masters
 * @description:
 * @date 2019/10/2115:30
 */
public interface CommentService {
    List<CommentBean> queryCommentAll(Integer pageNum, Integer pageSize, CommentBean commentBean);

    int updateCommentDelflag(List<Long> ids);

    CommentByDynamicIdVo selectCommentByDynamic(Long forDynamicId, Long commentId, Integer userType);

    List<CommentBean> queryCommentByDynamic(Integer pageNum, Integer pageSize, Long forDynamicId);
}
