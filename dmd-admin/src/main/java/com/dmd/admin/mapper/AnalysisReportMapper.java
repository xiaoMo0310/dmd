package com.dmd.admin.mapper;

import com.dmd.admin.model.vo.AnalysisReportVo;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: AnalysisReportMapper
 * @projectName dmd
 * @description: TODO
 * @date 2019/12/39:31
 */
public interface AnalysisReportMapper {
    List<AnalysisReportVo> queryAnalysisReport(AnalysisReportVo analysisReportVo);
}