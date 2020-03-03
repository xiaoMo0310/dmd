package com.dmd.mall.service.impl;

import com.dmd.mall.model.domain.UmsShopLevelExplain;
import com.dmd.mall.mapper.UmsShopLevelExplainMapper;
import com.dmd.mall.service.UmsShopLevelExplainService;
import com.dmd.core.support.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 教练店铺店铺表 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-02
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UmsShopLevelExplainServiceImpl extends BaseService<UmsShopLevelExplain> implements UmsShopLevelExplainService {

    @Autowired
    private UmsShopLevelExplainMapper umsShopLevelExplainMapper;
}
