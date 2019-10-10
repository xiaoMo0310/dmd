package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.PmsComment;
import com.dmd.mall.model.domain.PmsProduct;
import com.dmd.mall.model.domain.PmsShopDetails;
import com.dmd.mall.model.domain.SmsHomeAdvertise;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PmsShopProductMapper {
    public List<PmsProduct> getShopProduct(Map<String,Object> map);//获取商场首页的商品信息
    public List<SmsHomeAdvertise> getShopAdvertise(Map<String,Object> map);//获取商场首页轮播图
    public PmsShopDetails shopProductDetails(@Param("id") Integer id);//商品详情
    public List<PmsComment> shopComment(@Param("id") Integer id);//商品详情
}
