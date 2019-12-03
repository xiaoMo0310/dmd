package com.dmd.mall.service.impl;

import com.dmd.WordFilter;
import com.dmd.mall.mapper.DiveLogCommentMapper;
import com.dmd.mall.mapper.DiveLogMapper;
import com.dmd.mall.model.domain.CommentBean;
import com.dmd.mall.service.DiveLogCommentService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

/**
 * @author ChenYanbing
 * @title: DiveLogCommentServiceImpl
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/10/1110:52
 */
@Service
public class DiveLogCommentServiceImpl implements DiveLogCommentService{

    @Autowired
    private DiveLogCommentMapper diveLogCommentMapper;

    @Autowired
    private DiveLogMapper diveLogMapper;

    @Override
    public List<CommentBean> queryCommentAll(Long forDiveLogId,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Integer id = 0;
        List<CommentBean> node = getNode(Long.valueOf(id),forDiveLogId);
        return node;

    }

    private List<CommentBean> getNode(Long forPid,Long forDiveLogId) {
        List<CommentBean> findCommentListByPId = diveLogCommentMapper.findCommentListById(forPid,forDiveLogId);
        for (CommentBean commentBean : findCommentListByPId) {
            Long id2 = commentBean.getCommentId();
            List<CommentBean> nodes = getNode(id2,forDiveLogId);
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
       /* ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //获取ip地址是封装好的一个类
        String ip = IpaddressUtils.getIp(request);*/
        try {
            //获取ip地址
            InetAddress ia = InetAddress.getLocalHost();
            String hostAddress = ia.getHostAddress();
            commentBean.setIpAddress(hostAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        //评论状态默认正常 状态（0=正常 1=待审核 2=禁止）
        commentBean.setStatus(0);
        //逻辑删除默认为否
        commentBean.setDelflag(0);
        //敏感词过滤*****
        String content = WordFilter.doFilter(commentBean.getContent());
        commentBean.setContent(content);
        diveLogCommentMapper.addComment(commentBean);
        //发布评论，日志评论数加1
        return diveLogMapper.addrCommentNum(commentBean.getForDiveLogId());
    }

    @Override
    public int addCommentReply(CommentBean commentBean, Long commentId, Long forUid) {
        //回复的PId为当前评论id
        commentBean.setForPid(commentId);
        //被回复者id为评论人id
        commentBean.setForUid(forUid);
        //回复时间为当前时间
        commentBean.setCreateTime(new Date());
        //评论类型为评论0=评论  1=回复
        commentBean.setType(1);
        //评论ip地址为本地地址
        /*ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //获取ip地址是封装好的一个类
        String ip = IpaddressUtils.getIp(request);*/
        try {
            InetAddress ia = InetAddress.getLocalHost();
            String hostAddress = ia.getHostAddress();
            commentBean.setIpAddress(hostAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        //回复状态默认正常 状态（0=正常  1=待审核 2=禁止）
        commentBean.setStatus(0);
        //逻辑删除默认为否
        commentBean.setDelflag(0);
        //敏感词过滤*****
        String content = WordFilter.doFilter(commentBean.getContent());
        commentBean.setContent(content);
        diveLogCommentMapper.addComment(commentBean);
        //发布回复，日志评论数加1
        return diveLogMapper.addrCommentNum(commentBean.getForDiveLogId());
    }

    @Override
    public int updateCommentDelflag(Long commentId, Long userId, Long forDiveLogId) {
        diveLogCommentMapper.updateCommentDelflag(commentId,userId);
        //日志评论数量-1
        return diveLogMapper.reduceCommentNum(forDiveLogId);
    }

}
