package com.dmd.mall.service.impl;

import com.dmd.mall.model.domain.PmsSkuStock;
import com.dmd.mall.mapper.PmsProductSkuMapper;
import com.dmd.mall.service.PmsSkuStockService;
import com.dmd.core.support.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * sku的库存 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-11
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PmsSkuStockServiceImpl extends BaseService<PmsSkuStock> implements PmsSkuStockService {

    @Autowired
    private PmsProductSkuMapper pmsProductSkuMapper;
}
