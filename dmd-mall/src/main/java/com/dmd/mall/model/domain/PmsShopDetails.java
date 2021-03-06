package com.dmd.mall.model.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author 王海成
 * @since
 */
@Data
public class PmsShopDetails implements Serializable {
    private static final long serialVersionUID = 327619833891906195L;
    private Integer comment_count;//评论总数
    private Integer id;//商品id
    private String sale;//销量
    private String price;//商品市场价格
    private String shopId;//商品id
    private String detail;//商品详情页面链接（目前用长图代替）
    private String description;//商品详情描述
    private String stock;//库存
    private String albumPics;//画册图片
    private List<PmsProductAttribute> productAttributes;//商品规格内容
    private String name;//商品名称
    private Long productAttributeCategoryId;//属性分类对应的id
    private String telephone;//商铺电话
}
