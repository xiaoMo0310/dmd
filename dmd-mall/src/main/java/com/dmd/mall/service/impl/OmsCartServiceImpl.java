package com.dmd.mall.service.impl;

import com.dmd.mall.model.domain.OmsCart;
import com.dmd.mall.mapper.OmsCartMapper;
import com.dmd.mall.service.OmsCartService;
import com.dmd.core.support.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 购物车表 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-12
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OmsCartServiceImpl extends BaseService<OmsCart> implements OmsCartService {

    @Autowired
    private OmsCartMapper omsCartMapper;
}
