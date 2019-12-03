package com.dmd.admin.service.impl;

import com.dmd.admin.mapper.AnalysisReportMapper;
import com.dmd.admin.model.vo.AnalysisReportVo;
import com.dmd.admin.service.AnalysisReportSerrvice;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: AnalysisReportSerrviceImpl
 * @projectName dmd
 * @description: TODO
 * @date 2019/12/39:30
 */
@Service
public class AnalysisReportSerrviceImpl implements AnalysisReportSerrvice{

    @Autowired
    private AnalysisReportMapper analysisReportMapper;

    @Override
    public List<AnalysisReportVo> queryAnalysisReport(Integer pageNum, Integer pageSize, AnalysisReportVo analysisReportVo) {
        PageHelper.startPage(pageNum, pageSize);
        return analysisReportMapper.queryAnalysisReport(analysisReportVo);
    }

    @Override
    public List<AnalysisReportVo> queryAnalysisReports(AnalysisReportVo analysisReportVo) {
        return analysisReportMapper.queryAnalysisReport(analysisReportVo);
    }
}
