package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface PmsShopProductMapper {
    public List<PmsProduct> getShopProduct(Map<String,Object> map);//获取商场首页的商品信息
    public List<SmsHomeAdvertise> getShopAdvertise(@Param("type") String type);//获取轮播图
    public PmsShopDetails shopProductDetails(@Param("id") Long id);//商品详情
    public List<PmsComment> shopComment(@Param("id") Long id);//商品详情
    public List<PmsProductAttribute> getPmsProductAttribute(@Param("productAttributeCategoryId") Long productAttributeCategoryId);
}
