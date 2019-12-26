package com.dmd.mall.service;

import com.dmd.base.dto.LoginAuthDto;
import com.dmd.mall.model.domain.OmsIntegralOrder;
import com.dmd.core.support.IService;
import com.dmd.mall.model.dto.OrderParamDto;
import com.dmd.mall.model.vo.CourseOrderDetailVo;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 * 积分好礼订单表 服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-12-23
 */
public interface OmsIntegralOrderService extends IService<OmsIntegralOrder> {

    /**
     * 创建积分好礼订单
     * @param loginAuthDto
     * @param orderParamDto
     */
    void createIntegralOrder(LoginAuthDto loginAuthDto, OrderParamDto orderParamDto);

    /**
     * 根据订单的状态查询用户积分订单列表
     * @param loginAuthDto
     * @param pageNum
     * @param pageSize
     * @param status
     * @return
     */
    PageInfo queryIntegralOrderListWithPage(LoginAuthDto loginAuthDto, Integer pageNum, Integer pageSize, Integer status);

    /**
     * 查询用户积分订单详情
     * @param loginAuthDto
     * @param orderSn
     * @return
     */
    CourseOrderDetailVo getUserIntegralOrderDetail(LoginAuthDto loginAuthDto, String orderSn);

    /**
     * 查询教练积分订单详情
     * @param loginAuthDto
     * @param orderSn
     * @return
     */
    CourseOrderDetailVo getSellerIntegralOrderDetail(LoginAuthDto loginAuthDto, String orderSn);
}
