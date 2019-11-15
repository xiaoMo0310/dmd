package com.dmd.mall.service.impl;

import com.dmd.mall.model.domain.DmdIntegralGift;
import com.dmd.mall.mapper.DmdIntegralGiftMapper;
import com.dmd.mall.service.DmdIntegralGiftService;
import com.dmd.core.support.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 积分好礼兑换表，兑换物品由后台管理员编辑。 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-13
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DmdIntegralGiftServiceImpl extends BaseService<DmdIntegralGift> implements DmdIntegralGiftService {

    @Autowired
    private DmdIntegralGiftMapper dmdIntegralGiftMapper;
}
