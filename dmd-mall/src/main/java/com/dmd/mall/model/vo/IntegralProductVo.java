package com.dmd.mall.model.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/11/20 9:15
 * @Description 积分商品结算 vo
 */
@Data
public class IntegralProductVo {

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 商品图片
     */
    private String productPic;

    /**
     * 兑换商品积分数
     */
    private Integer productIntegral;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品规格
     */
    private List productSpec = new ArrayList<>(0);

    /**
     * 商品数量
     */
    private Integer productQuantity;

    /**
     * 使用总的积分数
     */
    private Integer totalIntegral;

    /**
     * 可用积分
     */
    private Integer availableIntegral;
}
