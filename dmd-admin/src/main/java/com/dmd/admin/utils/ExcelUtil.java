package com.dmd.admin.utils;

/**
 * @author ChenYanbing
 * @title: ExcelUtil
 * @projectName dmd
 * @description: TODO
 * @date 2019/11/2516:16
 */


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class ExcelUtil {
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName, boolean isCreateHeader, HttpServletResponse response) throws Exception {
        ExportParams exportParams = new ExportParams(title, sheetName);
        exportParams.setCreateHeadRows(isCreateHeader);
        defaultExport(list, pojoClass, fileName, response, exportParams);

    }
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass,String fileName, HttpServletResponse response) throws Exception {
        defaultExport(list, pojoClass, fileName, response, new ExportParams(title, sheetName));
    }


    public static void exportExcel(List<?> list, ExportParams exportParams, Class<?> pojoClass,String fileName, HttpServletResponse response) throws Exception {
        defaultExport(list, pojoClass, fileName, response, exportParams);
    }
    public static void exportExcel(List<Map<String, Object>> list, String fileName, HttpServletResponse response) throws Exception {
        defaultExport(list, fileName, response);
    }

    public static void exportExcel(Workbook workbook,String fileName,HttpServletResponse response) throws Exception {
        if (workbook != null) {
            downLoadExcel(fileName, response, workbook);
        }
    }

    private static void defaultExport(List<?> list, Class<?> pojoClass, String fileName, HttpServletResponse response, ExportParams exportParams) throws Exception {
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, list);
        if (workbook != null) {
            downLoadExcel(fileName, response, workbook);
        }
    }

    private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) throws Exception{
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            throw new Exception(e.getMessage());
        }
    }

    private static void defaultExport(List<Map<String, Object>> list, String fileName, HttpServletResponse response) throws Exception {
        Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);
        if (workbook != null){
            downLoadExcel(fileName, response, workbook);
        }
    }

    public static <T> List<T> importExcel(String filePath, Integer titleRows, Integer headerRows, Class<T> pojoClass)throws Exception {
        if (StringUtils.isBlank(filePath)) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
        } catch (NoSuchElementException e) {
            throw new Exception("模板不能为空");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return list;
    }

    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass) throws Exception {
        if (file == null) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
        } catch (NoSuchElementException e) {
            throw new Exception("excel文件不能为空");
        } catch (Exception e) {
            throw new Exception(e);
        }

        return list;
    }

    public static void handleExcel(HttpServletResponse response, String fileName, TemplateExportParams params, Map<String, Object> map) {
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);


        if (workbook != null) {
            try {
                response.setCharacterEncoding("UTF-8");
                response.setHeader("content-Type", "application/vnd.ms-excel");
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
                workbook.write(response.getOutputStream());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

/*    public static ArrayList<ExcelExportEntity> getExcelColumnList(List<UmsOrderStatisticsVo> detailList) {
        ArrayList<ExcelExportEntity> columnList = new ArrayList<>();
        if (detailList != null && detailList.size() > 0) {
            for (UmsOrderStatisticsVo firstTempDetail : detailList) {
                //处理自定义表头
                ExcelExportEntity excelExportEntity = new ExcelExportEntity(firstTempDetail.getName(), firstTempDetail.getName());
                List<UmsOrderStatisticsVo> items = (List<UmsOrderStatisticsVo>) firstTempDetail.getBirthday();
                if (items != null && items.size() > 0) {
                    List<ExcelExportEntity> excelExportList = new ArrayList<>();
                    for (Person item : items) {
                        excelExportList.add(new ExcelExportEntity(item.getName(), item.getName()));
                    }
                    excelExportEntity.setList(excelExportList);
                }
                columnList.add(excelExportEntity);
            }
        }
        return columnList;
    }*/

    /**
     * 获取下载路径
     *
     * @param filename 文件名称
     */
    public static String getAbsoluteFile(String filename)
    {
        String downloadPath = "D://" + filename;
        File desc = new File(downloadPath);
        if (!desc.getParentFile().exists())
        {
            desc.getParentFile().mkdirs();
        }
        return downloadPath;
    }
}

