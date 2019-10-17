package com.dmd.mall.service.impl;

import com.dmd.base.enums.ErrorCodeEnum;
import com.dmd.core.support.BaseService;
import com.dmd.mall.exceptions.OmsBizException;
import com.dmd.mall.mapper.OmsOrderItemMapper;
import com.dmd.mall.model.domain.OmsOrderItem;
import com.dmd.mall.service.OmsOrderItemService;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 订单详情表 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-14
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OmsOrderItemServiceImpl extends BaseService<OmsOrderItem> implements OmsOrderItemService {

    @Autowired
    private OmsOrderItemMapper omsOrderItemMapper;

    @Override
    public void batchInsertOrderDetail(List<OmsOrderItem> omcOrderDetailList) {
        omcOrderDetailList.forEach(omsOrderItem -> {
            int i = omsOrderItemMapper.insertSelective(omsOrderItem);
            if(i <= 0){
                throw new OmsBizException(ErrorCodeEnum.OMS10031009);
            }
        });
    }

    @Override
    public List<OmsOrderItem> getListByOrderNoUserId(String orderSn) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(orderSn), "订单号不能为空");
        return omsOrderItemMapper.getListByOrderNoUserId(orderSn);
    }
}
