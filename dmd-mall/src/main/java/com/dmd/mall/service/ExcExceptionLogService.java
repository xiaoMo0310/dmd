package com.dmd.mall.service;

import com.dmd.core.support.IService;
import com.dmd.mall.model.domain.ExcExceptionLog;
import com.dmd.mall.model.dto.GlobalExceptionLogDto;

/**
 * <p>
 * 全局异常记录 服务类
 * </p>
 *
 * @author YangAnsheng123
 * @since 2019-09-18
 */
public interface ExcExceptionLogService extends IService<ExcExceptionLog> {

    /**
     * 保存日志并发送钉钉消息.
     *
     * @param exceptionLogDto the exception log dto
     */
    void saveAndSendExceptionLog(GlobalExceptionLogDto exceptionLogDto);
}
