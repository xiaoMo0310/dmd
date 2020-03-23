package com.dmd.mall.service.impl;

import com.dmd.mall.model.domain.OmsFinanceSetting;
import com.dmd.mall.mapper.OmsFinanceSettingMapper;
import com.dmd.mall.service.OmsFinanceSettingService;
import com.dmd.core.support.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 财务设置表 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-19
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OmsFinanceSettingServiceImpl extends BaseService<OmsFinanceSetting> implements OmsFinanceSettingService {

    @Autowired
    private OmsFinanceSettingMapper omsFinanceSettingMapper;
}
