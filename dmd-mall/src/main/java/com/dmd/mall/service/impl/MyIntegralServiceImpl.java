package com.dmd.mall.service.impl;

import com.dmd.mall.mapper.UmsIntegrationChangeLogMapper;
import com.dmd.mall.mapper.UmsMemberMapper;
import com.dmd.mall.model.domain.UmsIntegrationChangeLog;
import com.dmd.mall.service.MyIntegralService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return umsMemberMapper.queryMyIntegral(userId);
    }

    @Override
    public List<UmsIntegrationChangeLog> selectIntegralIncome(Long userId,UmsIntegrationChangeLog umsIntegrationChangeLog,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Date startTime = umsIntegrationChangeLog.getStartTime();
        Date endTime = umsIntegrationChangeLog.getEndTime();
        return umsIntegrationChangeLogMapper.selectIntegralIncome(userId,startTime,endTime);
    }

    @Override
    public List<UmsIntegrationChangeLog> selectIntegralExpend(Long userId,UmsIntegrationChangeLog umsIntegrationChangeLog,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Date startTime = umsIntegrationChangeLog.getStartTime();
        Date endTime = umsIntegrationChangeLog.getEndTime();
        return umsIntegrationChangeLogMapper.selectIntegralExpend(userId,startTime,endTime);
    }

    @Override
    public Integer selectIntegralIncomeSum(Long userId) {
        return umsIntegrationChangeLogMapper.selectIntegralIncomeSum(userId);
    }

    @Override
    public Integer selectIntegralExpendSum(Long userId) {
        return umsIntegrationChangeLogMapper.selectIntegralExpendSum(userId);
    }
}
