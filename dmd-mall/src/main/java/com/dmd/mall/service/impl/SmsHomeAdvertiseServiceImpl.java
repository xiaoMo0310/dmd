package com.dmd.mall.service.impl;

import com.dmd.mall.model.domain.SmsHomeAdvertise;
import com.dmd.mall.mapper.SmsHomeAdvertiseMapper;
import com.dmd.mall.service.SmsHomeAdvertiseService;
import com.dmd.core.support.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 首页轮播广告表 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-10
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SmsHomeAdvertiseServiceImpl extends BaseService<SmsHomeAdvertise> implements SmsHomeAdvertiseService {

    @Autowired
    private SmsHomeAdvertiseMapper smsHomeAdvertiseMapper;

    @Override
    public List<SmsHomeAdvertise> selectAdvertisePicList(Integer type) {
        List<SmsHomeAdvertise> homeAdvertises = smsHomeAdvertiseMapper.selectAdvertisePicList(type);
        return homeAdvertises;
    }
}
