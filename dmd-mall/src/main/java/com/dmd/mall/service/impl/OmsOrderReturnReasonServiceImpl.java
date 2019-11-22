package com.dmd.mall.service.impl;

import com.dmd.core.support.BaseService;
import com.dmd.mall.mapper.OmsOrderReturnReasonMapper;
import com.dmd.mall.model.domain.OmsOrderReturnReason;
import com.dmd.mall.service.OmsOrderReturnReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 退货原因表 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-22
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OmsOrderReturnReasonServiceImpl extends BaseService<OmsOrderReturnReason> implements OmsOrderReturnReasonService {

    @Autowired
    private OmsOrderReturnReasonMapper omsOrderReturnReasonMapper;

    @Override
    public List<OmsOrderReturnReason> queryOrderReturnReason() {
        return omsOrderReturnReasonMapper.selectOrderReturnReason();
    }
}
