package com.dmd.admin.mapper;

import com.dmd.admin.model.domain.IntegralGiftsSpeBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: IntegralGiftsSpeMapper
 * @projectName dmd
 * @description: TODO
 * @date 2019/11/1515:23
 */
public interface IntegralGiftsSpeMapper {
    int addIntegralGiftsSpe(@Param("list") List<IntegralGiftsSpeBean> list);

    List<IntegralGiftsSpeBean> queryIntegralGiftsSpeById(Long id);

    int addGiftsSpe(IntegralGiftsSpeBean integralGiftsSpeBean);
}
