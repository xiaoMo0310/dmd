package com.dmd.mall.service;

import com.dmd.mall.model.domain.DmdIntegralGiftSpe;
import com.dmd.core.support.IService;

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
}
