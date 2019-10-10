package com.dmd.mall.model.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/9/29 11:25
 * @Description 课程商品 vo
 */
@Data
public class PmsCourseProductVo {

    /**
     * 店铺id
     */
    private Long shopId;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 店铺电话
     */
    private String mobile;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 产品图片(多个使用 , 隔开)
     */
    private String image;

    /**
     * 产品的价格
     */
    private BigDecimal price;

    /**
     * 产品类型(1:入门 2:进阶 3:专业)
     */
    private String productType;

    /**
     * 产品类型名称
     */
    private String productTypeName;

    /**
     * 产品介绍
     */
    private String productDescription;

    /**
     * 课程内容安排
     */
    private String contentArrangement;

    /**
     * 购买须知
     */
    private String purchaseNotes;

    /**
     * 销量
     */
    private String sales;
}
