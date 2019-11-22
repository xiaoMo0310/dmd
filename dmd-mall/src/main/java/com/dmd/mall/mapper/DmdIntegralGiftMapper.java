package com.dmd.mall.mapper;

import com.dmd.core.mybatis.MyMapper;
import com.dmd.mall.model.domain.DmdIntegralGift;
import com.dmd.mall.model.domain.IntegralGiftsBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

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

    List<IntegralGiftsBean> queryIntegralGifts();

    List<IntegralGiftsBean> queryIntegralGiftsById(Long id);

    int addIntegralGifts(IntegralGiftsBean integralGiftsBean);

    IntegralGiftsBean findIntegralGiftsInfoById(Long id);

    int updateIntegralGiftsById(IntegralGiftsBean integralGiftsBean);

    int deleteIntegralGiftsById(Long id);

    DmdIntegralGift selectById(Long productId);
}
