package com.dmd.mall.service.impl;

import com.dmd.core.support.BaseService;
import com.dmd.mall.mapper.PmsProductsMapper;
import com.dmd.mall.model.domain.PmsProduct;
import com.dmd.mall.model.dto.SortDto;
import com.dmd.mall.model.vo.PmsShipProductListVo;
import com.dmd.mall.service.PmsProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-09
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PmsProductServiceImpl extends BaseService<PmsProduct> implements PmsProductService {

    @Autowired
    private PmsProductsMapper pmsProductMapper;

    @Override
    public PageInfo findShipSleepsProduct(SortDto sortDto) {
        //product_category_id
        PageHelper.startPage(sortDto.getPageNum(), sortDto.getPageSize());
        List<PmsShipProductListVo> shipSleepsProdects = pmsProductMapper.selectShipSleepsProduct(sortDto);
        return new PageInfo(shipSleepsProdects);
    }
}
