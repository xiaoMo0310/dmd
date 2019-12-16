package com.dmd.mall.service.impl;

import com.dmd.mall.mapper.IntegralRuleMapper;
import com.dmd.mall.model.domain.IntegralRuleBean;
import com.dmd.mall.service.IntegralRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: IntegralRuleServiceImpl
 * @projectName dmd-masters
 * @description:
 * @date 2019/10/2213:53
 */
@Service
public class IntegralRuleServiceImpl implements IntegralRuleService{

    @Autowired
    private IntegralRuleMapper integralRuleMapper;

    @Override
    public List<IntegralRuleBean> queryIntegralRule() {
        return integralRuleMapper.queryIntegralRule();
    }
}
