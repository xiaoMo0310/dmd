package com.dmd.admin.web;

import com.dmd.admin.model.domain.TopicBean;
import com.dmd.admin.model.vo.UmsOrderStatisticsVo;
import com.dmd.admin.service.UmsOrderStatisticsService;
import com.dmd.admin.utils.ExcelUtil;
import com.dmd.base.result.CommonPage;
import com.dmd.base.result.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author ChenYanbing
 * @title: UmsOrderStatisticsController
 * @projectName dmd
 * @description: TODO
 * @date 2019/11/2515:47
 */
@Controller
@Api(tags = "UmsOrderStatisticsController", description = "订单明细信息管理统计")
@RequestMapping("/orderStatistics")
public class UmsOrderStatisticsController {


    @Autowired
    private UmsOrderStatisticsService umsOrderStatisticsService;

    /**
     * 分页查询订单明细信息/条查
     * @param pageNum
     * @param pageSize
     * @param umsOrderStatisticsVo
     * @return
     */
    @ApiOperation("分页查询订单明细信息/条查")
    @RequestMapping(value = "/selectOrderStatistics",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UmsOrderStatisticsVo>> queryOrderStatisticsPage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                                   UmsOrderStatisticsVo umsOrderStatisticsVo) {
        List<UmsOrderStatisticsVo> umsOrderStatisticsList = umsOrderStatisticsService.queryOrderStatisticsPage(pageNum,pageSize,umsOrderStatisticsVo);
        return CommonResult.success(CommonPage.restPage(umsOrderStatisticsList));
    }

    @ApiOperation("导出订单明细信息xls")
    @RequestMapping(value = "/exportOrderStatistics",method = RequestMethod.GET)
    public CommonResult export(HttpServletResponse response,UmsOrderStatisticsVo umsOrderStatisticsVo) throws Exception {

        //模拟从数据库获取需要导出的数据
        List<UmsOrderStatisticsVo> umsOrderStatisticsList = umsOrderStatisticsService.queryOrderStatistics(umsOrderStatisticsVo);

        //导出操作
        ExcelUtil.exportExcel(umsOrderStatisticsList,"订单明细信息","订单明细信息",UmsOrderStatisticsVo.class,"订单明细信息.xls",response);

        return CommonResult.success("导出成功");
    }
}
