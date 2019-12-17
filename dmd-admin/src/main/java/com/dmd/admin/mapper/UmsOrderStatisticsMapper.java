package com.dmd.admin.mapper;

import com.dmd.admin.model.vo.UmsOrderStatisticsVo;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: UmsOrderStatisticsMapper
 * @projectName dmd
 * @description:
 * @date 2019/11/2515:53
 */
public interface UmsOrderStatisticsMapper {
    List<UmsOrderStatisticsVo> queryOrderStatisticsPage(UmsOrderStatisticsVo umsOrderStatisticsVo);
}
