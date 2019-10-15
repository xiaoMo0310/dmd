package com.dmd.mall.service.impl;

import com.dmd.core.support.BaseService;
import com.dmd.mall.mapper.PmsSkuStockMapper;
import com.dmd.mall.model.domain.PmsSkuStock;
import com.dmd.mall.service.PmsSkuStockService;
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
    private PmsSkuStockMapper pmsProductSkuMapper;
}
