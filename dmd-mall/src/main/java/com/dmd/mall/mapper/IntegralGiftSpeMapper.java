package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.IntegralGiftsSpeBean;
import org.apache.ibatis.annotations.Param;

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

    Integer selectIntegralGiftsSpecStock(@Param("id")Long id,@Param("size") String size,@Param("color") String color);
}
