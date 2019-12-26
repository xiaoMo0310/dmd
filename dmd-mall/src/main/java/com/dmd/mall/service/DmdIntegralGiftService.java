package com.dmd.mall.service;

import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.IService;
import com.dmd.mall.model.domain.DmdIntegralGift;
import com.dmd.mall.model.domain.IntegralGiftsBean;
import com.dmd.mall.model.domain.IntegralGiftsSpeBean;
import com.dmd.mall.model.domain.OmsOrderItem;
import com.dmd.mall.model.vo.IntegralProductVo;

import java.util.List;

/**
 * <p>
 * 积分好礼兑换表，兑换物品由后台管理员编辑。 服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-13
 */
public interface DmdIntegralGiftService extends IService<DmdIntegralGift> {

    List<IntegralGiftsBean> queryIntegralGifts();

    List<IntegralGiftsBean> queryIntegralGiftsPage(Integer pageNum, Integer pageSize);

    List<IntegralGiftsBean> queryIntegralGiftsById(Long id);

    int addIntegralGifts(IntegralGiftsBean integralGiftsBean);

    IntegralGiftsBean findIntegralGiftsInfoById(Long id);

    int updateIntegralGiftsById(IntegralGiftsBean integralGiftsBean);

    int deleteIntegralGiftsById(Long id);

    List<IntegralGiftsSpeBean> queryIntegralGiftsSpeById(Long id);

    Integer selectIntegralGiftsSpecStock(Long id, String size, String color);

    /**
     * 封装积分好礼订单详情数据
     * @param integralGift
     * @return
     */
    OmsOrderItem createIntegralOrderItem(DmdIntegralGift integralGift, Long skuId, Integer quantity);

    /**
     * 结算积分商品
     * @param loginAuthDto
     * @param productSkuId
     * @param productQuantity
     * @return
     */
    IntegralProductVo settlementIntegralProduct(LoginAuthDto loginAuthDto, Long productSkuId, Integer productQuantity);

    Integer selectIntegralGiftsSpeNum(Long id);
}
