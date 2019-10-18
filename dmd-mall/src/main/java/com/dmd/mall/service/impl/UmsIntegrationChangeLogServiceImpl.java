package com.dmd.mall.service.impl;

import com.dmd.core.support.BaseService;
import com.dmd.mall.mapper.UmsIntegrationChangeLogMapper;
import com.dmd.mall.model.domain.UmsIntegrationChangeLog;
import com.dmd.mall.model.domain.UmsMember;
import com.dmd.mall.service.UmsIntegrationChangeLogService;
import com.dmd.mall.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 积分变化历史记录表 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-18
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UmsIntegrationChangeLogServiceImpl extends BaseService<UmsIntegrationChangeLog> implements UmsIntegrationChangeLogService {

    @Autowired
    private UmsIntegrationChangeLogMapper umsIntegrationChangeLogMapper;
    @Autowired
    private UmsMemberService umsMemberService;

    @Override
    public void updateIntegrationAndAddLog(UmsMember umsMember, Integer integration, String operateNote, Integer changeType){
        //记录日志信息
        UmsIntegrationChangeLog integrationChangeLog = new UmsIntegrationChangeLog();
        integrationChangeLog.setChangeCount(integration);
        integrationChangeLog.setChangeType(changeType);
        integrationChangeLog.setMemberId(umsMember.getId());
        integrationChangeLog.setOperateMan(umsMember.getUsername());
        integrationChangeLog.setOperateNote(operateNote);
        integrationChangeLog.setSourceType(0);
        umsIntegrationChangeLogMapper.insertSelective(integrationChangeLog);
    }

}
