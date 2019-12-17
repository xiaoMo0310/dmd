package com.dmd.mall.service.impl;

import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.utils.RequestUtil;
import com.dmd.mall.mapper.*;
import com.dmd.mall.model.domain.*;
import com.dmd.mall.model.vo.PmsCertificateVo;
import com.dmd.mall.service.HomeSearchService;
import com.dmd.mall.service.PmsCourseProductService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ChenYanbing
 * @title: HomeSearchServiceImpl
 * @projectName dmd-masters
 * @description:
 * @date 2019/10/1818:17
 */
@Service
public class HomeSearchServiceImpl implements HomeSearchService{

    @Autowired
    private HomeSearchMapper homeSearchMapper;

    @Autowired
    private DynamicMapper dynamicMapper;

    @Autowired
    private PmsProductMapper pmsProductMapper;

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private PmsCertificateMapper pmsCertificateMapper;

    @Autowired
    private PmsCourseProductMapper pmsCourseProductMapper;

    @Autowired
    private PmsCourseProductService courseProductService;

    @Override
    public List<DynamicBean> queryDynamic(Long userId, String content, Integer searchType,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<DynamicBean> objects = new ArrayList<>();
        //登陆信息
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        //登录角色
        String userTypes = loginAuthDto.getUserType();
        //用户登录
        if (userTypes.equals("member")){
            homeSearchMapper.addHomeSearchRecord(userId,content,searchType);
            //查用户所发
            List<DynamicBean> dynamicBeanList = dynamicMapper.queryDynamicByContent(content);
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
            //查教练所发
            List<DynamicBean> dynamicBeanListByCoach = dynamicMapper.queryDynamicByContentByCoach(content);
            //查询用户是否关注教练
            for (int i = 0; i < dynamicBeanListByCoach.size(); i++) {
                //教练id
                Long userId1 = dynamicBeanListByCoach.get(i).getUserId();
                Integer biaoshifu =  dynamicMapper.selectFavoritesCoach(userId,userId1);
                for (int j = 0; j <dynamicBeanListByCoach.size() ; j++) {
                    dynamicBeanListByCoach.get(i).setIdentification(biaoshifu);
                }
            }
            //查询用户是否点赞教练
            for (int i = 0; i < dynamicBeanListByCoach.size(); i++) {
                //教练所发动态id
                Long dynamicId = dynamicBeanListByCoach.get(i).getId();
                Integer praiseBiaoshifu =  dynamicMapper.selectFavoritespraiseCoach2(userId,dynamicId);
                for (int j = 0; j <dynamicBeanListByCoach.size() ; j++) {
                    dynamicBeanListByCoach.get(i).setIdentificationPraise(praiseBiaoshifu);
                }

            }
            //数据合并
            dynamicBeanList.addAll(dynamicBeanListByCoach);
            objects = dynamicBeanList;
            //按照时间倒序排序
            objects.sort((o1, o2) -> o2.getCreateTime().compareTo(o1.getCreateTime()));
        //教练登陆
        }else if(userTypes.equals("coach")){
            homeSearchMapper.addHomeSearchRecordByCoach(userId,content,searchType);
            //查用户所发
            List<DynamicBean> dynamicBeanList = dynamicMapper.queryDynamicByContent(content);
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
            //查教练所发
            List<DynamicBean> dynamicBeanListByCoach = dynamicMapper.queryDynamicByContentByCoach(content);
            for (int i = 0; i < dynamicBeanListByCoach.size(); i++) {
                //教练id
                Long userId1 = dynamicBeanListByCoach.get(i).getUserId();
                Integer biaoshifu =  dynamicMapper.selectFavoritesCoach2(userId,userId1);
                for (int j = 0; j <dynamicBeanListByCoach.size() ; j++) {
                    dynamicBeanListByCoach.get(i).setIdentification(biaoshifu);
                }
            }
            //查询教练是否点赞教练
            for (int i = 0; i < dynamicBeanListByCoach.size(); i++) {
                //教练所发动态id
                Long dynamicId = dynamicBeanListByCoach.get(i).getId();
                Integer praiseBiaoshifu =  dynamicMapper.selectFavoritespraiseCoach(userId,dynamicId);
                for (int j = 0; j <dynamicBeanListByCoach.size() ; j++) {
                    dynamicBeanListByCoach.get(i).setIdentificationPraise(praiseBiaoshifu);
                }
            }
            //数据合并
            dynamicBeanList.addAll(dynamicBeanListByCoach);
            objects = dynamicBeanList;
            //按照时间倒序排序
            objects.sort((o1, o2) -> o2.getCreateTime().compareTo(o1.getCreateTime()));
        }
        return objects;
    }

    @Override
    public List<PmsProduct> queryPmsProduct(Long userId, String content, Integer searchType,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        homeSearchMapper.addHomeSearchRecord(userId,content,searchType);
        return pmsProductMapper.queryPmsProductByContent(content);
    }

    @Override
    public List<TopicBean> queryTopic(Long userId, String content, Integer searchType,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //登陆信息
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        //登录角色
        String userTypes = loginAuthDto.getUserType();
        //用户登录
        if (userTypes.equals("member")){
            homeSearchMapper.addHomeSearchRecord(userId,content,searchType);
            //教练登陆
        }else if(userTypes.equals("coach")){
            homeSearchMapper.addHomeSearchRecordByCoach(userId,content,searchType);
        }
        return topicMapper.queryTopicByContent(content);
    }

    @Override
    public List<HomeSearchRecordBean> queryHistory(Long userId) {
        List objects = new ArrayList<>();
        //登陆信息
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        //登录角色
        String userTypes = loginAuthDto.getUserType();
        if (userTypes.equals("member")){
            //查用户历史搜索记录
            List<HomeSearchRecordBean> homeSearchRecordBeans = homeSearchMapper.queryHistory(userId);
            objects = homeSearchRecordBeans;
        }else if (userTypes.equals("coach")){
            //查教练历史搜索记录
            List<HomeSearchRecordBean> homeSearchRecordBeans = homeSearchMapper.queryHistoryByCoach(userId);
            objects = homeSearchRecordBeans;
        }
        return objects;
    }

    @Override
    public int deleteHistoryByUserid(Long userid) {
        List objects = new ArrayList<>();
        //登陆信息
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        //登录角色
        String userTypes = loginAuthDto.getUserType();
        int count = 1;
        if (userTypes.equals("member")){
            //删除用户历史搜索记录
            int i = homeSearchMapper.deleteHistoryByUserid(userid);
            count = i;
        }else if (userTypes.equals("coach")){
            //删除教练历史搜索记录
            int i = homeSearchMapper.deleteHistoryByUseridCoach(userid);
            count = i;
        }
        return 1;
    }

    @Override
    public List<PmsCourseProduct> queryPmsCourseProduct(Long userId, String content, Integer searchType, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //登陆信息
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        //登录角色
        String userTypes = loginAuthDto.getUserType();
        //用户登录
        if (userTypes.equals("member")){
            homeSearchMapper.addHomeSearchRecord(userId,content,searchType);
            //教练登陆
        }else if(userTypes.equals("coach")){
            homeSearchMapper.addHomeSearchRecordByCoach(userId,content,searchType);
        }
        return pmsCourseProductMapper.queryPmsCourseProduct(content);
    }

    @Override
    public List<DynamicBean> queryDynamicContent(Long userId, String content, Integer searchType, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<DynamicBean> objects = new ArrayList<>();
        //登陆信息
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        //登录角色
        String userTypes = loginAuthDto.getUserType();
        //用户登录
        if (userTypes.equals("member")){
            //查用户所发
            List<DynamicBean> dynamicBeanList = dynamicMapper.queryDynamicByContent(content);
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
            //查教练所发
            List<DynamicBean> dynamicBeanListByCoach = dynamicMapper.queryDynamicByContentByCoach(content);
            //查询用户是否关注教练
            for (int i = 0; i < dynamicBeanListByCoach.size(); i++) {
                //教练id
                Long userId1 = dynamicBeanListByCoach.get(i).getUserId();
                Integer biaoshifu =  dynamicMapper.selectFavoritesCoach(userId,userId1);
                for (int j = 0; j <dynamicBeanListByCoach.size() ; j++) {
                    dynamicBeanListByCoach.get(i).setIdentification(biaoshifu);
                }
            }
            //查询用户是否点赞教练
            for (int i = 0; i < dynamicBeanListByCoach.size(); i++) {
                //教练所发动态id
                Long dynamicId = dynamicBeanListByCoach.get(i).getId();
                Integer praiseBiaoshifu =  dynamicMapper.selectFavoritespraiseCoach2(userId,dynamicId);
                for (int j = 0; j <dynamicBeanListByCoach.size() ; j++) {
                    dynamicBeanListByCoach.get(i).setIdentificationPraise(praiseBiaoshifu);
                }

            }
            //数据合并
            dynamicBeanList.addAll(dynamicBeanListByCoach);
            objects = dynamicBeanList;
            //按照时间倒序排序
            objects.sort((o1, o2) -> o2.getCreateTime().compareTo(o1.getCreateTime()));
            //教练登陆
        }else if(userTypes.equals("coach")){
            //查用户所发
            List<DynamicBean> dynamicBeanList = dynamicMapper.queryDynamicByContent(content);
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
            //查教练所发
            List<DynamicBean> dynamicBeanListByCoach = dynamicMapper.queryDynamicByContentByCoach(content);
            for (int i = 0; i < dynamicBeanListByCoach.size(); i++) {
                //教练id
                Long userId1 = dynamicBeanListByCoach.get(i).getUserId();
                Integer biaoshifu =  dynamicMapper.selectFavoritesCoach2(userId,userId1);
                for (int j = 0; j <dynamicBeanListByCoach.size() ; j++) {
                    dynamicBeanListByCoach.get(i).setIdentification(biaoshifu);
                }
            }
            //查询教练是否点赞教练
            for (int i = 0; i < dynamicBeanListByCoach.size(); i++) {
                //教练所发动态id
                Long dynamicId = dynamicBeanListByCoach.get(i).getId();
                Integer praiseBiaoshifu =  dynamicMapper.selectFavoritespraiseCoach(userId,dynamicId);
                for (int j = 0; j <dynamicBeanListByCoach.size() ; j++) {
                    dynamicBeanListByCoach.get(i).setIdentificationPraise(praiseBiaoshifu);
                }
            }
            //数据合并
            dynamicBeanList.addAll(dynamicBeanListByCoach);
            objects = dynamicBeanList;
            //按照时间倒序排序
            objects.sort((o1, o2) -> o2.getCreateTime().compareTo(o1.getCreateTime()));
        }
        return objects;
    }

    @Override
    public List<PmsCourseProduct> queryPmsCourseProductContent(Long userId, String content, Integer searchType, Integer pageNum, Integer pageSize) {
        return pmsCourseProductMapper.queryPmsCourseProduct(content);
    }

    @Override
    public List<TopicBean> queryTopicContent(Long userId, String content, Integer searchType, Integer pageNum, Integer pageSize) {
        return topicMapper.queryTopicByContent(content);
    }

    @Override
    public List<PmsCertificateVo> queryPmsCertificate(Long userId, String content, Integer searchType, Integer pageNum, Integer pageSize) {
        List<PmsCertificate> pmsCertificates = pmsCertificateMapper.queryPmsCertificate(content);
        PageHelper.startPage(pageNum, pageSize);
        //登陆信息
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        //登录角色
        String userTypes = loginAuthDto.getUserType();
        //用户登录
        if (userTypes.equals("member")){
            homeSearchMapper.addHomeSearchRecord(userId,content,searchType);
            //教练登陆
        }else if(userTypes.equals("coach")){
            homeSearchMapper.addHomeSearchRecordByCoach(userId,content,searchType);
        }
        return pmsCertificates.stream().map(pmsCertificate -> {
            PmsCertificateVo pmsCertificateVo = new PmsCertificateVo();
            BeanUtils.copyProperties(pmsCertificate, pmsCertificateVo);
            //查询是否有商品信息
            long count = courseProductService.findCertificateProductNum(1, pmsCertificate.getId());
            pmsCertificateVo.setProductNum(count);
            return pmsCertificateVo;
        }).collect(Collectors.toList());
    }

    @Override
    public List<PmsCertificateVo> queryPmsCertificateCount(Long userId, String content, Integer searchType, Integer pageNum, Integer pageSize) {
        List<PmsCertificate> pmsCertificates = pmsCertificateMapper.queryPmsCertificate(content);
        return pmsCertificates.stream().map(pmsCertificate -> {
            PmsCertificateVo pmsCertificateVo = new PmsCertificateVo();
            BeanUtils.copyProperties(pmsCertificate, pmsCertificateVo);
            //查询是否有商品信息
            long count = courseProductService.findCertificateProductNum(1, pmsCertificate.getId());
            pmsCertificateVo.setProductNum(count);
            return pmsCertificateVo;
        }).collect(Collectors.toList());
    }

    /*@Override
    public List<PmsCourseProduct> queryPmsCourseProductByType(Long userId, String content, Integer searchType, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        homeSearchMapper.addHomeSearchRecord(userId,content,searchType);
        return pmsCourseProductMapper.queryPmsCourseProductByType(content);
    }

    @Override
    public List<PmsCourseProduct> queryPmsCourseProductByTypeContent(Long userId, String content, Integer searchType, Integer pageNum, Integer pageSize) {
        return pmsCourseProductMapper.queryPmsCourseProductByType(content);
    }*/


}
