package com.dmd.mall.service.impl;

import com.dmd.mall.model.domain.DmdIntegralGiftSpe;
import com.dmd.mall.mapper.DmdIntegralGiftSpeMapper;
import com.dmd.mall.service.DmdIntegralGiftSpeService;
import com.dmd.core.support.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-19
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DmdIntegralGiftSpeServiceImpl extends BaseService<DmdIntegralGiftSpe> implements DmdIntegralGiftSpeService {

    @Autowired
    private DmdIntegralGiftSpeMapper dmdIntegralGiftSpeMapper;

    @Override
    public DmdIntegralGiftSpe selectByGiftId(Long giftId) {
        return dmdIntegralGiftSpeMapper.selectByGiftId(giftId);
    }
}
