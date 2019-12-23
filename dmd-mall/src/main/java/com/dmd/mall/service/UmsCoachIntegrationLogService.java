package com.dmd.mall.service;

import com.dmd.mall.model.domain.UmsCoach;
import com.dmd.mall.model.domain.UmsCoachIntegrationLog;
import com.dmd.core.support.IService;

/**
 * <p>
 * 教练积分变化历史记录表 服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-12-18
 */
public interface UmsCoachIntegrationLogService extends IService<UmsCoachIntegrationLog> {

    void updateIntegrationAndAddLog(UmsCoach umsCoach, Integer integration, Integer totalIntegration, String operateNote, int changeType);
}
