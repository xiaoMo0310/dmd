package com.dmd.admin.service.impl;

import com.dmd.admin.model.domain.UmsOperationLog;
import com.dmd.admin.mapper.UmsOperationLogMapper;
import com.dmd.admin.service.UmsOperationLogService;
import com.dmd.core.support.BaseService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UmsOperationLogServiceImpl extends BaseService<UmsOperationLog> implements UmsOperationLogService {

    @Autowired
    private UmsOperationLogMapper umsOperationLogMapper;

    @Override
    public PageInfo getOperationLog(UmsOperationLog umsOperationLog) {
        PageHelper.startPage(umsOperationLog.getPageNum(), umsOperationLog.getPageSize());
        List<UmsOperationLog> logList=umsOperationLogMapper.getOperationLog(umsOperationLog);
        return new PageInfo<>(logList);
    }

    @Override
    public int addOperationLog(UmsOperationLog umsOperationLog) {
        return umsOperationLogMapper.addOperationLog(umsOperationLog);
    }
}
