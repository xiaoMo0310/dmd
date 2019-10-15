package com.dmd.mall.service.impl;

import com.dmd.mall.model.domain.UmsIntegrationRuleSetting;
import com.dmd.mall.mapper.UmsIntegrationRuleSettingMapper;
import com.dmd.mall.service.UmsIntegrationRuleSettingService;
import com.dmd.core.support.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 积分消费设置 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-15
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UmsIntegrationRuleSettingServiceImpl extends BaseService<UmsIntegrationRuleSetting> implements UmsIntegrationRuleSettingService {

    @Autowired
    private UmsIntegrationRuleSettingMapper umsIntegrationRuleSettingMapper;
}
