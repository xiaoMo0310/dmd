package com.dmd.mall.service.impl;

import com.dmd.WordFilter;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.utils.RequestUtil;
import com.dmd.mall.mapper.CommentMapper;
import com.dmd.mall.mapper.DynamicMapper;
import com.dmd.mall.model.domain.CommentBean;
import com.dmd.mall.model.dto.MessageDto;
import com.dmd.mall.service.CommentService;
import com.dmd.mall.service.UmsNoticeService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

/**
 * @author ChenYanbing
 * @title: CommentServiceImpl
 * @projectName dmd-master
 * @description:
 * @date 2019/9/2413:36
 */
@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private DynamicMapper dynamicMapper;
    @Autowired
    private UmsNoticeService noticeService;

    @Override
    public List<CommentBean> queryCommentAll(Long forDynamicId,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        /*ArrayList list = new ArrayList();
        //根节点id为评论id,根据文章id查询评论id
        List<Long> idList = commentMapper.queryCommentById(forDynamicId);
        for (int i = 0; i < idList.size(); i++) {
            List<CommentBean> node = getNode(idList.get(i));
            list.addAll(node);
        }
        return list;*/
        Integer id = 0;
        List<CommentBean> node = getNode(Long.valueOf(id),forDynamicId);
        return node;

    }



    private List<CommentBean> getNode(Long forPid,Long forDynamicId) {
        //用户所发
        List<CommentBean> findCommentListByPId = commentMapper.findCommentListById(forPid,forDynamicId);
        //教练所发
        List<CommentBean> findCommentListByPIdCoach = commentMapper.findCommentListByIdCoach(forPid,forDynamicId);
        //数据合并
        findCommentListByPId.addAll(findCommentListByPIdCoach);
        //按照时间倒序排序
        findCommentListByPId.sort((o1, o2) -> o2.getCreateTime().compareTo(o1.getCreateTime()));

        for (CommentBean commentBean : findCommentListByPId) {
            Long id2 = commentBean.getCommentId();
            List<CommentBean> nodes = getNode(id2,forDynamicId);
            commentBean.setChildren(nodes) ;
        }
        return findCommentListByPId;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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
        //登陆信息
        LoginAuthDto loginAuthDtos = RequestUtil.getLoginUser();
        //登录角色
        String userTypes = loginAuthDtos.getUserType();
        //用户登录
        if (userTypes.equals("member")){
            commentBean.setUserType(1);
        //教练登录
        }else if(userTypes.equals("coach")){
            commentBean.setUserType(2);
        }
        commentMapper.addComment(commentBean);
        int result = dynamicMapper.addrCommentNum(commentBean.getForDynamicId());
        //发布评论，动态评论数加1
        //添加发送消息
        if(result >0 ){
            //回复发送消息
            LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
            MessageDto messageDto = new MessageDto();
            messageDto.setTitle("动态评论消息");
            messageDto.setContent(content);
            messageDto.setJumpAddress(commentBean.getForDynamicId() + "");
            //发送消息
            noticeService.saveNoticeMessage(loginAuthDto, commentBean.getUserId(), "member", 3, messageDto);
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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
        //回复状态默认正常 状态（0=正常 1=待审核 2=禁止）
        commentBean.setStatus(0);
        //逻辑删除默认为否
        commentBean.setDelflag(0);
        //敏感词过滤*****
        String content = WordFilter.doFilter(commentBean.getContent());
        commentBean.setContent(content);
        //登陆信息
        LoginAuthDto loginAuthDtos = RequestUtil.getLoginUser();
        //登录角色
        String userTypes = loginAuthDtos.getUserType();
        //用户登录
        if (userTypes.equals("member")){
            commentBean.setUserType(1);
            //教练登录
        }else if(userTypes.equals("coach")){
            commentBean.setUserType(2);
        }
        commentMapper.addComment(commentBean);
        //发布回复，动态评论数加1
        int result = dynamicMapper.addrCommentNum(commentBean.getForDynamicId());
        if(result >0 ){
            //回复发送消息
            LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
            MessageDto messageDto = new MessageDto();
            messageDto.setTitle("动态回复消息");
            messageDto.setContent(content);
            messageDto.setJumpAddress(commentBean.getForDynamicId() + "");
            //发送消息
            noticeService.saveNoticeMessage(loginAuthDto, forUid, "member", 3, messageDto);
        }
        return result;
    }

    @Override
    public int updateCommentDelflag(Long commentId,Long userId,Long DynamicId) {
        //登陆信息
        LoginAuthDto loginAuthDtos = RequestUtil.getLoginUser();
        //登录角色
        String userTypes = loginAuthDtos.getUserType();
        //用户登录
        if (userTypes.equals("member")){
            commentMapper.updateCommentDelflag(commentId,userId);
            //教练登录
        }else if(userTypes.equals("coach")){
            commentMapper.updateCommentDelflagByCoach(commentId,userId);
        }
        //相应动态评论数量-1
        return dynamicMapper.reduceCommentNum(DynamicId);
    }

}
