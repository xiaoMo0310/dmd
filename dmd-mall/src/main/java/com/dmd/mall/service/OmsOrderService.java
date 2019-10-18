package com.dmd.mall.service;

import com.dmd.base.dto.BaseQuery;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.IService;
import com.dmd.mall.model.domain.OmsOrder;
import com.dmd.mall.model.dto.OrderPageQueryDto;
import com.dmd.mall.model.dto.OrderParamDto;
import com.dmd.mall.model.vo.OrderCreateResultVo;
import com.dmd.mall.model.vo.OrderCreateVo;
import com.dmd.mall.model.vo.OrderVo;
import com.github.pagehelper.PageInfo;

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
    OrderCreateResultVo createOrder(LoginAuthDto loginAuthDto, OrderCreateVo orderCreateVo);

    /**
     * 创建课程或积分订单
     * @param loginAuthDto
     * @param orderParamDto
     * @return
     */
    OrderCreateResultVo createCourseOrIntegralOrder(LoginAuthDto loginAuthDto, OrderParamDto orderParamDto);
    /**
     * 取消订单
     * @param loginAuthDto
     * @param orderSn
     * @return
     */
    int cancelOrderDoc(LoginAuthDto loginAuthDto, String orderSn);

    /**
     * 查询用户订单详情
     * @param userId
     * @param orderSn
     * @return
     */
    OrderVo getOrderDetail(Long userId, String orderSn);

    /**
     * 查询用户全部订单列表.
     * @param userId    the user id
     * @param baseQuery the base query
     * @return the page info
     */
    PageInfo queryUserOrderListWithPage(Long userId, BaseQuery baseQuery);

    /**
     * 根据订单的状态查询用户订单列表.
     * @param orderPageQuery the order page query
     * @return the page info
     */
    PageInfo queryOrderListWithPage(OrderPageQueryDto orderPageQuery);

}
