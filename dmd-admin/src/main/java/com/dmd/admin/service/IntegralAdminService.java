package com.dmd.admin.service;

import com.dmd.admin.model.domain.IntegralRuleBean;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: IntegralAdminService
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/10/2214:20
 */
public interface IntegralAdminService {
    List<IntegralRuleBean> queryIntegralRule();

    int updateIntegralRule(IntegralRuleBean integralRuleBean);

    IntegralRuleBean findIntegralInfoById(Long id);
}
