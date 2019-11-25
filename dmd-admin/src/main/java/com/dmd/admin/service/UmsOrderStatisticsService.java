package com.dmd.admin.service;

import com.dmd.admin.model.vo.UmsOrderStatisticsVo;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: UmsOrderStatisticsService
 * @projectName dmd
 * @description: TODO
 * @date 2019/11/2515:52
 */
public interface UmsOrderStatisticsService {
    List<UmsOrderStatisticsVo> queryOrderStatisticsPage(Integer pageNum, Integer pageSize, UmsOrderStatisticsVo umsOrderStatisticsVo);

    List<UmsOrderStatisticsVo> queryOrderStatistics(UmsOrderStatisticsVo umsOrderStatisticsVo);
}
