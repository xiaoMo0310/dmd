package com.dmd.admin.web;

import com.dmd.admin.model.vo.AnalysisReportVo;
import com.dmd.admin.model.vo.SalesStatisticsVo;
import com.dmd.admin.service.AnalysisReportSerrvice;
import com.dmd.base.result.CommonPage;
import com.dmd.base.result.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author ChenYanbing
 * @title: SalesStatisticsController
 * @projectName dmd
 * @description: TODO
 * @date 2019/12/416:48
 */
@Controller
@Api(tags = "SalesStatisticsController", description = "销售报表统计管理")
@RequestMapping("/sales")
public class SalesStatisticsController {

    @Autowired
    private AnalysisReportSerrvice analysisReportSerrvice;


    /**
     * 销售报表统计/条查
     *
     * @param pageNum
     * @param pageSize
     * @param salesStatisticsVo
     * @return
     */
    @ApiOperation("销售报表统计/条查")
    @RequestMapping(value = "/selectSalesStatistics", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<SalesStatisticsVo>> querySalesStatistics(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                            SalesStatisticsVo salesStatisticsVo) {
        if (salesStatisticsVo.getStartTime() != null) {
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(salesStatisticsVo.getStartTime());
            if (StringUtils.isNotBlank(dateStr)) {
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "00");
                time = sb.toString();
            }

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                salesStatisticsVo.setStartTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (salesStatisticsVo.getEndTime() != null) {
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(salesStatisticsVo.getEndTime());
            if (StringUtils.isNotBlank(dateStr)) {
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "24");
                time = sb.toString();
            }

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                salesStatisticsVo.setEndTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        List<SalesStatisticsVo> analysisReportVos = analysisReportSerrvice.querySalesStatistics(pageNum, pageSize, salesStatisticsVo);
        return CommonResult.success(CommonPage.restPage(analysisReportVos));
    }
}