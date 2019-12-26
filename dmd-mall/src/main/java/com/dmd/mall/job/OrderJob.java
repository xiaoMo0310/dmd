package com.dmd.mall.job;

import com.dmd.DateUtil;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.base.enums.ErrorCodeEnum;
import com.dmd.mall.constant.OmsApiConstant;
import com.dmd.mall.exceptions.PmsBizException;
import com.dmd.mall.model.domain.*;
import com.dmd.mall.model.dto.OrderAppraiseDto;
import com.dmd.mall.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/11/27 13:18
 * @Description 订单定时任务
 */
@Component
@Transactional(rollbackFor = Exception.class)
public class OrderJob {

    @Autowired
    private PmsCourseProductService courseProductService;
    @Autowired
    private OmsOrderService omsOrderService;
    @Autowired
    private OmsOrderSettingService orderSettingService;
    @Autowired
    private OmsOrderItemService omsOrderItemService;
    @Autowired
    private OmsOrderReturnApplyService omsOrderReturnApplyService;
    @Autowired
    private OmsOrderAppraiseService orderAppraiseService;

    /**
     * 自动修改订单为进行中
     */
    @Scheduled(cron = "0 10 1 * * ?")
    public void updateOrderProcessing(){
        //查询已支付的学证潜水订单
        List<OmsOrder> omsOrders = omsOrderService.selectOrderByStatus(1,OmsApiConstant.OrderStatusEnum.PAID.getCode());
        omsOrders.forEach(omsOrder -> {
            //查询订单详情数据
            List<OmsOrderItem> omsOrderItems = omsOrderItemService.getListByOrderNoUserId(omsOrder.getOrderSn());
            omsOrderItems.forEach(omsOrderItem -> {
                PmsCourseProduct courseProduct = courseProductService.selectByKey(omsOrderItem.getProductId());
                Date lastDate = DateUtil.resolverDate(DateUtil.formartDate(courseProduct.getStartTime()));
                if(System.currentTimeMillis() >= lastDate.getTime()){
                    //修改订单状态为进行中
                    LoginAuthDto loginAuthDto = new LoginAuthDto();
                    loginAuthDto.setUserId(0L);
                    loginAuthDto.setUserType("system");
                    loginAuthDto.setUserName("系统");
                    omsOrderService.updateOrderStatus(loginAuthDto, omsOrder.getOrderSn(), OmsApiConstant.OrderStatusEnum.SHIPPED.getCode());
                }
            });
        });
    }

    /**
     * 定时清理未支付的订单
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void clearNoPayOrder(){
        OmsOrderSetting omsOrderSetting = findOmsOrderSettingMessage();
        Integer normalOrderOvertime = omsOrderSetting.getNormalOrderOvertime();
        //查询所有未支付的订单
        List<OmsOrder> omsOrders = omsOrderService.selectOrderByStatus(1,OmsApiConstant.OrderStatusEnum.NO_PAY.getCode());
        omsOrders.forEach(order -> {
            Date lastDate = DateUtil.minuteShit("yyyy-MM-dd HH:mm:ss", order.getCreatedTime(), normalOrderOvertime);
            if(System.currentTimeMillis() >= lastDate.getTime()){
                LoginAuthDto loginAuthDto = new LoginAuthDto();
                loginAuthDto.setUserId(0L);
                loginAuthDto.setUserType("system");
                loginAuthDto.setUserName("系统");
                //修改订单状态取消
                omsOrderService.cancelOrderDoc(loginAuthDto, order.getOrderSn());
                //查询订单详情数据
                List<OmsOrderItem> omsOrderItems = omsOrderItemService.getListByOrderNoUserId(order.getOrderSn());
                //添加库存
                omsOrderItems.forEach(omsOrderItem -> {
                    PmsCourseProduct courseProduct = courseProductService.selectByKey(omsOrderItem.getProductId());
                    courseProduct.setStock(courseProduct.getStock() +1);
                    courseProduct.setUpdateInfo(loginAuthDto);
                    int result = courseProductService.update(courseProduct);
                    if(result <= 0){
                        throw new PmsBizException(ErrorCodeEnum.PMS10021032);
                    }
                });
            }
        });
    }

    /**
     * 定时清理售后未审核的订单
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void clearUnauditedOrders(){
        OmsOrderSetting omsOrderSetting = findOmsOrderSettingMessage();
        Integer returnOrderOvertime = omsOrderSetting.getReturnOrderOvertime();
        //查询待审核的售后订单
        List<OmsOrderReturnApply> orderReturnApplies = omsOrderReturnApplyService.selectReturnOrderByStatus(0);
        orderReturnApplies.forEach(omsOrderReturnApply -> {
            Date lastDate = DateUtil.dateshit("yyyy-MM-dd HH:mm:ss", omsOrderReturnApply.getCreatedTime(), returnOrderOvertime);
            if(System.currentTimeMillis() >= lastDate.getTime()){
                LoginAuthDto loginAuthDto = new LoginAuthDto();
                loginAuthDto.setUserId(0L);
                loginAuthDto.setUserType("system");
                loginAuthDto.setUserName("系统");
                //修改售后订单审核通过
                int result = omsOrderReturnApplyService.updateOmsReturnStatus(loginAuthDto, omsOrderReturnApply.getId(), 1);
                if(result <= 0){
                    throw new PmsBizException("定时修改售后订单状态异常");
                }
            }
        });
    }

    /**
     * 定时自动完成订单如果完成时间和评价时间相同好评(凌晨1:15执行)
     */
    @Scheduled(cron = "0 15 1 * * ?")
    public void AutomaticOrderFulfillment(){
        OmsOrderSetting omsOrderSetting = findOmsOrderSettingMessage();
        //确认完成时间
        Integer confirmOvertime = omsOrderSetting.getConfirmOvertime();
        //完成好评时间
        Integer finishOvertime = omsOrderSetting.getFinishOvertime();
        //查询进行中的潜水订单
        List<OmsOrder> omsOrders = omsOrderService.selectOrderByStatus(1,OmsApiConstant.OrderStatusEnum.SHIPPED.getCode());
        omsOrders.forEach(omsOrder -> {
            //查询订单详情数据
            List<OmsOrderItem> omsOrderItems = omsOrderItemService.getListByOrderNoUserId(omsOrder.getOrderSn());
            omsOrderItems.forEach(omsOrderItem -> {
                PmsCourseProduct courseProduct = courseProductService.selectByKey(omsOrderItem.getProductId());
                Date endDate = DateUtil.resolverDate(DateUtil.formartDate(courseProduct.getEndTime()));
                Date lastDate = DateUtil.dateshit("yyyy-MM-dd", endDate, confirmOvertime);
                if(System.currentTimeMillis() >= lastDate.getTime()){
                    //修改订单状态为已完成
                    LoginAuthDto loginAuthDto = new LoginAuthDto();
                    loginAuthDto.setUserId(0L);
                    loginAuthDto.setUserType("system");
                    loginAuthDto.setUserName("系统");
                    omsOrderService.updateOrderStatus(loginAuthDto, omsOrder.getOrderSn(), OmsApiConstant.OrderStatusEnum.ORDER_SUCCESS.getCode());
                    if(finishOvertime == 0 ){
                        //自动好评 并关闭订单
                        praiseAndCloseOrder(omsOrder.getId(), courseProduct.getId(), omsOrder.getMemberId());
                    }
                }
            });
        });
    }

    /**
     * 自动好评订单并关闭订单
     */
    @Scheduled(cron = "0 20 1 * * ?")
    public void automaticPraiseOrder(){
        OmsOrderSetting omsOrderSetting = findOmsOrderSettingMessage();
        //完成好评时间
        Integer finishOvertime = omsOrderSetting.getFinishOvertime();
        //查询已支付的学证潜水订单
        List<OmsOrder> omsOrders = omsOrderService.selectOrderByStatus(1,OmsApiConstant.OrderStatusEnum.ORDER_SUCCESS.getCode());
        omsOrders.forEach(omsOrder -> {
            //查询订单详情数据
            List<OmsOrderItem> omsOrderItems = omsOrderItemService.getListByOrderNoUserId(omsOrder.getOrderSn());
            omsOrderItems.forEach(omsOrderItem -> {
                Date finishDate = DateUtil.resolverDate(DateUtil.formartDate(omsOrder.getEndTime()));
                Date lastDate = DateUtil.dateshit("yyyy-MM-dd", finishDate, finishOvertime);
                if(System.currentTimeMillis() >= lastDate.getTime()){
                    //自动好评,关闭订单
                    praiseAndCloseOrder(omsOrder.getId(), omsOrderItem.getProductId(), omsOrder.getMemberId());
                }
            });
        });
    }

    /**
     * 默认好评并关闭订单
     * @param orderId
     * @param productId
     * @param BuyerId
     */
    public void praiseAndCloseOrder(Long orderId, Long productId, Long BuyerId){
        LoginAuthDto loginAuthDto = new LoginAuthDto();
        loginAuthDto.setUserId(BuyerId);
        loginAuthDto.setUserType("system");
        loginAuthDto.setUserName("系统");
        OrderAppraiseDto orderAppraiseDto = new OrderAppraiseDto();
        orderAppraiseDto.setOrderId(orderId);
        orderAppraiseDto.setProductId(productId);
        orderAppraiseDto.setInfo("用户默认好评");
        orderAppraiseDto.setLevel("1");
        orderAppraiseDto.setDescStar(5);
        orderAppraiseDto.setLogisticsStar(5);
        orderAppraiseDto.setAttitudeStar(5);
        int result = orderAppraiseService.insertAppraiseMessage(loginAuthDto, orderAppraiseDto);
    }
    /**
     * 查询订单配置信息
     * @return
     */
    public OmsOrderSetting findOmsOrderSettingMessage(){
        OmsOrderSetting omsOrderSetting = orderSettingService.selectByKey(1);
        return omsOrderSetting;
    }
}
