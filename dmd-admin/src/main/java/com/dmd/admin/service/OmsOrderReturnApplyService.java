package com.dmd.admin.service;

import com.dmd.admin.model.domain.OmsOrderReturnApply;
import com.dmd.admin.model.dto.OmsOrderReturnApplyResult;
import com.dmd.admin.model.dto.OmsReturnApplyQueryParam;
import com.dmd.admin.model.dto.OmsUpdateStatusParam;
import com.dmd.core.support.IService;

import java.util.List;

/**
 * <p>
 * 订单退货申请 服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-26
 */
public interface OmsOrderReturnApplyService extends IService<OmsOrderReturnApply> {

    List<OmsOrderReturnApply> list(OmsReturnApplyQueryParam queryParam, Integer pageSize, Integer pageNum);

    int deleteByIds(List<Long> ids);

    OmsOrderReturnApplyResult getItem(Long id);

    int updateStatus(Long id, OmsUpdateStatusParam statusParam);
}
