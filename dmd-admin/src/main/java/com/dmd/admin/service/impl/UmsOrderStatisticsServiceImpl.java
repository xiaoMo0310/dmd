package com.dmd.admin.service.impl;

import com.dmd.admin.mapper.UmsOrderStatisticsMapper;
import com.dmd.admin.model.vo.UmsOrderStatisticsVo;
import com.dmd.admin.service.UmsOrderStatisticsService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: UmsOrderStatisticsServiceImpl
 * @projectName dmd
 * @description:
 * @date 2019/11/2515:52
 */
@Service
public class UmsOrderStatisticsServiceImpl implements UmsOrderStatisticsService{

    @Autowired
    private UmsOrderStatisticsMapper umsOrderStatisticsMapper;

    @Override
    public List<UmsOrderStatisticsVo> queryOrderStatisticsPage(Integer pageNum, Integer pageSize, UmsOrderStatisticsVo umsOrderStatisticsVo) {
        PageHelper.startPage(pageNum, pageSize);
        //用户订单
        List<UmsOrderStatisticsVo> umsOrderStatisticsVos = umsOrderStatisticsMapper.queryOrderStatisticsPage(umsOrderStatisticsVo);
        //教练订单
        List<UmsOrderStatisticsVo> umsOrderStatisticsVosCoach = umsOrderStatisticsMapper.queryOrderStatisticsPageCoach(umsOrderStatisticsVo);
        //数据合并
        umsOrderStatisticsVos.addAll(umsOrderStatisticsVosCoach);
        umsOrderStatisticsVos.sort((o1, o2) -> o2.getOrderCreatedTime().compareTo(o1.getOrderCreatedTime()));
        return umsOrderStatisticsVos;
    }

    @Override
    public List<UmsOrderStatisticsVo> queryOrderStatistics(UmsOrderStatisticsVo umsOrderStatisticsVo) {
        //用户订单
        List<UmsOrderStatisticsVo> umsOrderStatisticsVos = umsOrderStatisticsMapper.queryOrderStatisticsPage(umsOrderStatisticsVo);
        //教练订单
        List<UmsOrderStatisticsVo> umsOrderStatisticsVosCoach = umsOrderStatisticsMapper.queryOrderStatisticsPageCoach(umsOrderStatisticsVo);
        //数据合并
        umsOrderStatisticsVos.addAll(umsOrderStatisticsVosCoach);
        umsOrderStatisticsVos.sort((o1, o2) -> o2.getOrderCreatedTime().compareTo(o1.getOrderCreatedTime()));
        return umsOrderStatisticsVos;
    }
}
