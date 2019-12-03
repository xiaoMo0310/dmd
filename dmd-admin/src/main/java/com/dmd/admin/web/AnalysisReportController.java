package com.dmd.admin.web;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.dmd.admin.model.vo.AnalysisReportVo;
import com.dmd.admin.model.vo.UmsOrderStatisticsVo;
import com.dmd.admin.service.AnalysisReportSerrvice;
import com.dmd.admin.utils.ExcelUtil;
import com.dmd.admin.utils.FileUtils;
import com.dmd.base.result.CommonPage;
import com.dmd.base.result.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author ChenYanbing
 * @title: AnalysisReportController
 * @projectName dmd
 * @description: TODO
 * @date 2019/12/39:27
 */
@Controller
@Api(tags = "AnalysisReportController", description = "分析报表管理统计")
@RequestMapping("/analysis")
public class AnalysisReportController {

    @Autowired
    private AnalysisReportSerrvice analysisReportSerrvice;

    /**
     * 分页查询分析报表统计/条查
     * @param pageNum
     * @param pageSize
     * @param analysisReportVo
     * @return
     */
    @ApiOperation("分页查询分析报表统计/条查")
    @RequestMapping(value = "/selectAnalysisReport",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<AnalysisReportVo>> queryAnalysisReport(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                          AnalysisReportVo analysisReportVo) {
        if(analysisReportVo.getStartTime() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(analysisReportVo.getStartTime());
            if(StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "00");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                analysisReportVo.setStartTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(analysisReportVo.getEndTime() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(analysisReportVo.getEndTime());
            if(StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "24");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                analysisReportVo.setEndTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        List<AnalysisReportVo> analysisReportVos = analysisReportSerrvice.queryAnalysisReport(pageNum,pageSize,analysisReportVo);
        return CommonResult.success(CommonPage.restPage(analysisReportVos));
    }
    /**
     * Easypoi导出Excel
     * @param umsOrderStatisticsVo
     * @param response
     * @return
     * @throws Exception
     */
    @ApiOperation("Easypoi导出Excel")
    @RequestMapping(value = "/exportOrderStatistics",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult exportOrderStatistics(AnalysisReportVo analysisReportVo,HttpServletResponse response) throws Exception{
        if(analysisReportVo.getStartTime() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(analysisReportVo.getStartTime());
            if(StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "00");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                analysisReportVo.setStartTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(analysisReportVo.getEndTime() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(analysisReportVo.getEndTime());
            if(StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "24");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                analysisReportVo.setEndTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        //从数据库获取需要导出的数据
        List<AnalysisReportVo> analysisReportVos = analysisReportSerrvice.queryAnalysisReports(analysisReportVo);
        ExportParams ex = new ExportParams("分析订单统计", "分析订单统计");
        Workbook workbook = ExcelExportUtil.exportExcel(ex,AnalysisReportVo.class,analysisReportVos);
        try {
            FileOutputStream fos = new FileOutputStream(ExcelUtil.getAbsoluteFile("分析订单统计"));
            workbook.write(fos);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return CommonResult.success("分析订单统计");
    }

    /**
     * 通用下载请求
     *
     * @param fileName 文件名称
     * @param delete 是否删除
     */
    @ApiOperation("Easypoi导出Excel")
    @RequestMapping(value = "/exportOrderStatistics2",method = RequestMethod.GET)
    @ResponseBody
    public void fileDownload(@RequestParam("fileName") String fileName,@RequestParam("delete") Boolean delete, HttpServletResponse response, HttpServletRequest request)
    {
        try
        {
            if (!FileUtils.isValidFilename(fileName))
            {
                throw new Exception(com.dmd.admin.utils.StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
            }
            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = "D://" + fileName;

            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, realFileName)+".xls");
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete)
            {
                FileUtils.deleteFile(filePath);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
