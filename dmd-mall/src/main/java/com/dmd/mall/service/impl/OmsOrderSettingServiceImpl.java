package com.dmd.mall.service.impl;

import com.dmd.mall.model.domain.OmsOrderSetting;
import com.dmd.mall.mapper.OmsOrderSettingMapper;
import com.dmd.mall.service.OmsOrderSettingService;
import com.dmd.core.support.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 订单设置表 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-12-10
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OmsOrderSettingServiceImpl extends BaseService<OmsOrderSetting> implements OmsOrderSettingService {

    @Autowired
    private OmsOrderSettingMapper omsOrderSettingMapper;
}
