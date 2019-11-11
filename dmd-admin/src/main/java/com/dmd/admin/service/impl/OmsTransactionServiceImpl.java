package com.dmd.admin.service.impl;

import com.dmd.admin.mapper.OmsTransactionMapper;
import com.dmd.admin.model.domain.OmsTransaction;
import com.dmd.admin.model.dto.BillingDetailDto;
import com.dmd.admin.service.OmsFashionableService;
import com.dmd.admin.service.OmsOrderService;
import com.dmd.admin.service.OmsTransactionService;
import com.dmd.core.support.BaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    @Autowired
    private OmsOrderService omsOrderService;
    @Autowired
    private OmsFashionableService fashionableService;

    @Override
    public PageInfo<OmsTransaction> findIncomeAndExpenditureDetails(BillingDetailDto billingDetailDto) {
        //查询所有的数据
        PageHelper.startPage(billingDetailDto.getPageNum(), billingDetailDto.getPageSize());
        List<OmsTransaction> omsTransactions = omsTransactionMapper.selectAllMessage(billingDetailDto);
        PageInfo<OmsTransaction> omsTransactionPageInfo = new PageInfo<>(omsTransactions);
        return omsTransactionPageInfo;
    }
}
