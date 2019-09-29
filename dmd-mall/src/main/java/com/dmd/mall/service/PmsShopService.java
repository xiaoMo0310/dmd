package com.dmd.mall.service;

import com.dmd.mall.model.PmsComment;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * <p>
 * 商品首页内容
 * </p>
 *
 * @author 王海成
 * @since 2019-09-29
 */
public interface PmsShopService {
    public Map<String,Object> getShopIndex(Map<String,Object> map);//获取首页内容
    public Map<String,Object> shopProductDetails(Map<String,Object> mapParam);//获取商品详情和评论
    public PageInfo<PmsComment> shopProductComment(int id, int page, int pageSize);//获取评论内容
}
