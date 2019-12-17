package com.dmd.admin.mapper;

import com.dmd.admin.model.domain.IntegralRuleBean;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: IntegralRuleMapper
 * @projectName dmd-masters
 * @description:
 * @date 2019/10/2214:22
 */
public interface IntegralRuleMapper {
    List<IntegralRuleBean> queryIntegralRule();

    int updateIntegralRule(IntegralRuleBean integralRuleBean);

    IntegralRuleBean findIntegralInfoById(Long id);
}
