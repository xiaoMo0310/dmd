package com.dmd.mall.service;

import com.dmd.core.support.IService;
import com.dmd.mall.model.domain.UmsIntegrationChangeLog;
import com.dmd.mall.model.domain.UmsMember;

/**
 * <p>
 * 积分变化历史记录表 服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-18
 */
public interface UmsIntegrationChangeLogService extends IService<UmsIntegrationChangeLog> {

    /**
     * 记录用户积分日志
     * @param umsMember
     * @param integration
     * @param operateNote
     */
    void updateIntegrationAndAddLog(UmsMember umsMember, Integer integration, Integer totalIntegration, String operateNote, Integer changeType);

}
