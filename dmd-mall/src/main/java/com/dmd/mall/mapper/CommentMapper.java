package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.CommentBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: CommentMapper
 * @projectName dmd-master
 * @description: TODO
 * @date 2019/9/2413:36
 */
public interface CommentMapper {
    List<Long> queryCommentById(Long forDynamicId);

    List<CommentBean> findCommentListById(@Param("forPid")Long forPid,@Param("forDynamicId") Long forDynamicId);

    void addComment(CommentBean commentBean);

    Integer queryComment(Long id);

    void updateCommentDelflag(@Param("commentId") Long commentId,@Param("userId")Long userId);

    void updateComment(Long id);

    List<CommentBean> findCommentListByIdCoach(@Param("forPid")Long forPid,@Param("forDynamicId") Long forDynamicId);

    void updateCommentDelflagByCoach(@Param("commentId") Long commentId,@Param("userId")Long userId);
}
