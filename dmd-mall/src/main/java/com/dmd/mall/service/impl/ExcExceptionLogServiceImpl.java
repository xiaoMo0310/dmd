package com.dmd.mall.service.impl;

import com.dmd.mall.model.domain.ExcExceptionLog;
import com.dmd.mall.mapper.ExcExceptionLogMapper;
import com.dmd.mall.model.dto.GlobalExceptionLogDto;
import com.dmd.mall.service.ExcExceptionLogService;
import com.dmd.core.support.BaseService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 * 全局异常记录 服务实现类
 * </p>
 *
 * @author YangAnsheng123
 * @since 2019-09-18
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ExcExceptionLogServiceImpl extends BaseService<ExcExceptionLog> implements ExcExceptionLogService {

    @Autowired
    private ExcExceptionLogMapper excExceptionLogMapper;

    @Override
    public void saveAndSendExceptionLog(GlobalExceptionLogDto exceptionLogDto) {
        ExcExceptionLog exceptionLog = new ModelMapper().map(exceptionLogDto, ExcExceptionLog.class);

        exceptionLog.setId(generateId());
        exceptionLog.setCreateTime(new Date());
        excExceptionLogMapper.insertSelective(exceptionLog);
    }
}
