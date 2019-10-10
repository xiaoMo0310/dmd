package com.dmd.mall.service.impl;

import com.dmd.mall.model.domain.PmsProductAttribute;
import com.dmd.mall.mapper.PmsProductAttributeMapper;
import com.dmd.mall.service.PmsProductAttributeService;
import com.dmd.core.support.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 商品属性参数表 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-10
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PmsProductAttributeServiceImpl extends BaseService<PmsProductAttribute> implements PmsProductAttributeService {

    @Autowired
    private PmsProductAttributeMapper pmsProductAttributeMapper;
}
