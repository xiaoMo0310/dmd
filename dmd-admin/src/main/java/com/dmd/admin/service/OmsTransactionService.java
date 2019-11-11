package com.dmd.admin.service;

import com.dmd.admin.model.domain.OmsTransaction;
import com.dmd.admin.model.dto.BillingDetailDto;
import com.dmd.core.support.IService;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 * 订单交易表  服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-04
 */
public interface OmsTransactionService extends IService<OmsTransaction> {

    /**
     * 获取平台的收支明细
     * @param billingDetailDto
     * @return
     */
    PageInfo<OmsTransaction> findIncomeAndExpenditureDetails(BillingDetailDto billingDetailDto);
}
