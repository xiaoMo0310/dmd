package com.dmd.mall.mapper;

import com.dmd.core.mybatis.MyMapper;
import com.dmd.mall.model.domain.DmdIntegralGiftSpe;
import com.dmd.mall.model.domain.IntegralGiftsSpeBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-19
 */
@Mapper
@Component
public interface DmdIntegralGiftSpeMapper extends MyMapper<DmdIntegralGiftSpe> {

    List<IntegralGiftsSpeBean> queryIntegralGiftsSpeById(Long id);

    Integer selectIntegralGiftsSpecStock(@Param("id")Long id, @Param("size") String size, @Param("color") String color);

    DmdIntegralGiftSpe selectByGiftId(Long giftId);
}
