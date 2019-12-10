package com.dmd.mall.job;

import com.dmd.DateUtil;
import com.dmd.mall.model.domain.PmsCourseProduct;
import com.dmd.mall.service.PmsCourseProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/12/3 15:34
 * @Description 商品任务类
 */
@Component
public class ProductJob {

    @Autowired
    private PmsCourseProductService courseProductService;

    /**
     * 发布的商品到活动开始时间停止销售 到结束时间自动下架(每日凌晨一点执行)
     */
    @Scheduled(cron = "0 0 1 * * ? ")
    public void updateProductTakeOff(){
        //查询全部的在售的商品信息
        List<PmsCourseProduct> courseProducts = courseProductService.findCourseProductByStatus(1);
        courseProducts.forEach(pmsCourseProduct -> {
            Date startDate = DateUtil.resolverDate(DateUtil.formartDate(pmsCourseProduct.getStartTime()));
            Date endDate = DateUtil.resolverDate(DateUtil.formartDate(pmsCourseProduct.getEndTime()));
            if(System.currentTimeMillis() >= startDate.getTime()){
                //修改订单状态为停止销售
                int result = courseProductService.updateCourseProductStatus(pmsCourseProduct.getId(), 4);
            }
            if(System.currentTimeMillis() >= endDate.getTime()){
                //修改订单状态为下架
                int result = courseProductService.updateCourseProductStatus(pmsCourseProduct.getId(), 2);
            }
        });
    }
}
