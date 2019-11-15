package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.DmdIntegralGift;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 积分好礼兑换表，兑换物品由后台管理员编辑。 Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-13
 */
@Mapper
@Component
public interface DmdIntegralGiftMapper extends MyMapper<DmdIntegralGift> {

}
