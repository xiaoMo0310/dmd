package com.dmd.mall.service.impl;

import com.dmd.base.dto.BaseQuery;
import com.dmd.mall.model.domain.SmsShopActivity;
import com.dmd.mall.mapper.SmsShopActivityMapper;
import com.dmd.mall.service.SmsShopActivityService;
import com.dmd.core.support.BaseService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 活动表 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-11
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SmsShopActivityServiceImpl extends BaseService<SmsShopActivity> implements SmsShopActivityService {

    @Autowired
    private SmsShopActivityMapper shopActivityMapper;

    @Override
    public PageInfo<SmsShopActivity> findShopActivityByPage(BaseQuery baseQuery) {
        PageHelper.startPage( baseQuery.getPageNum(), baseQuery.getPageSize(), baseQuery.getOrderBy() );
        List<SmsShopActivity> shopActivities = shopActivityMapper.selectShopAvtivityList();
        return new PageInfo<>( shopActivities );
    }
}
