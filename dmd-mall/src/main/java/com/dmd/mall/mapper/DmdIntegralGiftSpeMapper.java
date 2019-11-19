package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.DmdIntegralGiftSpe;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

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

    DmdIntegralGiftSpe selectByGiftId(Long giftId);
}
