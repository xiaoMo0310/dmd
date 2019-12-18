package com.dmd.mall.service.impl;

import com.dmd.BeanUtils;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author ChenYanbing
 * @title: DynamicServiceImpl
 * @projectName dmd-master
 * @description:
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
    public List<DynamicBean> queryDynamicPage(Long userId, Integer pageNum, Integer pageSize,Integer userType) {
        PageHelper.startPage(pageNum, pageSize);
        DynamicBean dynamicBean = new DynamicBean();
        List objects = new ArrayList<>();
        //登陆信息
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        //登陆角色
        String userTypes = loginAuthDto.getUserType();
        //当前登录人
        Long userId2 = loginAuthDto.getUserId();
        if(userType==null && userTypes.equals("member")){
            List<DynamicBean> dynamicBeanListMember = dynamicMapper.queryDynamic(userId);
            for (int i = 0; i < dynamicBeanListMember.size(); i++) {
                //动态id
                Long id = dynamicBeanListMember.get(i).getId();
                //判断是否点赞
                Integer biaoshifu =  dynamicMapper.selectFavoritespraise(userId2,id);
                for (int j = 0; j <dynamicBeanListMember.size() ; j++) {
                    dynamicBeanListMember.get(i).setIdentificationPraise(biaoshifu);
                }
            }
            objects=dynamicBeanListMember;
        }else if(userType==null && userTypes.equals("coach")){
            List<DynamicBean> dynamicBeanListCoach = dynamicMapper.queryDynamicCoach(userId);
            for (int i = 0; i < dynamicBeanListCoach.size(); i++) {
                //动态id
                Long id = dynamicBeanListCoach.get(i).getId();
                Integer biaoshifu =  dynamicMapper.selectFavoritespraiseCoach(userId2,id);
                for (int j = 0; j <dynamicBeanListCoach.size() ; j++) {
                    dynamicBeanListCoach.get(i).setIdentificationPraise(biaoshifu);
                }
            }
            objects=dynamicBeanListCoach;
        }
        //查的是用户
        else if (userType == 1){
            List<DynamicBean> dynamicBeanListMember = dynamicMapper.queryDynamic(userId);
            //登录人是用户
            if(userTypes.equals("member")){
                for (int i = 0; i < dynamicBeanListMember.size(); i++) {
                    //动态id
                    Long id = dynamicBeanListMember.get(i).getId();
                    //判断是否点赞
                    Integer biaoshifu =  dynamicMapper.selectFavoritespraise(userId2,id);
                    for (int j = 0; j <dynamicBeanListMember.size() ; j++) {
                        dynamicBeanListMember.get(i).setIdentificationPraise(biaoshifu);
                    }
                }
                objects=dynamicBeanListMember;
            //登录人是教练
            }else if(userTypes.equals("coach")){
                for (int i = 0; i < dynamicBeanListMember.size(); i++) {
                    //动态id
                    Long id = dynamicBeanListMember.get(i).getId();
                    Integer biaoshifu =  dynamicMapper.selectFavoritespraiseByCoach(userId2,id);
                    for (int j = 0; j <dynamicBeanListMember.size() ; j++) {
                        dynamicBeanListMember.get(i).setIdentificationPraise(biaoshifu);
                    }
                }
                objects=dynamicBeanListMember;
            }
        }
        //查的是教练
        else if (userType == 2){
            List<DynamicBean> dynamicBeanListCoach = dynamicMapper.queryDynamicCoach(userId);
            //登录人是用户
            if(userTypes.equals("member")){
                for (int i = 0; i < dynamicBeanListCoach.size(); i++) {
                    //动态id
                    Long id = dynamicBeanListCoach.get(i).getId();
                    //判断是否点赞
                    Integer biaoshifu =  dynamicMapper.selectFavoritespraiseCoach2(userId2,id);
                    for (int j = 0; j <dynamicBeanListCoach.size() ; j++) {
                        dynamicBeanListCoach.get(i).setIdentificationPraise(biaoshifu);
                    }
                }
                objects=dynamicBeanListCoach;
                //登录人是教练
            }else if(userTypes.equals("coach")){
                for (int i = 0; i < dynamicBeanListCoach.size(); i++) {
                    //动态id
                    Long id = dynamicBeanListCoach.get(i).getId();
                    Integer biaoshifu =  dynamicMapper.selectFavoritespraiseCoach(userId2,id);
                    for (int j = 0; j <dynamicBeanListCoach.size() ; j++) {
                        dynamicBeanListCoach.get(i).setIdentificationPraise(biaoshifu);
                    }
                }
                objects=dynamicBeanListCoach;
            }
        }
        return objects;
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
    public int updateLikePraise(Long id,Integer userType) {
        //用戶ID
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        //当前登录人id
        Long userId = loginAuthDto.getUserId();
        //登录人类型
        String userTypes = loginAuthDto.getUserType();
        //动态是用户发布
        if (userType == 1){
            //登录人是用户
            if(userTypes.equals("member")){
                //判断登陆用户是否给该用户所发动态进行点赞
                Integer or = dynamicMapper.selectUserIdBypraise(id,userId);
                //未点赞
                if(or == 0){
                    dynamicMapper.insertUserPraise(id,userId);
                }
                //点过赞
                if(or != 0){
                    dynamicMapper.updateUserPraiseLike(id,userId);
                }
            //登录人是教练
            }else if(userTypes.equals("coach")){
                //判断登陆教练是否给该用户所发动态进行点赞
                Integer or = dynamicMapper.selectUserIdBypraiseCoach(id,userId);
                //未点赞
                if(or == 0){
                    dynamicMapper.insertUserPraiseCoach(id,userId);
                }
                //点过赞
                if(or != 0){
                    dynamicMapper.updateUserPraiseLikeCoach(id,userId);
                }
            }
        }
        //动态是教练发布
        else if (userType == 2){
            //登录人是用户
            if(userTypes.equals("member")){
                //判断登陆用户是否给该教练所发动态进行点赞
                Integer or = dynamicMapper.selectUserIdBypraiseByCoach(id,userId);
                //未点赞
                if(or == 0){
                    dynamicMapper.insertUserPraiseByCoach(id,userId);
                }
                //点过赞
                if(or != 0){
                    dynamicMapper.updateUserPraiseLikeByCoach(id,userId);
                }
                //登录人是教练
            }else if(userTypes.equals("coach")){
                //判断登陆教练是否给该教练所发动态进行点赞
                Integer or = dynamicMapper.selectUserIdBypraiseCoach2(id,userId);
                //未点赞
                if(or == 0){
                    dynamicMapper.insertUserPraiseCoach2(id,userId);
                }
                //点过赞
                if(or != 0){
                    dynamicMapper.updateUserPraiseLikeCoach2(id,userId);
                }
            }
        }
        int result = dynamicMapper.updateLikePraise(id);
        if(result > 0){
            DynamicBean dynamicBean = dynamicMapper.selectDynamicById(id);
            MessageDto messageDto = new MessageDto();
            messageDto.setTitle("动态点赞消息");
            messageDto.setContent(dynamicBean.getDynamicContent());
            messageDto.setJumpAddress(dynamicBean.getId() + "");
            //发送消息 todo 教练待做
            noticeService.saveNoticeMessage(loginAuthDto, dynamicBean.getUserId(), "member", 2, messageDto);
        }
        return result;
    }

    @Override
    public int updateCancelPraise(Long id,Integer userType) {
        //用戶ID
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        //当前登录人id
        Long userId = loginAuthDto.getUserId();
        //登录人类型
        String userTypes = loginAuthDto.getUserType();
        //该动态是用户发布
        if (userType == 1){
            //登录人是用户
            if(userTypes.equals("member")){
                //去修改用户点赞了用户的数据
                dynamicMapper.updateUserPraise(id,userId);
            //登录人是教练
            }else if(userTypes.equals("coach")){
                //去修改教练点赞了用户的数据
                dynamicMapper.updateUserPraiseCoach(id,userId);
            }
        //该动态是教练发布
        }else if(userType == 2){
            //登录人是用户
            if(userTypes.equals("member")){
                //去修改用户点赞了教练的数据
                dynamicMapper.updateUserPraise2(id,userId);
                //登录人是教练
            }else if(userTypes.equals("coach")){
                //去修改教练点赞了教练的数据
                dynamicMapper.updateUserPraiseCoach2(id,userId);
            }
        }
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
        List<DynamicBean> objects = new ArrayList<>();
        //登陆信息
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        //当前登录人
        Long userId = loginAuthDto.getUserId();
        //登录角色
        String userTypes = loginAuthDto.getUserType();
        //用户登录
        if (userTypes.equals("member")){
            //用户所发动态按照时间排序
            List<DynamicBean> dynamicBeanList = dynamicMapper.queryTopicByDynamicTime(id);
            //查询用户是否关注用户
            for (int i = 0; i < dynamicBeanList.size(); i++) {
                //用户id
                Long userId1 = dynamicBeanList.get(i).getUserId();
                Integer biaoshifu =  dynamicMapper.selectFavorites(userId,userId1);
                for (int j = 0; j <dynamicBeanList.size() ; j++) {
                    dynamicBeanList.get(i).setIdentification(biaoshifu);
                }
            }
            //查询用户是否点赞用户
            for (int i = 0; i < dynamicBeanList.size(); i++) {
                //用户所发动态id
                Long dynamicId = dynamicBeanList.get(i).getId();
                Integer biaoshifuPraise =  dynamicMapper.selectFavoritespraiseTopic(userId,dynamicId);
                for (int j = 0; j <dynamicBeanList.size() ; j++) {
                    dynamicBeanList.get(i).setIdentificationPraise(biaoshifuPraise);
                }
            }

            //教练所发动态按照时间排序
            List<DynamicBean> dynamicBeanCoachList = dynamicMapper.queryTopicByDynamicForCoachTime(id);
            //查询用户是否关注教练
            for (int i = 0; i < dynamicBeanCoachList.size(); i++) {
                //教练id
                Long userId1 = dynamicBeanCoachList.get(i).getUserId();
                Integer biaoshifu =  dynamicMapper.selectFavoritesCoach(userId,userId1);
                for (int j = 0; j <dynamicBeanCoachList.size() ; j++) {
                    dynamicBeanCoachList.get(i).setIdentification(biaoshifu);
                }
            }
            //查询用户是否点赞教练
            for (int i = 0; i < dynamicBeanCoachList.size(); i++) {
                //教练所发动态id
                Long dynamicId = dynamicBeanCoachList.get(i).getId();
                Integer praiseBiaoshifu =  dynamicMapper.selectFavoritespraiseCoach2(userId,dynamicId);
                for (int j = 0; j <dynamicBeanCoachList.size() ; j++) {
                    dynamicBeanCoachList.get(i).setIdentificationPraise(praiseBiaoshifu);
                }

            }
            //数据合并
            dynamicBeanList.addAll(dynamicBeanCoachList);
            objects = dynamicBeanList;
            //按照时间倒序排序
            objects.sort((o1, o2) -> o2.getCreateTime().compareTo(o1.getCreateTime()));
        }
        //教练登陆
        else if (userTypes.equals("coach")){
            //用户所发动态按照时间排序
            List<DynamicBean> dynamicBeanList = dynamicMapper.queryTopicByDynamicTime(id);
            //查询教练是否关注用户
            for (int i = 0; i < dynamicBeanList.size(); i++) {
                //用户id
                Long userId1 = dynamicBeanList.get(i).getUserId();
                Integer biaoshifu =  dynamicMapper.selectFavoritesByCoach(userId,userId1);
                for (int j = 0; j <dynamicBeanList.size() ; j++) {
                    dynamicBeanList.get(i).setIdentification(biaoshifu);
                }
            }
            //查询教练是否点赞用户
            for (int i = 0; i < dynamicBeanList.size(); i++) {
                //用户所发动态id
                Long dynamicId = dynamicBeanList.get(i).getId();
                Integer biaoshifuPraise =  dynamicMapper.selectFavoritespraiseByCoach(userId,dynamicId);
                for (int j = 0; j <dynamicBeanList.size() ; j++) {
                    dynamicBeanList.get(i).setIdentificationPraise(biaoshifuPraise);
                }
            }
            //教练所发动态按照时间排序
            List<DynamicBean> dynamicBeanCoachList = dynamicMapper.queryTopicByDynamicForCoachTime(id);
            //查询用户是否关注教练
            for (int i = 0; i < dynamicBeanCoachList.size(); i++) {
                //教练id
                Long userId1 = dynamicBeanCoachList.get(i).getUserId();
                Integer biaoshifu =  dynamicMapper.selectFavoritesCoach2(userId,userId1);
                for (int j = 0; j <dynamicBeanCoachList.size() ; j++) {
                    dynamicBeanCoachList.get(i).setIdentification(biaoshifu);
                }
            }
            //查询教练是否点赞教练
            for (int i = 0; i < dynamicBeanCoachList.size(); i++) {
                //教练所发动态id
                Long dynamicId = dynamicBeanCoachList.get(i).getId();
                Integer praiseBiaoshifu =  dynamicMapper.selectFavoritespraiseCoach(userId,dynamicId);
                for (int j = 0; j <dynamicBeanCoachList.size() ; j++) {
                    dynamicBeanCoachList.get(i).setIdentificationPraise(praiseBiaoshifu);
                }
            }
            //数据合并
            dynamicBeanList.addAll(dynamicBeanCoachList);
            objects = dynamicBeanList;
            //按照时间倒序排序
            objects.sort((o1, o2) -> o2.getCreateTime().compareTo(o1.getCreateTime()));
        }

        return objects;
    }

    @Override
    public List<DynamicBean> selectTopicByDynamicHeat(Integer id,Integer pageNum,Integer pageSize) {
        List<DynamicBean> objects = new ArrayList<>();
        //登陆信息
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        //当前登录人
        Long userId = loginAuthDto.getUserId();
        //登录角色
        String userTypes = loginAuthDto.getUserType();
        //用户登录
        if (userTypes.equals("member")){
            //用户所发动态按照热度(点赞量)排序
            List<DynamicBean> dynamicBeanList = dynamicMapper.selectTopicByDynamicHeat(id);
            //查询用户是否关注用户
            for (int i = 0; i < dynamicBeanList.size(); i++) {
                //用户id
                Long userId1 = dynamicBeanList.get(i).getUserId();
                Integer biaoshifu =  dynamicMapper.selectFavorites(userId,userId1);
                for (int j = 0; j <dynamicBeanList.size() ; j++) {
                    dynamicBeanList.get(i).setIdentification(biaoshifu);
                }
            }
            //查询用户是否点赞用户
            for (int i = 0; i < dynamicBeanList.size(); i++) {
                //用户所发动态id
                Long dynamicId = dynamicBeanList.get(i).getId();
                Integer biaoshifuPraise =  dynamicMapper.selectFavoritespraiseTopic(userId,dynamicId);
                for (int j = 0; j <dynamicBeanList.size() ; j++) {
                    dynamicBeanList.get(i).setIdentificationPraise(biaoshifuPraise);
                }
            }

            //教练所发动态按照热度(点赞量)排序
            List<DynamicBean> dynamicBeanCoachList = dynamicMapper.selectTopicByDynamicByCoachHeat(id);
            //查询用户是否关注教练
            for (int i = 0; i < dynamicBeanCoachList.size(); i++) {
                //教练id
                Long userId1 = dynamicBeanCoachList.get(i).getUserId();
                Integer biaoshifu =  dynamicMapper.selectFavoritesCoach(userId,userId1);
                for (int j = 0; j <dynamicBeanCoachList.size() ; j++) {
                    dynamicBeanCoachList.get(i).setIdentification(biaoshifu);
                }
            }
            //查询用户是否点赞教练
            for (int i = 0; i < dynamicBeanCoachList.size(); i++) {
                //教练所发动态id
                Long dynamicId = dynamicBeanCoachList.get(i).getId();
                Integer praiseBiaoshifu =  dynamicMapper.selectFavoritespraiseCoach2(userId,dynamicId);
                for (int j = 0; j <dynamicBeanCoachList.size() ; j++) {
                    dynamicBeanCoachList.get(i).setIdentificationPraise(praiseBiaoshifu);
                }

            }
            //数据合并
            dynamicBeanList.addAll(dynamicBeanCoachList);
            objects = dynamicBeanList;
            //按照热度(点赞量)倒序排序
            objects.sort((o1, o2) -> o2.getDynamicPraise().compareTo(o1.getDynamicPraise()));
        }
        //教练登陆
        else if (userTypes.equals("coach")){
            //用户所发动态按照热度(点赞量)排序
            List<DynamicBean> dynamicBeanList = dynamicMapper.selectTopicByDynamicHeat(id);
            //查询教练是否关注用户
            for (int i = 0; i < dynamicBeanList.size(); i++) {
                //用户id
                Long userId1 = dynamicBeanList.get(i).getUserId();
                Integer biaoshifu =  dynamicMapper.selectFavoritesByCoach(userId,userId1);
                for (int j = 0; j <dynamicBeanList.size() ; j++) {
                    dynamicBeanList.get(i).setIdentification(biaoshifu);
                }
            }
            //查询教练是否点赞用户
            for (int i = 0; i < dynamicBeanList.size(); i++) {
                //用户所发动态id
                Long dynamicId = dynamicBeanList.get(i).getId();
                Integer biaoshifuPraise =  dynamicMapper.selectFavoritespraiseByCoach(userId,dynamicId);
                for (int j = 0; j <dynamicBeanList.size() ; j++) {
                    dynamicBeanList.get(i).setIdentificationPraise(biaoshifuPraise);
                }
            }
            //教练所发动态按照热度(点赞量)排序
            List<DynamicBean> dynamicBeanCoachList = dynamicMapper.selectTopicByDynamicByCoachHeat(id);
            //查询用户是否关注教练
            for (int i = 0; i < dynamicBeanCoachList.size(); i++) {
                //教练id
                Long userId1 = dynamicBeanCoachList.get(i).getUserId();
                Integer biaoshifu =  dynamicMapper.selectFavoritesCoach2(userId,userId1);
                for (int j = 0; j <dynamicBeanCoachList.size() ; j++) {
                    dynamicBeanCoachList.get(i).setIdentification(biaoshifu);
                }
            }
            //查询教练是否点赞教练
            for (int i = 0; i < dynamicBeanCoachList.size(); i++) {
                //教练所发动态id
                Long dynamicId = dynamicBeanCoachList.get(i).getId();
                Integer praiseBiaoshifu =  dynamicMapper.selectFavoritespraiseCoach(userId,dynamicId);
                for (int j = 0; j <dynamicBeanCoachList.size() ; j++) {
                    dynamicBeanCoachList.get(i).setIdentificationPraise(praiseBiaoshifu);
                }
            }
            //数据合并
            dynamicBeanList.addAll(dynamicBeanCoachList);
            objects = dynamicBeanList;
            //按照热度(点赞量)倒序排序
            objects.sort((o1, o2) -> o2.getDynamicPraise().compareTo(o1.getDynamicPraise()));
        }

        return objects;
    }

    @Override
    public List<DynamicBean> queryDynamicById(Long id,Integer userType) {
        List objects = new ArrayList<>();
        //登陆信息
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        //当前登录人
        Long userId = loginAuthDto.getUserId();
        //根据登陆类型查询动态详情1==普通用户 2==教练
        String userTypes = loginAuthDto.getUserType();
        //普通用户发布：
        if(userType==1){
            //用户发布用户登陆
            List<DynamicBean> dynamicBeanList = dynamicMapper.queryDynamicById(id);
            if (userTypes.equals("member")){
                Long userId1 = dynamicBeanList.get(0).getUserId();
                Integer biaoshifu =  dynamicMapper.selectFavorites(userId,userId1);
                if (biaoshifu == 0){
                    dynamicBeanList.get(0).setIdentification(0);
                }if(biaoshifu !=0 ){
                    dynamicBeanList.get(0).setIdentification(1);
                }
                Integer praiseBiaoshifu =  dynamicMapper.selectFavoritespraise(userId,id);
                if (praiseBiaoshifu == 0){
                    dynamicBeanList.get(0).setIdentificationPraise(0);
                }if(praiseBiaoshifu !=0 ){
                    dynamicBeanList.get(0).setIdentificationPraise(1);
                }
                objects=dynamicBeanList;
            }
            //用户发布教练登陆
            else if (userTypes.equals("coach")){
                Long userId1 = dynamicBeanList.get(0).getUserId();
                Integer biaoshifu =  dynamicMapper.selectFavoritesByCoach(userId,userId1);
                if (biaoshifu == 0){
                    dynamicBeanList.get(0).setIdentification(0);
                }if(biaoshifu !=0 ){
                    dynamicBeanList.get(0).setIdentification(1);
                }
                Integer praiseBiaoshifu =  dynamicMapper.selectFavoritespraiseByCoach(userId,id);
                if (praiseBiaoshifu == 0){
                    dynamicBeanList.get(0).setIdentificationPraise(0);
                }if(praiseBiaoshifu !=0 ){
                    dynamicBeanList.get(0).setIdentificationPraise(1);
                }
                objects=dynamicBeanList;
            }

        }
        //教练发布用户登陆
        else if(userType==2){
            //教练发布用户登陆
            List<DynamicBean> dynamicBeanList = dynamicMapper.queryDynamicByCoachById(id);
            if(userTypes.equals("member")){
                Long userId1 = dynamicBeanList.get(0).getUserId();
                Integer biaoshifu =  dynamicMapper.selectFavoritesCoach(userId,userId1);
                if (biaoshifu == 0){
                    dynamicBeanList.get(0).setIdentification(0);
                }if(biaoshifu !=0 ){
                    dynamicBeanList.get(0).setIdentification(1);
                }
                Integer praiseBiaoshifu =  dynamicMapper.selectFavoritespraiseCoach2(userId,id);
                if (praiseBiaoshifu == 0){
                    dynamicBeanList.get(0).setIdentificationPraise(0);
                }if(praiseBiaoshifu !=0 ){
                    dynamicBeanList.get(0).setIdentificationPraise(1);
                }
                objects=dynamicBeanList;
            }
            //教练发布教练登陆
            else if (userTypes.equals("coach")){
                Long userId1 = dynamicBeanList.get(0).getUserId();
                Integer biaoshifu =  dynamicMapper.selectFavoritesCoach2(userId,userId1);
                if (biaoshifu == 0){
                    dynamicBeanList.get(0).setIdentification(0);
                }if(biaoshifu !=0 ){
                    dynamicBeanList.get(0).setIdentification(1);
                }
                Integer praiseBiaoshifu =  dynamicMapper.selectFavoritespraiseCoach(userId,id);
                if (praiseBiaoshifu == 0){
                    dynamicBeanList.get(0).setIdentificationPraise(0);
                }if(praiseBiaoshifu !=0 ){
                    dynamicBeanList.get(0).setIdentificationPraise(1);
                }
                objects=dynamicBeanList;
            }
        }
        return objects;
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
        //登陆信息
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        //判断登陆角色
        String userType = loginAuthDto.getUserType();
        //登录人id
        dynamicBean.setUserId(loginAuthDto.getUserId());
        if(userType.equals("member")){
            dynamicBean.setUserType(1);
        }else if(userType.equals("coach")){
            dynamicBean.setUserType(2);
        }
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
        int countNum = 0;
        //登陆信息
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        //登陆角色
        String userType = loginAuthDto.getUserType();
        //当前登录人
        Long userId1 = loginAuthDto.getUserId();
        //普通用户登录
        if(userType.equals("member")){
            Integer count = dynamicMapper.queryDynamicCount(userId);
            countNum = count;
        }
        //教练登陆
        if(userType.equals("coach")){
            Integer count = dynamicMapper.queryDynamicCountByCoach(userId);
            countNum = count;
        }

        return countNum;
    }

    @Override
    public List<DynamicAlbumTimeBean> queryDynamicAlbumTimeBean(Long userId, Integer pageNum, Integer pageSize,Integer userType) {
        PageHelper.startPage(pageNum, pageSize);
        List list = new ArrayList<>();
        //登陆信息
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        //登陆角色
        String userTypes = loginAuthDto.getUserType();
        //当前登录人
        Long userId1 = loginAuthDto.getUserId();
        //看的是用户的资料
        if (userType == 1){
            //查询该用户每月的数据
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
            list = dynamicAlbumTimeBeans;
        //看的是教练
        }else if(userType == 2){
            //查询该用户每月的数据
            List<DynamicAlbumTimeBean> dynamicAlbumTimeBeans = dynamicMapper.queryDynamicAlbumTimeByCoachBean(userId);
            for (int i = 0; i < dynamicAlbumTimeBeans.size(); i++) {
                String months = dynamicAlbumTimeBeans.get(i).getMonths();
                //将教练在每个月发布的所有照片拼接
                List<DynamicAlbumTimeBean> pictureList = dynamicMapper.queryDynamicAlbumTimePictureByCoach(months,userId);
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
            list = dynamicAlbumTimeBeans;
        }

        return list;
    }

    @Override
    public UserDetailsVo queryUserDetails(Long userId,Integer userType) {
        //登陆信息
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        //当前登录人
        Long memberId = loginAuthDto.getUserId();
        //根据登陆类型查询动态详情1==普通用户 2==教练
        String userTypes = loginAuthDto.getUserType();
        UserDetailsVo userDetailsVo1 = new UserDetailsVo();
        //普通用户发布：
        if(userType==1){
            //用户发布用户登陆
            UserDetailsVo userDetailsVo = dynamicMapper.queryUserDetails(userId);
            //用户登录
            if (userTypes.equals("member")){
                Integer biaoshifu =  dynamicMapper.selectFavorites(memberId,userId);
                if (biaoshifu == 0){
                    userDetailsVo.setIdentification(0);
                }if(biaoshifu !=0 ){
                    userDetailsVo.setIdentification(1);
                }
                userDetailsVo1 = userDetailsVo;
            }
            //用户发布教练登陆
            else if (userTypes.equals("coach")){
                Integer biaoshifu =  dynamicMapper.selectFavoritesByCoach(memberId,userId);
                if (biaoshifu == 0){
                    userDetailsVo.setIdentification(0);
                }if(biaoshifu !=0 ){
                    userDetailsVo.setIdentification(1);
                }
                userDetailsVo1 = userDetailsVo;
            }

        }
        //教练发布用户登陆
        else if(userType==2){
            //教练发布用户登陆
            UserDetailsVo userDetailsVo = dynamicMapper.queryUserDetailsByCoach(userId);
            //用户登录
            if(userTypes.equals("member")){
                Integer biaoshifu =  dynamicMapper.selectFavoritesCoach(memberId,userId);
                if (biaoshifu == 0){
                    userDetailsVo.setIdentification(0);
                }if(biaoshifu !=0 ){
                    userDetailsVo.setIdentification(1);
                }
                userDetailsVo1 = userDetailsVo;
            }
            //教练发布教练登陆
            else if (userTypes.equals("coach")){
                Integer biaoshifu =  dynamicMapper.selectFavoritesCoach2(memberId,userId);
                if (biaoshifu == 0){
                    userDetailsVo.setIdentification(0);
                }if(biaoshifu !=0 ){
                    userDetailsVo.setIdentification(1);
                }
                userDetailsVo1 = userDetailsVo;
            }
        }
        return userDetailsVo1;
    }

}
