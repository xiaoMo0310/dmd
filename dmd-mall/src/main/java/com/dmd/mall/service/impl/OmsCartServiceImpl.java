package com.dmd.mall.service.impl;

import com.dmd.base.dto.LoginAuthDto;
import com.dmd.base.enums.ErrorCodeEnum;
import com.dmd.core.support.BaseService;
import com.dmd.mall.exceptions.OmsBizException;
import com.dmd.mall.mapper.OmsCartMapper;
import com.dmd.mall.mapper.OmsShippingMapper;
import com.dmd.mall.mapper.PmsShopProductMapper;
import com.dmd.mall.model.domain.*;
import com.dmd.mall.model.vo.OrderCreateVo;
import com.dmd.mall.service.OmsCartService;
import com.dmd.mall.service.PmsProductService;
import com.dmd.mall.service.PmsSkuStockService;
import com.dmd.mall.service.UmsCoachShopService;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * <p>
 * 购物车表 服务实现类
 * </p>
 *
 * @author 王海成
 * @since 2019-10-12
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OmsCartServiceImpl extends BaseService<OmsCart> implements OmsCartService {
    @Autowired
    private OmsShippingMapper omsShippingMapper;
    @Autowired
    private OmsCartMapper omsCartMapper;
    @Autowired
    private PmsShopProductMapper pmsShopProductMapper;
    @Autowired
    private PmsProductService pmsProductService;
    @Autowired
    private PmsSkuStockService pmsSkuStockService;
    @Autowired
    private UmsCoachShopService umsShopService;

    @Override
    public List<OmsCart> findOmsCart(Long memberId) {
        return omsCartMapper.findOmsCart(memberId);
    }

    @Override
    public List<OmsCart> findOmsCartById(List<Long> ids) {
        return omsCartMapper.findOmsCartById(ids);
    }

    @Override
    public int addOmsCart(OmsCart omsCart) {
        return omsCartMapper.addOmsCart(omsCart);
    }

    @Override
    public int updateOmsCart(String quantity, String deleteStatus, List<Long> id, String updateTime) {
        return omsCartMapper.updateOmsCart(quantity,deleteStatus,id,updateTime);
    }

    @Override
    public Map<String, Object> beforeSubmitOrder(List<Long> ids, LoginAuthDto loginAuthDto, Long productId) {
        Map<String,Object> map=new TreeMap<>();
        List<OmsCart> omsCarts=findOmsCartById(ids);
        PmsShopDetails pmsShopDetails=pmsShopProductMapper.shopProductDetails(productId);//点击立即购买时需要的商品信息
        List<OmsShipping> omsShippings=omsShippingMapper.selectByUserId(loginAuthDto.getUserId(), loginAuthDto.getUserType());
        map.put("omsCarts",omsCarts);
        map.put("omsShippings",omsShippings);
        map.put("pmsShopDetails",pmsShopDetails);
        return map;
    }

    @Override
    public List<OmsOrderItem> getCartOrderItem(Long userId, List<OmsCart> cartList) {
        List<OmsOrderItem> orderItemList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(cartList)) {
            throw new OmsBizException(ErrorCodeEnum.OMS10031001, userId);
        }
        //校验购物车的数据,包括产品的状态和数量
        for (OmsCart cartItem : cartList) {
            OmsOrderItem orderItem = pmsProductService.createOrderItem(cartItem.getProductId(), cartItem.getProductSkuId(), cartItem.getQuantity());
            orderItemList.add(orderItem);
        }
        return orderItemList;
    }


    @Override
    public OrderCreateVo settlementById(Long[] cartIdArray) {
        //获取店铺集合，购物车集合
        Set<Long> shopIdSet = new HashSet<>();
        List<OmsCart> omcCartList = new ArrayList<>();
        for (int i = 0; i < cartIdArray.length; i++) {
            OmsCart omcCart = omsCartMapper.selectByPrimaryKey(cartIdArray[i]);
            omcCartList.add(omcCart);
            Long shopId = omcCart.getShopId();
            shopIdSet.add(shopId);
        }

        OrderCreateVo orderCreateVo = new OrderCreateVo();
        //已选商品总金额
        BigDecimal totalChoose = new BigDecimal("0.00");
        //运费总金额
        BigDecimal totalpostage = new BigDecimal("0.00");

        //店铺订单集合
        List<OrderCreateVo.OrderGroupByShop> orderGroupByShopList = new ArrayList<>();

        //根据店铺分单结算
        Iterator<Long> it = shopIdSet.iterator();
        while (it.hasNext()) {
            Long shopId = it.next();
            OrderCreateVo.OrderGroupByShop orderGroupByShop = new OrderCreateVo.OrderGroupByShop();
            orderGroupByShop.setShopId(shopId);
            UmsCoachShop umsShop = umsShopService.selectByKey(shopId);
            orderGroupByShop.setShopName(umsShop.getName());
            orderGroupByShop.setLogo(umsShop.getLogo());
            //店铺合计
            BigDecimal shopPayment = new BigDecimal("0.00");
            //运费
            BigDecimal postage = new BigDecimal("0.00");

            ArrayList<OrderCreateVo.OrderDetailGroupByShop> orderDetailGroupByShops = new ArrayList<>();

            //查找本店铺商品
            for (OmsCart omcCart : omcCartList) {
                if (shopId.equals(omcCart.getShopId())) {
                    //查询商品的数据
                    PmsProduct product = pmsProductService.selectByKey(omcCart.getProductId());
                    //查询商品的sku数据
                    PmsSkuStock skuStock = pmsSkuStockService.selectByKey(omcCart.getProductSkuId());
                    //单品小计
                    BigDecimal multiply = skuStock.getPrice().multiply(new BigDecimal(omcCart.getQuantity()));
                    shopPayment = shopPayment.add(multiply);

                    //店铺商品SKU
                    OrderCreateVo.OrderDetailGroupByShop orderDetailGroupByShop = new OrderCreateVo.OrderDetailGroupByShop();
                    //封装商铺的信息
                    orderDetailGroupByShop.setCartId(omcCart.getId());
                    orderDetailGroupByShop.setShopSkuId(skuStock.getId());
                    orderDetailGroupByShop.setProductId(product.getId());
                    orderDetailGroupByShop.setProductName(product.getName());
                    orderDetailGroupByShop.setProductImage(skuStock.getPic());
                    orderDetailGroupByShop.setProductAttribute(skuStock.getSpec());
                    orderDetailGroupByShop.setCurrentUnitPrice(skuStock.getPrice());
                    orderDetailGroupByShop.setQuantity(omcCart.getQuantity());
                    orderDetailGroupByShop.setTotalPrice(multiply);
                    orderDetailGroupByShops.add(orderDetailGroupByShop);
                }

            }
            orderGroupByShop.setShopPayment(shopPayment);
            orderGroupByShop.setPostage(postage);
            orderGroupByShop.setOrderDetailGroupByShop(orderDetailGroupByShops);

            orderGroupByShopList.add(orderGroupByShop);
            totalChoose = totalChoose.add(shopPayment);
            //todo 运费计算逻辑待开发
            totalpostage = totalpostage.add(postage);

        }
        orderCreateVo.setTotalChoose(totalChoose);
        orderCreateVo.setTotalPostage(totalpostage);
        orderCreateVo.setTotalPayment(totalChoose.add(totalpostage));
        orderCreateVo.setOrderGroupByShopList(orderGroupByShopList);

        return orderCreateVo;
    }

}