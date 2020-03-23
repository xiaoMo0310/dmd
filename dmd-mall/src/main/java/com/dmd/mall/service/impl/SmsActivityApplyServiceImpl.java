package com.dmd.mall.service.impl;

import com.dmd.BeanUtils;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.BaseService;
import com.dmd.mall.mapper.SmsActivityApplyMapper;
import com.dmd.mall.model.domain.SmsActivityApply;
import com.dmd.mall.model.dto.SmsActivityApplyDto;
import com.dmd.mall.service.SmsActivityApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 活动报名表 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-11
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SmsActivityApplyServiceImpl extends BaseService<SmsActivityApply> implements SmsActivityApplyService {

    @Autowired
    private SmsActivityApplyMapper activityApplyMapper;

    @Override
    public int addActivityApplyMessage(LoginAuthDto loginAuthDto, SmsActivityApplyDto activityApplyDto) {
        SmsActivityApply smsActivityApply = new SmsActivityApply();
        BeanUtils.copyProperties( activityApplyDto, smsActivityApply );
        smsActivityApply.setUpdateInfo( loginAuthDto );
        smsActivityApply.setStatus( 1 );
        smsActivityApply.setUserId( loginAuthDto.getUserId() );
        if(loginAuthDto.getUserType().equals( "coach" )){
            smsActivityApply.setUserType( "coach" );
        }else {
            smsActivityApply.setUserType( "member" );
        }
        return activityApplyMapper.insertSelective( smsActivityApply );
    }
}
