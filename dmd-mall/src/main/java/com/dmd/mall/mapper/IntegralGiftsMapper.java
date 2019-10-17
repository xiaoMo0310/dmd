package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.IntegralGiftsBean;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: IntegralGiftsMapper
 * @projectName dmd-master
 * @description: TODO
 * @date 2019/10/910:33
 */
public interface IntegralGiftsMapper {
    List<IntegralGiftsBean> queryIntegralGifts();

    List<IntegralGiftsBean> queryIntegralGiftsById(Long id);

    int addIntegralGifts(IntegralGiftsBean integralGiftsBean);

    IntegralGiftsBean findIntegralGiftsInfoById(Long id);

    int updateIntegralGiftsById(IntegralGiftsBean integralGiftsBean);

    int deleteIntegralGiftsById(Long id);
}
