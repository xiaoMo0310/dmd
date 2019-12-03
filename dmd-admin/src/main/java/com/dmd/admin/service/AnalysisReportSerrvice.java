package com.dmd.admin.service;

import com.dmd.admin.model.vo.AnalysisReportVo;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: AnalysisReportSerrvice
 * @projectName dmd
 * @description: TODO
 * @date 2019/12/39:29
 */
public interface AnalysisReportSerrvice {
    List<AnalysisReportVo> queryAnalysisReport(Integer pageNum, Integer pageSize, AnalysisReportVo analysisReportVo);

    List<AnalysisReportVo> queryAnalysisReports(AnalysisReportVo analysisReportVo);
}
