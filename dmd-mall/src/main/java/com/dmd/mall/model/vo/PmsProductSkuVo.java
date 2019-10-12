package com.dmd.mall.model.vo;


import com.dmd.base.dto.BaseVo;
import lombok.Data;

import java.math.BigDecimal;
/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/10/11 11:03
 * @Description 商品sku数据vo类
 */
@Data
public class PmsProductSkuVo extends BaseVo {

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 商铺id
     */
    private Long shopId;

    /**
     * 商铺logo
     */
    private String logo;

    /**
     * 商铺名称
     */
    private String shopName;

    /**
     * 商品sku编号
     */
    private String skuCode;

    /**
     *商品的价格
     */
    private BigDecimal price;

    /**
     * 商品状态.1-在售 2-下架 3-删除
     */
    private Integer status;

    /**
     * 商品图片
     */
    private String pic;

    /**
     *库存
     */
    private Integer stock;

    /**
     * 规格信息
     */
    private String spec;

    /**
     * 是否是默认(0:非默认 1:默认)
     */
    private Integer isDefault;
}
