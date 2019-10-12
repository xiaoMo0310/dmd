package com.dmd.mall.service.impl;

import com.dmd.mall.model.domain.UmsShop;
import com.dmd.mall.mapper.UmsShopMapper;
import com.dmd.mall.service.UmsShopService;
import com.dmd.core.support.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 店铺表 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-11
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UmsShopServiceImpl extends BaseService<UmsShop> implements UmsShopService {

    @Autowired
    private UmsShopMapper umsShopMapper;
}
