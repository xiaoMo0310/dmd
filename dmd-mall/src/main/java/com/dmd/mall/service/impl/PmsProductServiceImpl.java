package com.dmd.mall.service.impl;

import com.dmd.BigDecimalUtil;
import com.dmd.base.dto.BaseQuery;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.base.enums.ErrorCodeEnum;
import com.dmd.core.support.BaseService;
import com.dmd.mall.exceptions.OmsBizException;
import com.dmd.mall.exceptions.PmsBizException;
import com.dmd.mall.mapper.*;
import com.dmd.mall.model.domain.*;
import com.dmd.mall.model.dto.SortDto;
import com.dmd.mall.model.vo.PmsCourseListVo;
import com.dmd.mall.model.vo.PmsProductListVo;
import com.dmd.mall.model.vo.PmsProductVo;
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
    private PmsSkuStockMapper pmsProductSkuMapper;
    //@Autowired
    //private UmsShopMapper umsShopMapper;
    @Autowired
    private UmsCoachShopMapper umsShopMapper;
    @Autowired
    private PmsProductCategoryMapper productCategoryMapper;

    @Override
    public PageInfo findShipSleepsProduct(SortDto sortDto) {
        PageHelper.startPage(sortDto.getPageNum(), sortDto.getPageSize());
        List<PmsProductListVo> shipSleepsProducts = pmsProductMapper.selectShipSleepsProduct(sortDto, 2);
        return new PageInfo(shipSleepsProducts);
    }

    @Override
    public PmsProductVo findShipSleepsMessage(Long productId) {
        PmsProductVo pmsProductVo = new PmsProductVo();
        //查询商品的详细信息
        PmsProduct pmsProduct = pmsProductMapper.selectByPrimaryKey(productId);
        BeanUtils.copyProperties(pmsProduct, pmsProductVo);
        //查询商铺的信息
        //UmsShop umsShop = umsShopMapper.selectByPrimaryKey(pmsProduct.getShopId());
        //pmsProductVo.setShopId(umsShop.getId());
        //pmsProductVo.setShopName(umsShop.getName());
        //pmsProductVo.setLogo(umsShop.getLogo());
        UmsCoachShop umsShop = umsShopMapper.selectByPrimaryKey(pmsProduct.getShopId());
        pmsProductVo.setShopId(umsShop.getId());
        pmsProductVo.setShopName(umsShop.getName());
        pmsProductVo.setLogo(umsShop.getLogo());
        //查询sku的数据信息
        if(pmsProduct.getProductType() != 3){
            List<PmsSkuStock> pmsProductSkuVos = pmsProductSkuMapper.selectSkuMessageByProductId(productId);
            pmsProductVo.setProductSkuList(pmsProductSkuVos);
        }
        //查询评价的信息(最新五条)
        List<PmsComment> comments = pmsCommentMapper.selectCommentMessageByProductId(productId);
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
            pmsProduct.setProductType(3);
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
    public OmsOrderItem createOrderItem(Long productId, Long productSkuId, Integer quantity) {
        //查询商品的信息
        PmsProduct product = pmsProductMapper.selectByPrimaryKey(productId);
        if(product == null){
            throw new PmsBizException(ErrorCodeEnum.PMS10021004, productId);
        }
        //查询商品sku的信息
        PmsSkuStock pmsSkuStock = pmsProductSkuMapper.selectByPrimaryKey(productSkuId);
        if(pmsSkuStock == null){
            throw new PmsBizException(ErrorCodeEnum.PMS10021004, productSkuId);
        }
        if (product.getVerifyStatus() == 0) {
            logger.error("商品未通过审核不能销售, productId={}", product.getId());
            throw new OmsBizException(ErrorCodeEnum.PMS10021015, product.getId());
        }
        if(product.getDeleteStatus() == 1 || product.getPublishStatus() == 0 || pmsSkuStock.getStatus() != 1){
            logger.error("商品已下架或者删除, productId={}", product.getId());
            throw new OmsBizException(ErrorCodeEnum.PMS10021017, product.getId());
        }
        //校验库存
        //查询商品的库存
        if (quantity > (pmsSkuStock.getStock() - pmsSkuStock.getLockStock())) {
            logger.error("商品库存不足, productId={}", product.getId());
            throw new OmsBizException(ErrorCodeEnum.PMS10021016, product.getId());
        }
        OmsOrderItem orderDetail = new OmsOrderItem();
        //封装商品的信息
        orderDetail.setProductId(product.getId());
        orderDetail.setProductPic(product.getPic());
        orderDetail.setProductName(product.getName());
        orderDetail.setProductType(1);
        orderDetail.setProductBrand(product.getBrandName());
        orderDetail.setProductQuantity(quantity);
        orderDetail.setProductCategoryId(product.getProductCategoryId());
        orderDetail.setTotalPrice(BigDecimalUtil.mul(pmsSkuStock.getPrice().doubleValue(), quantity));
        //封装商品sku数据
        orderDetail.setProductSkuId(pmsSkuStock.getId());
        orderDetail.setProductAttr(pmsSkuStock.getSpec());
        orderDetail.setProductPrice(pmsSkuStock.getPrice());
        orderDetail.setProductSkuCode(pmsSkuStock.getSkuCode());
        return orderDetail;
    }

}
