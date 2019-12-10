package com.dmd.mall.service.impl;

import com.dmd.WordFilter;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.utils.RequestUtil;
import com.dmd.mall.mapper.CommentMapper;
import com.dmd.mall.mapper.DynamicMapper;
import com.dmd.mall.mapper.TopicMapper;
import com.dmd.mall.model.domain.DynamicAlbumTimeBean;
import com.dmd.mall.model.domain.DynamicBean;
import com.dmd.mall.model.domain.UmsMember;
import com.dmd.mall.model.dto.MessageDto;
import com.dmd.mall.model.vo.UserDetailsVo;
import com.dmd.mall.service.DynamicService;
import com.dmd.mall.service.UmsMemberService;
import com.dmd.mall.service.UmsNoticeService;
import com.github.pagehelper.PageHelper;
import io.swagger.models.auth.In;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author ChenYanbing
 * @title: DynamicServiceImpl
 * @projectName dmd-master
 * @description: TODO
 * @date 2019/9/239:43
 */
@Service
public class DynamicServiceImpl implements DynamicService{

    @Autowired
    private DynamicMapper dynamicMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private UmsNoticeService noticeService;
    @Autowired
    private UmsMemberService memberService;

    @Override
    public List<DynamicBean> queryDynamic(Long userId) {
        //当前登录人ID
        List<DynamicBean> dynamicBeanList = dynamicMapper.queryDynamic(userId);
        for (int i = 0; i < dynamicBeanList.size(); i++) {
            //动态id
            Long id = dynamicBeanList.get(i).getId();
            Integer biaoshifu =  dynamicMapper.selectFavoritespraise(userId,id);
            for (int j = 0; j <dynamicBeanList.size() ; j++) {
                dynamicBeanList.get(i).setIdentificationPraise(biaoshifu);
            }
        }
        return dynamicBeanList;
    }

    @Override
    public List<DynamicBean> queryDynamicPage(Long userId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //当前登录人ID
        List<DynamicBean> dynamicBeanList = dynamicMapper.queryDynamic(userId);
        for (int i = 0; i < dynamicBeanList.size(); i++) {
            //动态id
            Long id = dynamicBeanList.get(i).getId();
            Integer biaoshifu =  dynamicMapper.selectFavoritespraise(userId,id);
            for (int j = 0; j <dynamicBeanList.size() ; j++) {
                dynamicBeanList.get(i).setIdentificationPraise(biaoshifu);
            }
        }
        return dynamicBeanList;
    }

    @Override
    public Integer queryPraise(Long id) {
        return dynamicMapper.queryPraise(id);
    }

    @Override
    public Integer queryComment(Long id) {
        return commentMapper.queryComment(id);
    }

    @Override
    public Integer queryShare(Long id) {
        return dynamicMapper.queryShare(id);
    }

    @Override
    public int updateLikePraise(Long id) {
        //用戶ID
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        Long userId = loginAuthDto.getUserId();
        //判断用户是否点过赞
        Integer or = dynamicMapper.selectUserIdBypraise(id,userId);
        if(or == 0){
            dynamicMapper.insertUserPraise(id,userId);
        }
        if(or != 0){
            dynamicMapper.updateUserPraiseLike(id,userId);
        }
        int result = dynamicMapper.updateLikePraise(id);
        if(result > 0){
            DynamicBean dynamicBean = dynamicMapper.selectDynamicById(id);
            MessageDto messageDto = new MessageDto();
            messageDto.setTitle("动态点赞消息");
            messageDto.setContent(dynamicBean.getDynamicContent());
            messageDto.setJumpAddress(dynamicBean.getId() + "");
            //发送消息
            noticeService.saveNoticeMessage(loginAuthDto, dynamicBean.getUserId(), "member", 2, messageDto);
        }
        return result;
    }

    @Override
    public int updateCancelPraise(Long id) {
        //用戶ID
        Long userId = RequestUtil.getLoginUser().getUserId();
        dynamicMapper.updateUserPraise(id,userId);
        return dynamicMapper.updateCancelPraise(id);
    }

    @Override
    public int updateDynamicrShare(Long id) {
        return dynamicMapper.updateDynamicrShare(id);
    }

    @Override
    public int updateDynamicDelflag(Long id) {
        //动态删除时对应的动态下评论也全部删除
        commentMapper.updateComment(id);
        //动态删除时判断是否有话题id，如果有话题id则话题下动态数量减1
        List<DynamicBean> dynamicBeanList = dynamicMapper.queryDynamicById(id);
        if (dynamicBeanList.get(0).getTopicId() != null){
            topicMapper.reduceTopicNum(dynamicBeanList.get(0).getTopicId());
        }
        return dynamicMapper.updateDynamicDelflag(id);
    }

    @Override
    public List<DynamicBean> queryTopicByDynamicTime(Integer id,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //当前登录人ID
        Long userId = RequestUtil.getLoginUser().getUserId();
        //当前发布动态用户ID
        List<DynamicBean> dynamicBeanList = dynamicMapper.queryTopicByDynamicTime(id);
        for (int i = 0; i < dynamicBeanList.size(); i++) {
            Long userId1 = dynamicBeanList.get(i).getUserId();
            Integer biaoshifu =  dynamicMapper.selectFavorites(userId,userId1);
                for (int j = 0; j <dynamicBeanList.size() ; j++) {
                    dynamicBeanList.get(i).setIdentification(biaoshifu);
            }
        }
        for (int i = 0; i < dynamicBeanList.size(); i++) {
            Long dynamicId = dynamicBeanList.get(i).getId();
            Integer biaoshifuPraise =  dynamicMapper.selectFavoritespraiseTopic(userId,dynamicId);
            for (int j = 0; j <dynamicBeanList.size() ; j++) {
                dynamicBeanList.get(i).setIdentificationPraise(biaoshifuPraise);
            }
        }
        return dynamicBeanList;
    }

    @Override
    public List<DynamicBean> selectTopicByDynamicHeat(Integer id,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //当前登录人ID
        Long userId = RequestUtil.getLoginUser().getUserId();
        //当前发布动态用户ID
        List<DynamicBean> dynamicBeanList = dynamicMapper.selectTopicByDynamicHeat(id);
        for (int i = 0; i < dynamicBeanList.size(); i++) {
            Long userId1 = dynamicBeanList.get(i).getUserId();
            Integer biaoshifu =  dynamicMapper.selectFavorites(userId,userId1);
            for (int j = 0; j <dynamicBeanList.size() ; j++) {
                dynamicBeanList.get(i).setIdentification(biaoshifu);
            }
        }

        for (int i = 0; i < dynamicBeanList.size(); i++) {
            Long dynamicId = dynamicBeanList.get(i).getId();
            Integer biaoshifuPraise =  dynamicMapper.selectFavoritespraiseTopic(userId,dynamicId);
            for (int j = 0; j <dynamicBeanList.size() ; j++) {
                dynamicBeanList.get(i).setIdentificationPraise(biaoshifuPraise);
            }
        }
        return dynamicBeanList;
    }

    @Override
    public List<DynamicBean> queryDynamicById(Long id) {
        //当前登录人ID
        Long userId = RequestUtil.getLoginUser().getUserId();
        List<DynamicBean> dynamicBeanList = dynamicMapper.queryDynamicById(id);
        //todo 索引越界
        Long userId1 = dynamicBeanList.get(0).getUserId();
        Integer biaoshifu =  dynamicMapper.selectFavorites(userId,userId1);
        if (biaoshifu == 0){
            dynamicBeanList.get(0).setIdentification(0);
        }if(biaoshifu !=0 ){
            dynamicBeanList.get(0).setIdentification(1);
        }

        for (int i = 0; i < dynamicBeanList.size(); i++) {
            //动态id
            Integer praiseBiaoshifu =  dynamicMapper.selectFavoritespraise(userId,id);
            if (praiseBiaoshifu == 0){
                dynamicBeanList.get(0).setIdentificationPraise(0);
            }if(praiseBiaoshifu !=0 ){
                dynamicBeanList.get(0).setIdentificationPraise(1);
            }
        }
        return dynamicBeanList;
    }

    @Override
    public int addDynamic(DynamicBean dynamicBean) {
        //判断用户是否选择话题类型,如果选择,话题下的动态数量+1
        if (dynamicBean.getTopicId() != null){
            topicMapper.addTopicNum(dynamicBean.getTopicId());
        }
        LoginAuthDto loginUser = RequestUtil.getLoginUser();
        //查询用户信息
        UmsMember member = memberService.getById(loginUser.getUserId());
        dynamicBean.setDynamicHeadPortrait(member.getIcon());
        //发布时间为当前时间
        dynamicBean.setCreateTime(new Date());
        //点赞数默认为0
        dynamicBean.setDynamicPraise(0);
        //分享数默认为0
        dynamicBean.setDynamicSharenum(0);
        //评论数默认为0
        dynamicBean.setDynamicCommentnum(0);
        //逻辑删除默认为0不删除
        dynamicBean.setDelflag(0);
        //设置图片随机宽度
        Random random = new Random();
        dynamicBean.setWidth(random.nextInt(101)+100);
        //设置图片随机高度
        dynamicBean.setHeight(random.nextInt(101)+100);
        //敏感词过滤*****
        String content = WordFilter.doFilter(dynamicBean.getDynamicContent());
        dynamicBean.setDynamicContent(content);
        return dynamicMapper.addDynamic(dynamicBean);
    }

    @Override
    public List<DynamicBean> queryDynamicTime(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //当前登录人ID
        Long userId = RequestUtil.getLoginUser().getUserId();
        //当前发布动态用户ID
        List<DynamicBean> dynamicBeanList = dynamicMapper.queryDynamicTime();
        for (int i = 0; i < dynamicBeanList.size(); i++) {
            Long userId1 = dynamicBeanList.get(i).getUserId();
            Integer biaoshifu =  dynamicMapper.selectFavorites(userId,userId1);
            for (int j = 0; j <dynamicBeanList.size() ; j++) {
                dynamicBeanList.get(i).setIdentification(biaoshifu);
            }
        }

        for (int i = 0; i < dynamicBeanList.size(); i++) {
            Long id = dynamicBeanList.get(i).getId();
            Integer biaoshifuPraise =  dynamicMapper.selectFavoritespraise(userId,id);
            for (int j = 0; j <dynamicBeanList.size() ; j++) {
                dynamicBeanList.get(i).setIdentificationPraise(biaoshifuPraise);
            }
        }

        return dynamicBeanList;
    }

    @Override
    public List<DynamicBean> queryDynamicHeat(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //当前登录人ID
        Long userId = RequestUtil.getLoginUser().getUserId();
        //当前发布动态用户ID
        List<DynamicBean> dynamicBeanList = dynamicMapper.queryDynamicHeat();
        for (int i = 0; i < dynamicBeanList.size(); i++) {
            Long userId1 = dynamicBeanList.get(i).getUserId();
            Integer biaoshifu =  dynamicMapper.selectFavorites(userId,userId1);
            for (int j = 0; j <dynamicBeanList.size() ; j++) {
                dynamicBeanList.get(i).setIdentification(biaoshifu);
            }
        }

        for (int i = 0; i < dynamicBeanList.size(); i++) {
            Long id = dynamicBeanList.get(i).getId();
            Integer biaoshifuPraise =  dynamicMapper.selectFavoritespraise(userId,id);
            for (int j = 0; j <dynamicBeanList.size() ; j++) {
                dynamicBeanList.get(i).setIdentificationPraise(biaoshifuPraise);
            }
        }
        return dynamicBeanList;
    }

    @Override
    public Integer queryDynamicCount(Long userId) {

        return dynamicMapper.queryDynamicCount(userId);
    }

    @Override
    public List<DynamicAlbumTimeBean> queryDynamicAlbumTimeBean(Long userId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //查询用户每月的数据
        List<DynamicAlbumTimeBean> dynamicAlbumTimeBeans = dynamicMapper.queryDynamicAlbumTimeBean(userId);
        for (int i = 0; i < dynamicAlbumTimeBeans.size(); i++) {
            String months = dynamicAlbumTimeBeans.get(i).getMonths();
            //将用户在每个月发布的所有照片拼接
            List<DynamicAlbumTimeBean> pictureList = dynamicMapper.queryDynamicAlbumTimePicture(months,userId);
            String pictureAll = "";
            for (int j = 0; j < pictureList.size(); j++) {
                pictureAll += pictureList.get(j).getPicture()+",";
            }
            //用户在每个月发布的所有照片
            dynamicAlbumTimeBeans.get(i).setPicture(pictureAll.substring(0,pictureAll.length()-1));
            String substring = pictureAll.substring(0, pictureAll.length() - 1);
            String[] split = substring.split(",");
            //用户在每个月发布的照片数量
            dynamicAlbumTimeBeans.get(i).setPictureNum(split.length);

        }
        return dynamicAlbumTimeBeans;
    }

    @Override
    public UserDetailsVo queryUserDetails(Long userId) {
        UserDetailsVo userDetailsVo = dynamicMapper.queryUserDetails(userId);
        //当前登录人Id
        Long memberId = RequestUtil.getLoginUser().getUserId();
        //查询登录人是否关注着该用户
        Integer biaoshifu =  dynamicMapper.selectFavorites(memberId,userId);
        if (biaoshifu == 0){
            userDetailsVo.setIdentification(0);
        }if(biaoshifu !=0 ){
            userDetailsVo.setIdentification(1);
        }
        return userDetailsVo;
    }

}
