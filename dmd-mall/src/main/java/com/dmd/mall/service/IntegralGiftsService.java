package com.dmd.mall.service;

import com.dmd.mall.model.domain.IntegralGiftsBean;
import com.dmd.mall.model.domain.IntegralGiftsSpeBean;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: IntegralGiftsService
 * @projectName dmd-master
 * @description: TODO
 * @date 2019/10/910:32
 */
public interface IntegralGiftsService {
    List<IntegralGiftsBean> queryIntegralGifts();

    List<IntegralGiftsBean> queryIntegralGiftsPage(Integer pageNum, Integer pageSize);

    List<IntegralGiftsBean> queryIntegralGiftsById(Long id);

    int addIntegralGifts(IntegralGiftsBean integralGiftsBean);

    IntegralGiftsBean findIntegralGiftsInfoById(Long id);

    int updateIntegralGiftsById(IntegralGiftsBean integralGiftsBean);

    int deleteIntegralGiftsById(Long id);

    List<IntegralGiftsSpeBean> queryIntegralGiftsSpeById(Long id);

    Integer selectIntegralGiftsSpecStock(Long id, String size, String color);
}
