package com.dmd.admin.service.impl;

import com.dmd.admin.mapper.CommentMapper;
import com.dmd.admin.mapper.DynamicAmdinMappper;
import com.dmd.admin.model.domain.CommentBean;
import com.dmd.admin.model.domain.DynamicBean;
import com.dmd.admin.model.vo.CommentByDynamicIdVo;
import com.dmd.admin.service.CommentService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.DynamicMBean;
import java.util.List;

/**
 * @author ChenYanbing
 * @title: CommentServiceImpl
 * @projectName dmd-masters
 * @description:
 * @date 2019/10/2115:30
 */
@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private DynamicAmdinMappper dynamicAmdinMappper;

    @Override
    public List<CommentBean> queryCommentAll(Integer pageNum, Integer pageSize, CommentBean commentBean) {
        PageHelper.startPage(pageNum, pageSize);
        //全部评论
        List<CommentBean> commentBeans = commentMapper.queryCommentAll(commentBean);
        for (int i = 0; i < commentBeans.size(); i++) {
            if (commentBeans.get(i).getUserType() == 1){
                commentBeans.get(i).setCommentHeadPortrait(commentBeans.get(i).getCommentHeadPortrait());
                commentBeans.get(i).setCommentName(commentBeans.get(i).getCommentName());
                commentBeans.get(i).setCommentHeadPortraitCoach("");
                commentBeans.get(i).setCoachName("");
            }else if(commentBeans.get(i).getUserType() == 2){
                commentBeans.get(i).setCommentHeadPortraitCoach(commentBeans.get(i).getCommentHeadPortraitCoach());
                commentBeans.get(i).setCoachName(commentBeans.get(i).getCoachName());
                commentBeans.get(i).setCommentHeadPortrait("");
                commentBeans.get(i).setCommentName("");
            }
            if(commentBeans.get(i).getForUserType()!=null && commentBeans.get(i).getForUserType() == 1){
                commentBeans.get(i).setForUserTypeName2(commentBeans.get(i).getForUserTypeName2());
                commentBeans.get(i).setForUserTypeName("");
            }else if(commentBeans.get(i).getForUserType()!=null && commentBeans.get(i).getForUserType() == 2){
                commentBeans.get(i).setForUserTypeName(commentBeans.get(i).getForUserTypeName());
                commentBeans.get(i).setForUserTypeName2("");
            }
        }
        /*//教练发布
        List<CommentBean> commentBeansCoach = commentMapper.queryCommentAllByCoach(commentBean);*/

        /*//数据合并
        commentBeans.addAll(commentBeansCoach);
        //按照时间倒序排序
        commentBeans.sort((o1, o2) -> o2.getCreateTime().compareTo(o1.getCreateTime()));*/
        return commentBeans;
    }

    @Override
    public int updateCommentDelflag(List<Long> ids) {
        return commentMapper.updateCommentDelflag(ids);
    }

    @Override
    public CommentByDynamicIdVo selectCommentByDynamic(Long forDynamicId, Long commentId, Integer userType) {
        CommentByDynamicIdVo commentByDynamicIdVo = new CommentByDynamicIdVo();
        if (userType == 1){
            CommentBean commentBean = commentMapper.selectCommentByIdMember(commentId);
            commentByDynamicIdVo.setCommentHeadPortrait(commentBean.getCommentHeadPortrait());
            commentByDynamicIdVo.setCommentName(commentBean.getCommentName());
            commentByDynamicIdVo.setContent(commentBean.getContent());
            commentByDynamicIdVo.setCreateTime(commentBean.getCreateTime());
            commentByDynamicIdVo.setIpAddress(commentBean.getIpAddress());
            commentByDynamicIdVo.setStatus(commentBean.getStatus());
            commentByDynamicIdVo.setType(commentBean.getType());
            commentByDynamicIdVo.setUserType(commentBean.getUserType());
        }else if(userType == 2){
            CommentBean commentBean = commentMapper.selectCommentByIdCoach(commentId);
            commentByDynamicIdVo.setCommentHeadPortrait(commentBean.getCommentHeadPortrait());
            commentByDynamicIdVo.setCommentName(commentBean.getCommentName());
            commentByDynamicIdVo.setContent(commentBean.getContent());
            commentByDynamicIdVo.setCreateTime(commentBean.getCreateTime());
            commentByDynamicIdVo.setIpAddress(commentBean.getIpAddress());
            commentByDynamicIdVo.setStatus(commentBean.getStatus());
            commentByDynamicIdVo.setType(commentBean.getType());
            commentByDynamicIdVo.setUserType(commentBean.getUserType());
        }
        DynamicBean dynamicBean = dynamicAmdinMappper.selectDynamicByIdComment(forDynamicId);
        commentByDynamicIdVo.setDynamicContent(dynamicBean.getDynamicContent());
        commentByDynamicIdVo.setDynamicId(dynamicBean.getId());
        commentByDynamicIdVo.setCreateTimeByDynamic(dynamicBean.getCreateTime());
        return commentByDynamicIdVo;
    }

    @Override
    public List<CommentBean> queryCommentByDynamic(Integer pageNum, Integer pageSize, Long forDynamicId) {
        PageHelper.startPage(pageNum, pageSize);
        List<CommentBean> commentBeans = commentMapper.queryCommentByDynamic(forDynamicId);

        for (int i = 0; i < commentBeans.size(); i++) {
            if (commentBeans.get(i).getUserType() == 1){
                commentBeans.get(i).setCommentName(commentBeans.get(i).getCommentName());
                commentBeans.get(i).setCoachName("");
            }else if(commentBeans.get(i).getUserType() == 2){
                commentBeans.get(i).setCoachName(commentBeans.get(i).getCoachName());
                commentBeans.get(i).setCommentName("");
            }
            if(commentBeans.get(i).getForUserType()!=null && commentBeans.get(i).getForUserType() == 1){
                commentBeans.get(i).setForUserTypeName2(commentBeans.get(i).getForUserTypeName2());
                commentBeans.get(i).setForUserTypeName("");
            }else if(commentBeans.get(i).getForUserType()!=null && commentBeans.get(i).getForUserType() == 2){
                commentBeans.get(i).setForUserTypeName(commentBeans.get(i).getForUserTypeName());
                commentBeans.get(i).setForUserTypeName2("");
            }
        }
        return commentBeans;
    }
}
