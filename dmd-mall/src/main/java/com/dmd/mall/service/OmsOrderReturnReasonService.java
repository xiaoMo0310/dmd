package com.dmd.mall.service;

import com.dmd.core.support.IService;
import com.dmd.mall.model.domain.OmsOrderReturnReason;

import java.util.List;

/**
 * <p>
 * 退货原因表 服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-22
 */
public interface OmsOrderReturnReasonService extends IService<OmsOrderReturnReason> {

    /**
     * 查询所有的退款原因
     * @return
     */
    List<OmsOrderReturnReason> queryOrderReturnReason();
}
