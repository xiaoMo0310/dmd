package com.dmd.mall.service.impl;

import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.BaseService;
import com.dmd.mall.mapper.UmsCoachIntegrationLogMapper;
import com.dmd.mall.model.domain.UmsCoach;
import com.dmd.mall.model.domain.UmsCoachIntegrationLog;
import com.dmd.mall.service.UmsCoachIntegrationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 教练积分变化历史记录表 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-12-18
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UmsCoachIntegrationLogServiceImpl extends BaseService<UmsCoachIntegrationLog> implements UmsCoachIntegrationLogService {

    @Autowired
    private UmsCoachIntegrationLogMapper umsCoachIntegrationLogMapper;

    @Override
    public void updateIntegrationAndAddLog(UmsCoach umsCoach, Integer integration, Integer totalIntegration, String operateNote, int changeType) {
        //记录日志信息
        UmsCoachIntegrationLog integrationChangeLog = new UmsCoachIntegrationLog();
        integrationChangeLog.setChangeCount(integration);
        integrationChangeLog.setChangeType(changeType);
        integrationChangeLog.setCoachId(umsCoach.getId());
        integrationChangeLog.setOperateMan(umsCoach.getCoachName());
        integrationChangeLog.setOperateNote(operateNote);
        integrationChangeLog.setIntegralTrend(operateNote);
        integrationChangeLog.setSourceType(0);
        LoginAuthDto loginAuthDto = new LoginAuthDto();
        loginAuthDto.setUserId(umsCoach.getId());
        loginAuthDto.setUserName(umsCoach.getInvitationCode());
        integrationChangeLog.setUpdateInfo(loginAuthDto);
        umsCoachIntegrationLogMapper.insertSelective(integrationChangeLog);
    }
}
