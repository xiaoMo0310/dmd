package com.dmd.mall.service;

import com.dmd.core.support.IService;
import com.dmd.mall.model.domain.DmdIntegralGiftSpe;
import com.dmd.mall.model.domain.IntegralGiftsSpeBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-19
 */
public interface DmdIntegralGiftSpeService extends IService<DmdIntegralGiftSpe> {

    DmdIntegralGiftSpe selectByGiftId(Long giftId);

    List<IntegralGiftsSpeBean> queryIntegralGiftsSpeById(Long id);

    Integer selectIntegralGiftsSpecStock(Long id, String size, String color);

    Integer selectIntegralGiftsSpeNum(Long id);
}
