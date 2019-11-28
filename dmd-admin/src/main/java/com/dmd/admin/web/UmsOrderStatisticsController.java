package com.dmd.admin.web;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.dmd.admin.model.domain.TopicBean;
import com.dmd.admin.model.vo.UmsOrderStatisticsVo;
import com.dmd.admin.service.UmsOrderStatisticsService;
import com.dmd.admin.utils.ExcelUtil;
import com.dmd.admin.utils.FileUtils;
import com.dmd.admin.utils.StringUtils;
import com.dmd.base.result.CommonPage;
import com.dmd.base.result.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
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
        if(umsOrderStatisticsVo.getStartUserCreateTime() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(umsOrderStatisticsVo.getStartUserCreateTime());
            if(org.apache.commons.lang.StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "00");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                umsOrderStatisticsVo.setStartUserCreateTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(umsOrderStatisticsVo.getEndUserCreateTime() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(umsOrderStatisticsVo.getEndUserCreateTime());
            if(org.apache.commons.lang.StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "24");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                umsOrderStatisticsVo.setEndUserCreateTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(umsOrderStatisticsVo.getStartOrderCreatedTime() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(umsOrderStatisticsVo.getStartOrderCreatedTime());
            if(org.apache.commons.lang.StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "00");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                umsOrderStatisticsVo.setStartOrderCreatedTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(umsOrderStatisticsVo.getEndOrderCreatedTime() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(umsOrderStatisticsVo.getEndOrderCreatedTime());
            if(org.apache.commons.lang.StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "24");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                umsOrderStatisticsVo.setEndOrderCreatedTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(umsOrderStatisticsVo.getStartOrderPaymentTime() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(umsOrderStatisticsVo.getStartOrderPaymentTime());
            if(org.apache.commons.lang.StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "00");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                umsOrderStatisticsVo.setStartOrderPaymentTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(umsOrderStatisticsVo.getEndOrderPaymentTime() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(umsOrderStatisticsVo.getEndOrderPaymentTime());
            if(org.apache.commons.lang.StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "24");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                umsOrderStatisticsVo.setEndOrderPaymentTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


        if(umsOrderStatisticsVo.getStartOrderEndTime() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(umsOrderStatisticsVo.getStartOrderEndTime());
            if(org.apache.commons.lang.StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "00");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                umsOrderStatisticsVo.setStartOrderEndTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(umsOrderStatisticsVo.getEndOrderEndTime() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(umsOrderStatisticsVo.getEndOrderEndTime());
            if(org.apache.commons.lang.StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "24");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                umsOrderStatisticsVo.setEndOrderEndTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        List<UmsOrderStatisticsVo> umsOrderStatisticsList = umsOrderStatisticsService.queryOrderStatisticsPage(pageNum,pageSize,umsOrderStatisticsVo);
        return CommonResult.success(CommonPage.restPage(umsOrderStatisticsList));
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
    public CommonResult exportOrderStatistics(UmsOrderStatisticsVo umsOrderStatisticsVo,HttpServletResponse response) throws Exception{
        if(umsOrderStatisticsVo.getStartUserCreateTime() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(umsOrderStatisticsVo.getStartUserCreateTime());
            if(org.apache.commons.lang.StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "00");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                umsOrderStatisticsVo.setStartUserCreateTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(umsOrderStatisticsVo.getEndUserCreateTime() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(umsOrderStatisticsVo.getEndUserCreateTime());
            if(org.apache.commons.lang.StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "24");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                umsOrderStatisticsVo.setEndUserCreateTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(umsOrderStatisticsVo.getStartOrderCreatedTime() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(umsOrderStatisticsVo.getStartOrderCreatedTime());
            if(org.apache.commons.lang.StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "00");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                umsOrderStatisticsVo.setStartOrderCreatedTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(umsOrderStatisticsVo.getEndOrderCreatedTime() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(umsOrderStatisticsVo.getEndOrderCreatedTime());
            if(org.apache.commons.lang.StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "24");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                umsOrderStatisticsVo.setEndOrderCreatedTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(umsOrderStatisticsVo.getStartOrderPaymentTime() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(umsOrderStatisticsVo.getStartOrderPaymentTime());
            if(org.apache.commons.lang.StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "00");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                umsOrderStatisticsVo.setStartOrderPaymentTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(umsOrderStatisticsVo.getEndOrderPaymentTime() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(umsOrderStatisticsVo.getEndOrderPaymentTime());
            if(org.apache.commons.lang.StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "24");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                umsOrderStatisticsVo.setEndOrderPaymentTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


        if(umsOrderStatisticsVo.getStartOrderEndTime() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(umsOrderStatisticsVo.getStartOrderEndTime());
            if(org.apache.commons.lang.StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "00");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                umsOrderStatisticsVo.setStartOrderEndTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(umsOrderStatisticsVo.getEndOrderEndTime() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(umsOrderStatisticsVo.getEndOrderEndTime());
            if(org.apache.commons.lang.StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "24");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                umsOrderStatisticsVo.setEndOrderEndTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        //从数据库获取需要导出的数据
        List<UmsOrderStatisticsVo> umsOrderStatisticsList = umsOrderStatisticsService.queryOrderStatistics(umsOrderStatisticsVo);
        ExportParams ex = new ExportParams("订单明细信息", "订单明细信息");
        Workbook workbook = ExcelExportUtil.exportExcel(ex,UmsOrderStatisticsVo.class,umsOrderStatisticsList);
        try {
            FileOutputStream fos = new FileOutputStream(ExcelUtil.getAbsoluteFile("订单明细信息"));
            workbook.write(fos);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return CommonResult.success("订单明细信息");
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
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
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
