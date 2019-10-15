package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.CommentBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: DiveLogCommentMapper
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/10/1110:54
 */
public interface DiveLogCommentMapper {

    List<CommentBean> findCommentListById(@Param("forPid")Long forPid,@Param("forDiveLogId") Long forDiveLogId);

    void addComment(CommentBean commentBean);

    void updateCommentDelflag(@Param("commentId") Long commentId,@Param("userId")Long userId);
}
