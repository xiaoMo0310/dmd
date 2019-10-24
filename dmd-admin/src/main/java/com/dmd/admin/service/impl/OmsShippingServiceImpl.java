package com.dmd.admin.service.impl;

import com.dmd.admin.model.domain.OmsShipping;
import com.dmd.admin.mapper.OmsShippingMapper;
import com.dmd.admin.service.OmsShippingService;
import com.dmd.core.support.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 收货人信息表 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-24
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OmsShippingServiceImpl extends BaseService<OmsShipping> implements OmsShippingService {

    @Autowired
    private OmsShippingMapper omsShippingMapper;
}
