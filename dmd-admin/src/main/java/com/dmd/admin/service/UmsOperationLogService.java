package com.dmd.admin.service;

import com.dmd.admin.model.domain.UmsOperationLog;
import com.dmd.core.support.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-17
 */
public interface UmsOperationLogService extends IService<UmsOperationLog> {

    PageInfo getOperationLog(UmsOperationLog umsOperationLog);
    int addOperationLog(UmsOperationLog umsOperationLog);
}
