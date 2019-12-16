package com.dmd.admin.mapper;

import com.dmd.admin.model.domain.IntegralGiftsBean;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: IntegralGiftsMapper
 * @projectName dmd-masters
 * @description:
 * @date 2019/10/2916:35
 */
public interface IntegralGiftsMapper {
    List<IntegralGiftsBean> queryIntegralGifts(IntegralGiftsBean integralGiftsBean);

    int updateIntegralGiftsById(IntegralGiftsBean integralGiftsBean);

    IntegralGiftsBean findIntegralGiftsInfoById(Long id);

    int addIntegralGifts(IntegralGiftsBean integralGiftsBean);

    int deleteIntegralGiftsById(List<Long> ids);

    int updateIntegralGiftsPass(List<Long> ids);

    int updateIntegralGiftsNoPass(List<Long> ids);
}
