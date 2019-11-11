package com.dmd.mall.service.impl;

import com.dmd.mall.model.domain.OmsTransaction;
import com.dmd.mall.mapper.OmsTransactionMapper;
import com.dmd.mall.service.OmsTransactionService;
import com.dmd.core.support.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 订单交易表  服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-04
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OmsTransactionServiceImpl extends BaseService<OmsTransaction> implements OmsTransactionService {

    @Autowired
    private OmsTransactionMapper omsTransactionMapper;
}
