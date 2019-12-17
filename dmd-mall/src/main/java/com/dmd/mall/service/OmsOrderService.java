package com.dmd.mall.service;

import com.alibaba.fastjson.JSONObject;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.IService;
import com.dmd.mall.model.domain.OmsOrder;
import com.dmd.mall.model.dto.OrderParamDto;
import com.dmd.mall.model.dto.PmsCourseOrderDto;
import com.dmd.mall.model.vo.CourseOrderDetailVo;
import com.dmd.mall.model.vo.OrderCreateResultVo;
import com.dmd.mall.model.vo.OrderCreateVo;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

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
     * 创建积分订单
     * @param loginAuthDto
     * @param orderParamDto
     * @return
     */
    void createIntegralOrder(LoginAuthDto loginAuthDto, OrderParamDto orderParamDto);

    /**
     * 创建潜水学证商品订单
     * @param loginAuthDto
     * @param orderParamDto
     * @return
     */
    JSONObject createCourseProductOrder(LoginAuthDto loginAuthDto, PmsCourseOrderDto orderParamDto);
    /**
     * 取消订单
     * @param loginAuthDto
     * @param orderSn
     * @return
     */
    int cancelOrderDoc(LoginAuthDto loginAuthDto, String orderSn);

    /**
     * 查询用户订单详情
     * @param loginAuthDto
     * @param orderSn
     * @return
     */
    CourseOrderDetailVo getUserOrderDetail(LoginAuthDto loginAuthDto, String orderSn);

    /**
     * 查询卖家订单详情
     * @param loginAuthDto
     * @param orderSn
     * @return
     */
    CourseOrderDetailVo getSellerOrderDetail(LoginAuthDto loginAuthDto, String orderSn);
    /**
     * 根据订单的状态查询用户订单列表
     * @param loginAuthDto
     * @param pageNum
     * @param pageSize
     * @param status
     * @return
     */
    PageInfo queryUserOrderList(LoginAuthDto loginAuthDto, Integer pageNum, Integer pageSize, Integer status);

    /**
     * 根据订单的状态查询卖家订单列表
     * @param loginAuthDto
     * @param pageNum
     * @param pageSize
     * @param status
     * @return
     */
    PageInfo querySellerOrderListWithPage(LoginAuthDto loginAuthDto, Integer pageNum, Integer pageSize, Integer status);

    /**
     * 确认完成订单
     * @param loginAuthDto
     * @param orderSn
     * @return
     */
    int updateOrderStatus(LoginAuthDto loginAuthDto, String orderSn, Integer status);

    /**
     * 根据订单编号查询当前登录人的订单信息
     * @param orderSn
     * @return
     */
    OmsOrder getOmsOrderByOrderSn(String orderSn);

    /**
     * 根据订单号查询订单信息
     * @param loginAuthDto
     * @param orderId
     * @return
     */
    OmsOrder getOmsOrderByOrderId(LoginAuthDto loginAuthDto, Long orderId);

    List<CourseOrderDetailVo> queryOrderListByStatus(Integer orderType, Integer status);

    /**
     * 统计各个状态下订单的数量
     * @param loginAuthDto
     * @return
     */
    List<Map> countOrderNum(LoginAuthDto loginAuthDto);

    /**
     * 分页查询用户积分好礼订单列表
     * @param loginAuthDto
     * @param pageNum
     * @param pageSize
     * @param status
     * @return
     */
    PageInfo queryIntegralOrderListWithPage(LoginAuthDto loginAuthDto, Integer pageNum, Integer pageSize, Integer status);

    /**
     * 根据订单状态查询订单信息
     * @param status
     * @return
     */
    List<OmsOrder> selectOrderByStatus(Integer orderType, Integer status);
}
