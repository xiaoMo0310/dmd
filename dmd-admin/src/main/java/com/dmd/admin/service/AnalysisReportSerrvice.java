package com.dmd.admin.service;

import com.dmd.admin.model.vo.AnalysisReportVo;
import com.dmd.admin.model.vo.SalesStatisticsVo;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: AnalysisReportSerrvice
 * @projectName dmd
 * @description:
 * @date 2019/12/39:29
 */
public interface AnalysisReportSerrvice {
    List<AnalysisReportVo> queryAnalysisReport(Integer pageNum, Integer pageSize, AnalysisReportVo analysisReportVo);

    List<AnalysisReportVo> queryAnalysisReports(AnalysisReportVo analysisReportVo);

    List<SalesStatisticsVo> querySalesStatistics(Integer pageNum, Integer pageSize, SalesStatisticsVo salesStatisticsVo);

    List<SalesStatisticsVo> querySalesStatisticsAll(SalesStatisticsVo salesStatisticsVo);
}
