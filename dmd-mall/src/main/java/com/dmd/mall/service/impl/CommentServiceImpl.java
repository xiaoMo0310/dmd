package com.dmd.mall.service.impl;

import com.dmd.IpaddressUtils;
import com.dmd.mall.mapper.CommentMapper;
import com.dmd.mall.model.domain.CommentBean;
import com.dmd.mall.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.net.util.IPAddressUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ChenYanbing
 * @title: CommentServiceImpl
 * @projectName dmd-master
 * @description: TODO
 * @date 2019/9/2413:36
 */
@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<CommentBean> queryCommentAll(Long forDynamicId) {
        /*ArrayList list = new ArrayList();
        //根节点id为评论id,根据文章id查询评论id
        List<Long> idList = commentMapper.queryCommentById(forDynamicId);
        for (int i = 0; i < idList.size(); i++) {
            List<CommentBean> node = getNode(idList.get(i));
            list.addAll(node);
        }

        return list;*/
        //所有留言新增时父id为0.所有的回复对应的都是他儿子，两层分级
        Integer id = 0;
        List<CommentBean> node = getNode(Long.valueOf(id),forDynamicId);
        return node;

    }



    private List<CommentBean> getNode(Long forPid,Long forDynamicId) {
        List<CommentBean> findCommentListByPId = commentMapper.findCommentListById(forPid,forDynamicId);
        System.out.println(findCommentListByPId);
        for (CommentBean commentBean : findCommentListByPId) {
            Long id2 = commentBean.getCommentId();
            System.out.println(id2);
            List<CommentBean> nodes = getNode(id2,forDynamicId);
            commentBean.setChildren(nodes) ;
        }
        return findCommentListByPId;
    }

    @Override
    public int addComment(CommentBean commentBean) {
        //所有评论留言父 PID默认为0
        commentBean.setForPid(Long.valueOf(0));
        //评论被回复者id无  为0
        commentBean.setForUid(Long.valueOf(0));
        //评论时间为当前时间
        commentBean.setCreateTime(new Date());
        //评论类型为评论0=评论  1=回复
        commentBean.setType(0);
        //评论ip地址为本地地址
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //获取ip地址是封装好的一个类
        String ip = IpaddressUtils.getIp(request);
        commentBean.setIpAddress(ip);
        //评论状态默认正常 状态（0=正常 1=待审核 2=禁止）
        commentBean.setStatus(0);
        //逻辑删除默认为否
        commentBean.setDelflag(0);
        return commentMapper.addComment(commentBean);
    }

    @Override
    public int addCommentReply(CommentBean commentBean,Long commentId, Long forUid) {
        //回复的PId为当前评论id
        commentBean.setForPid(commentId);
        //被回复者id为评论人id
        commentBean.setForUid(forUid);
        //回复时间为当前时间
        commentBean.setCreateTime(new Date());
        //评论类型为评论0=评论  1=回复
        commentBean.setType(1);
        //评论ip地址为本地地址
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //获取ip地址是封装好的一个类
        String ip = IpaddressUtils.getIp(request);
        commentBean.setIpAddress(ip);
        //回复状态默认正常 状态（0=正常 1=待审核 2=禁止）
        commentBean.setStatus(0);
        //逻辑删除默认为否
        commentBean.setDelflag(0);
        return commentMapper.addComment(commentBean);
    }

    @Override
    public int updateCommentDelflag(Long commentId,Long userId) {
        return commentMapper.updateCommentDelflag(commentId,userId);
    }

}
