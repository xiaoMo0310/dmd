package com.dmd.admin.mapper;

import com.dmd.admin.model.domain.IntegralGiftsSpeBean;
import com.dmd.admin.model.domain.UmsIntegrationChangeHistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: IntegralGiftsSpeMapper
 * @projectName dmd
 * @description:
 * @date 2019/11/1515:23
 */
public interface IntegralGiftsSpeMapper {
    int addIntegralGiftsSpe(@Param("list") List<IntegralGiftsSpeBean> list);

    List<IntegralGiftsSpeBean> queryIntegralGiftsSpeById(Long id);

    int addGiftsSpe(IntegralGiftsSpeBean integralGiftsSpeBean);

    int updateIntegralGiftsSpe(IntegralGiftsSpeBean integralGiftsSpeBean);

    IntegralGiftsSpeBean findIntegralGiftsSpeInfoById(Long id);

    int deleteIntegralGiftsSpeById(List<Long> ids);

}
