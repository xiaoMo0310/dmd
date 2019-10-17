package com.dmd.mall.service;

import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.IService;
import com.dmd.mall.model.domain.OmsOrder;
import com.dmd.mall.model.vo.OrderCreateVo;
import com.dmd.mall.model.vo.OrderVo;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-14
 */
public interface OmsOrderService extends IService<OmsOrder> {

    /**
     * 创建订单
     * @param loginAuthDto
     * @param orderCreateVo
     */
    OrderVo createOrder(LoginAuthDto loginAuthDto, OrderCreateVo orderCreateVo);
}
