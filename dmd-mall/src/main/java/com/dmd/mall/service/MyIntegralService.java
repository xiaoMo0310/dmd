package com.dmd.mall.service;

import com.dmd.mall.model.domain.UmsIntegrationChangeLog;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: MyIntegralService
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/10/2215:24
 */
public interface MyIntegralService {
    Integer queryMyIntegral(Long userId);

    List<UmsIntegrationChangeLog> selectIntegralIncome(Long userId,UmsIntegrationChangeLog umsIntegrationChangeLog,Integer pageNum,Integer pageSize);

    List<UmsIntegrationChangeLog> selectIntegralExpend(Long userId,UmsIntegrationChangeLog umsIntegrationChangeLog,Integer pageNum,Integer pageSize);

    Integer selectIntegralIncomeSum(Long userId);

    Integer selectIntegralExpendSum(Long userId);
}
