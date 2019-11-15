package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.IntegralGiftsSpeBean;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: IntegralGiftSpeMapper
 * @projectName dmd
 * @description: TODO
 * @date 2019/11/1516:18
 */
public interface IntegralGiftSpeMapper {
    List<IntegralGiftsSpeBean> queryIntegralGiftsSpeById(Long id);
}
