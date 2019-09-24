package com.dmd.mall.service.impl;

import com.dmd.mall.model.domain.UmsUserWalletLog;
import com.dmd.mall.mapper.UmsUserWalletLogMapper;
import com.dmd.mall.service.UmsUserWalletLogService;
import com.dmd.core.support.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户账户日志表  服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-09-24
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UmsUserWalletLogServiceImpl extends BaseService<UmsUserWalletLog> implements UmsUserWalletLogService {

    @Autowired
    private UmsUserWalletLogMapper umsUserWalletLogMapper;
}
