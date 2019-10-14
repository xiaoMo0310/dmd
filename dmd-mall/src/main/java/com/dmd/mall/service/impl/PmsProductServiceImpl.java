package com.dmd.mall.service.impl;

import com.dmd.base.dto.BaseQuery;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.base.enums.ErrorCodeEnum;
import com.dmd.core.support.BaseService;
import com.dmd.mall.exceptions.PmsBizException;
import com.dmd.mall.mapper.*;
import com.dmd.mall.model.domain.*;
import com.dmd.mall.model.dto.SortDto;
import com.dmd.mall.model.vo.*;
import com.dmd.mall.service.PmsProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    private PmsProductMapper pmsProductMapper;
    @Autowired
    private PmsCommentMapper pmsCommentMapper;
    @Autowired
    private PmsProductSkuMapper pmsProductSkuMapper;
    @Autowired
    private UmsShopMapper umsShopMapper;
    @Autowired
    private PmsProductCategoryMapper productCategoryMapper;

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

    @Override
    public List<PageInfo> findCourseProduct(BaseQuery baseQuery, LoginAuthDto loginAuthDto) {
        //查询课程分类下的所有的分类
        PmsProductCategory pmsProductCategory = new PmsProductCategory();
        pmsProductCategory.setParentId(56L);
        List<PmsProductCategory> productCategories = productCategoryMapper.select(pmsProductCategory);
        if(CollectionUtils.isEmpty(productCategories)){
            throw new PmsBizException(ErrorCodeEnum.GL9999404, 56L);
        }
        return productCategories.stream().map(productCategory -> {
            PmsProduct pmsProduct = new PmsProduct();
            pmsProduct.setProductCategoryId(productCategory.getId());
            pmsProduct.setVerifyStatus(1);
            pmsProduct.setDeleteStatus(0);
            pmsProduct.setPublishStatus(1);
            PageHelper.startPage(baseQuery.getPageNum(), baseQuery.getPageSize());
            List<PmsProduct> courseProducts = pmsProductMapper.select(pmsProduct);
            List<PmsCourseListVo> courseProductVos = courseProducts.stream().map(courseProduct -> {
                PmsCourseListVo courseProductVo = new PmsCourseListVo();
                BeanUtils.copyProperties(courseProduct, courseProductVo);
                return courseProductVo;
            }).collect(Collectors.toList());
            return new PageInfo<>(courseProductVos);
        }).collect(Collectors.toList());
    }

    @Override
    public PmsCourseProductVo findCourseProductById(LoginAuthDto loginAuthDto, Long id) {
        PmsProduct pmsCourseProduct = pmsProductMapper.selectByPrimaryKey(id);
        PmsCourseProductVo courseProductVo = new PmsCourseProductVo();
        BeanUtils.copyProperties(pmsCourseProduct, courseProductVo);
        return courseProductVo;
    }


}
