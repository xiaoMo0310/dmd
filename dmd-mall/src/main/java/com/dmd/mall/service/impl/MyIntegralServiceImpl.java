package com.dmd.mall.service.impl;

import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.utils.RequestUtil;
import com.dmd.mall.mapper.UmsIntegrationChangeLogMapper;
import com.dmd.mall.mapper.UmsMemberMapper;
import com.dmd.mall.model.domain.DynamicBean;
import com.dmd.mall.model.domain.UmsIntegrationChangeLog;
import com.dmd.mall.service.MyIntegralService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ChenYanbing
 * @title: MyIntegralServiceImpl
 * @projectName dmd-masters
 * @description:
 * @date 2019/10/2215:25
 */
@Service
public class MyIntegralServiceImpl implements MyIntegralService{

    @Autowired
    private UmsMemberMapper umsMemberMapper;

    @Autowired
    private UmsIntegrationChangeLogMapper umsIntegrationChangeLogMapper;

    @Override
    public Integer queryMyIntegral(Long userId) {
        int num = 0;
        //登陆信息
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        //登陆角色
        String userTypes = loginAuthDto.getUserType();
        //普通用户登录
        if (userTypes.equals("member")){
            Integer integer = umsMemberMapper.queryMyIntegral(userId);
            if (integer==0){
                num = 0;
            }
            else if (integer==null){
                num = 0;
            }
            else if(integer != 0 || integer!=null){
                num = integer;
            }
        }else if(userTypes.equals("coach")){
            Integer integerCoach = umsMemberMapper.queryMyIntegralByCoach(userId);
            if (integerCoach==0){
                num = 0;
            }
            else if (integerCoach==null){
                num = 0;
            }
            else if(integerCoach != 0 || integerCoach!=null){
                num = integerCoach;
            }
        }
        return num;
    }

    @Override
    public List<UmsIntegrationChangeLog> selectIntegralIncome(Long userId,UmsIntegrationChangeLog umsIntegrationChangeLog,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<UmsIntegrationChangeLog> objects = new ArrayList<>();
        Date startTime = umsIntegrationChangeLog.getStartTime();
        Date endTime = umsIntegrationChangeLog.getEndTime();
        //登陆信息
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        //登陆角色
        String userTypes = loginAuthDto.getUserType();
        //普通用户登陆
        if (userTypes.equals("member")){
            List<UmsIntegrationChangeLog> umsIntegrationChangeLogs = umsIntegrationChangeLogMapper.selectIntegralIncome(userId, startTime, endTime);
            objects = umsIntegrationChangeLogs;
        }else if(userTypes.equals("coach")){
            List<UmsIntegrationChangeLog> umsIntegrationChangeLogs = umsIntegrationChangeLogMapper.selectIntegralIncomeCoach(userId, startTime, endTime);
            objects = umsIntegrationChangeLogs;
        }
        return objects;
    }

    @Override
    public List<UmsIntegrationChangeLog> selectIntegralExpend(Long userId,UmsIntegrationChangeLog umsIntegrationChangeLog,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<UmsIntegrationChangeLog> objects = new ArrayList<>();
        Date startTime = umsIntegrationChangeLog.getStartTime();
        Date endTime = umsIntegrationChangeLog.getEndTime();
        //登陆信息
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        //登陆角色
        String userTypes = loginAuthDto.getUserType();
        //普通用户登陆
        if (userTypes.equals("member")){
            List<UmsIntegrationChangeLog> umsIntegrationChangeLogs = umsIntegrationChangeLogMapper.selectIntegralExpend(userId, startTime, endTime);
            objects = umsIntegrationChangeLogs;
        }else if(userTypes.equals("coach")){
            List<UmsIntegrationChangeLog> umsIntegrationChangeLogs = umsIntegrationChangeLogMapper.selectIntegralExpendCoach(userId, startTime, endTime);
            objects = umsIntegrationChangeLogs;
        }
        return objects;
    }

    @Override
    public Integer selectIntegralIncomeSum(Long userId) {
        int num = 0;
        //登陆信息
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        //登陆角色
        String userTypes = loginAuthDto.getUserType();
        //普通用户登陆
        if (userTypes.equals("member")){
            Integer integer = umsIntegrationChangeLogMapper.selectIntegralIncomeSum(userId);
            if (integer==null){
                num = 0;
            }
            else if(integer != 0 || integer!=null){
                num = integer;
            }
        }else if(userTypes.equals("coach")){
            Integer integer = umsIntegrationChangeLogMapper.selectIntegralIncomeSumByCoach(userId);
            if (integer==null){
                num = 0;
            }
            else if(integer != 0 || integer!=null){
                num = integer;
            }
        }
        return num;
    }

    @Override
    public Integer selectIntegralExpendSum(Long userId) {
        int num = 0;
        //登陆信息
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        //登陆角色
        String userTypes = loginAuthDto.getUserType();
        //普通用户登陆
        if (userTypes.equals("member")){
            Integer integer = umsIntegrationChangeLogMapper.selectIntegralExpendSum(userId);
            if (integer==null){
                num = 0;
            }
            else if(integer != 0 || integer!=null){
                num = integer;
            }
        }else if(userTypes.equals("coach")){
            Integer integer = umsIntegrationChangeLogMapper.selectIntegralExpendByCoach(userId);
            if (integer==null){
                num = 0;
            }
            else if(integer != 0 || integer!=null){
                num = integer;
            }
        }
        return num;
    }
}
