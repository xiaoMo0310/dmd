package com.dmd.mall.job;

import com.alibaba.fastjson.JSONArray;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.mall.constant.OmsApiConstant;
import com.dmd.mall.model.vo.CourseOrderDetailVo;
import com.dmd.mall.service.OmsOrderService;
import com.dmd.mall.service.PmsCourseProductService;
import com.xiaoleilu.hutool.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/11/27 13:18
 * @Description 订单定时任务
 */
@Controller
public class OrderJob {

    @Autowired
    private PmsCourseProductService courseProductService;
    @Autowired
    private OmsOrderService omsOrderService;

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
                    Date date = DateUtil.parse((String) map.get("value"), "yyyy-MM-dd HH:mm:ss");
                    if(omsOrder.getOrderSn().equals("201911210100000011")){
                        System.out.println(System.currentTimeMillis());
                        System.out.println(date.getTime());
                        System.out.println(System.currentTimeMillis() >= date.getTime());
                    }
                    if(System.currentTimeMillis() >= date.getTime()){
                        //修改订单状态为进行中
                        LoginAuthDto loginAuthDto = new LoginAuthDto();
                        System.out.println(omsOrder.getUserName());
                        System.out.println(omsOrder.getUserId());
                        loginAuthDto.setUserId(omsOrder.getUserId());
                        loginAuthDto.setUserType("member");
                        loginAuthDto.setUserName("定时修改");
                        omsOrderService.updateOrderStatus(loginAuthDto, omsOrder.getOrderSn(), OmsApiConstant.OrderStatusEnum.SHIPPED.getCode());
                    }
                }

            });
        });
    }
}
