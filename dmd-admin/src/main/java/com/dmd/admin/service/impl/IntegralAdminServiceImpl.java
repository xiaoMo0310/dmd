package com.dmd.admin.service.impl;

import com.dmd.admin.mapper.IntegralRuleMapper;
import com.dmd.admin.model.domain.IntegralRuleBean;
import com.dmd.admin.service.IntegralAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ChenYanbing
 * @title: IntegralAdminServiceImpl
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/10/2214:20
 */
@Service
public class IntegralAdminServiceImpl implements IntegralAdminService{

    @Autowired
    private IntegralRuleMapper integralRuleMapper;

    @Override
    public List<IntegralRuleBean> queryIntegralRule() {
        return integralRuleMapper.queryIntegralRule();
    }

    @Override
    public int updateIntegralRule(IntegralRuleBean integralRuleBean) {
        integralRuleBean.setCreateTime(new Date());
        return integralRuleMapper.updateIntegralRule(integralRuleBean);
    }
}
