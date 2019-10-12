package com.dmd.mall.service.impl;

import com.dmd.core.support.BaseService;
import com.dmd.mall.mapper.PmsCommentMapper;
import com.dmd.mall.mapper.PmsProductSkuMapper;
import com.dmd.mall.mapper.PmsProductsMapper;
import com.dmd.mall.mapper.UmsShopMapper;
import com.dmd.mall.model.domain.PmsComment;
import com.dmd.mall.model.domain.PmsProduct;
import com.dmd.mall.model.domain.PmsSkuStock;
import com.dmd.mall.model.domain.UmsShop;
import com.dmd.mall.model.dto.SortDto;
import com.dmd.mall.model.vo.PmsProductListVo;
import com.dmd.mall.model.vo.PmsProductVo;
import com.dmd.mall.service.PmsProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
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
    @Autowired
    private PmsCommentMapper pmsCommentMapper;
    @Autowired
    private PmsProductSkuMapper pmsProductSkuMapper;
    @Autowired
    private UmsShopMapper umsShopMapper;

    @Override
    public PageInfo findShipSleepsProduct(SortDto sortDto) {
        PageHelper.startPage(sortDto.getPageNum(), sortDto.getPageSize());
        List<PmsProductListVo> shipSleepsProducts = pmsProductMapper.selectShipSleepsProduct(sortDto, 55L);
        return new PageInfo(shipSleepsProducts);
    }

    @Override
    public PmsProductVo findShipSleepsMessage(Long id) {
        PmsProductVo pmsProductVo = new PmsProductVo();
        //查询商品的详细信息
        PmsProduct pmsProduct = pmsProductMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(pmsProduct, pmsProductVo);
        //查询商铺的信息
        UmsShop umsShop = umsShopMapper.selectByPrimaryKey(pmsProduct.getShopId());
        pmsProductVo.setShopId(umsShop.getId());
        pmsProductVo.setShopName(umsShop.getName());
        pmsProductVo.setLogo(umsShop.getLogo());
        //查询sku的数据信息
        List<PmsSkuStock> pmsProductSkuVos = pmsProductSkuMapper.selectSkuMessageByProductId(id);
        pmsProductVo.setProductSkuList(pmsProductSkuVos);
        //查询评价的信息(最新五条)
        List<PmsComment> comments = pmsCommentMapper.selectCommentMessageByProductId(id);
        pmsProductVo.setComments(comments);
        return pmsProductVo;
    }

    @Override
    public PageInfo findProductByShopId(SortDto sortDto, Long shopId) {
        PageHelper.startPage(sortDto.getPageNum(), sortDto.getPageSize());
        List<PmsProductListVo> shipSleepsProducts = pmsProductMapper.selectProductByShopId(sortDto, shopId);
        return new PageInfo(shipSleepsProducts);
    }
}
