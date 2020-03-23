package com.dmd.admin.service.impl;

import com.dmd.admin.model.domain.SmsShopActivity;
import com.dmd.admin.mapper.SmsShopActivityMapper;
import com.dmd.admin.service.SmsShopActivityService;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.BaseService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * <p>
 * 活动表 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-10
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SmsShopActivityServiceImpl extends BaseService<SmsShopActivity> implements SmsShopActivityService {

    @Autowired
    private SmsShopActivityMapper shopActivityMapper;

    @Override
    public int createOrUpdateActivity(LoginAuthDto loginAuthDto, SmsShopActivity shopActivity) {
        shopActivity.setUpdateInfo( loginAuthDto );
        int result = 0;
        if(shopActivity.isNew()){
            result = shopActivityMapper.insertSelective( shopActivity );
        }else {
            result = shopActivityMapper.updateByPrimaryKeySelective( shopActivity );
        }
        return result;
    }

    @Override
    public PageInfo<SmsShopActivity> getActivityList(SmsShopActivity smsShopActivity) {
        PageHelper.startPage( smsShopActivity.getPageNum(), smsShopActivity.getPageSize());
        List<SmsShopActivity> smsShopActivities = shopActivityMapper.selectActivityList(smsShopActivity);
        return new PageInfo<>( smsShopActivities );
    }

    @Override
    public int deleteActivity(List<Long> ids) {
        Example example = new Example( SmsShopActivity.class );
        example.createCriteria().andIn( "id", ids );
        return shopActivityMapper.deleteByExample( example );
    }

    @Override
    public int updateShowStatus(List<Long> ids, Integer showStatus) {
        SmsShopActivity shopActivity = new SmsShopActivity();
        shopActivity.setShowStatus( showStatus );
        Example example = new Example( SmsShopActivity.class );
        example.createCriteria().andIn( "id", ids );
        return shopActivityMapper.updateByExample( shopActivity, example );
    }
}
