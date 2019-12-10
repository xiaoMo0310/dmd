package com.dmd.mall.job;

import com.alibaba.fastjson.JSONArray;
import com.dmd.DateUtil;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.base.enums.ErrorCodeEnum;
import com.dmd.mall.constant.OmsApiConstant;
import com.dmd.mall.exceptions.PmsBizException;
import com.dmd.mall.model.domain.*;
import com.dmd.mall.model.vo.CourseOrderDetailVo;
import com.dmd.mall.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/11/27 13:18
 * @Description 订单定时任务
 */
@Component
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

    /**
     * 自动修改订单为进行中
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void updateOrderProcessing(){
        //查询已支付的学证潜水订单
        List<CourseOrderDetailVo> omsOrders = omsOrderService.queryOrderListByStatus(1, OmsApiConstant.OrderStatusEnum.PAID.getCode());
        omsOrders.forEach(omsOrder -> {
            List<Map> maps = (List<Map>) JSONArray.parse(omsOrder.getSpec());
            maps.forEach(map -> {
                String key = (String) map.get("key");
                if(key.equals("开始时间")){
                    Date date = DateUtil.resolverDate((String) map.get("value"));
                    if(System.currentTimeMillis() >= date.getTime()){
                        //修改订单状态为进行中
                        LoginAuthDto loginAuthDto = new LoginAuthDto();
                        loginAuthDto.setUserId(omsOrder.getUserId());
                        loginAuthDto.setUserType("member");
                        loginAuthDto.setUserName("定时修改");
                        omsOrderService.updateOrderStatus(loginAuthDto, omsOrder.getOrderSn(), OmsApiConstant.OrderStatusEnum.SHIPPED.getCode());
                    }
                }
            });
        });
    }

    /**
     * 定时清理未支付的订单
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void clearNoPayOrder(){
        OmsOrderSetting omsOrderSetting = findOmsOrderSettingMessage();
        Integer normalOrderOvertime = omsOrderSetting.getNormalOrderOvertime();
        //查询所有未支付的订单
        List<OmsOrder> omsOrders = omsOrderService.selectOrderByStatus(0);
        omsOrders.forEach(order -> {
            Date lastDate = DateUtil.minuteShit("yyyy-MM-dd HH:mm:ss", order.getCreatedTime(), normalOrderOvertime);
            if(System.currentTimeMillis() >= lastDate.getTime()){
                LoginAuthDto loginAuthDto = new LoginAuthDto();
                loginAuthDto.setUserName("系统");
                loginAuthDto.setUserId(0L);
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
    @Scheduled(cron = "0 0/1 * * * ?")
    public void clearUnauditedOrders(){
        OmsOrderSetting omsOrderSetting = findOmsOrderSettingMessage();
        Integer returnOrderOvertime = omsOrderSetting.getReturnOrderOvertime();
        //查询待审核的售后订单
        List<OmsOrderReturnApply> orderReturnApplies = omsOrderReturnApplyService.selectReturnOrderByStatus(0);
        orderReturnApplies.forEach(omsOrderReturnApply -> {
            Date lastDate = DateUtil.dateshit("yyyy-MM-dd HH:mm:ss", omsOrderReturnApply.getCreatedTime(), returnOrderOvertime);
            if(System.currentTimeMillis() >= lastDate.getTime()){
                LoginAuthDto loginAuthDto = new LoginAuthDto();
                loginAuthDto.setUserName("系统");
                loginAuthDto.setUserId(0L);
                //修改售后订单未审核通过
                int result = omsOrderReturnApplyService.updateOmsReturnStatus(loginAuthDto, omsOrderReturnApply.getId(), 1);
                if(result <= 0){
                    throw new PmsBizException("定时修改售后订单状态异常");
                }
            }
        });
    }

    /**
     * 定时自动完成订单并好评(凌晨1:15执行)
     *//*
    @Scheduled(cron = "0 15 1 * * ?")
    public void AutomaticOrderFulfillment(){
        OmsOrderSetting omsOrderSetting = findOmsOrderSettingMessage();
        Integer finishOvertime = omsOrderSetting.getFinishOvertime();

    }
*/
    /**
     * 查询订单配置信息
     * @return
     */
    public OmsOrderSetting findOmsOrderSettingMessage(){
        OmsOrderSetting omsOrderSetting = orderSettingService.selectByKey(1);
        return omsOrderSetting;
    }

    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
        Date date1 = DateUtil.minuteShit("yyyy-MM-dd HH:mm:ss", date, -10);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date1));
    }
}
