package com.dmd.mall.model.vo;


import com.dmd.mall.model.domain.PmsComment;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/10/11 9:16
 * @Description 商品详情vo类
 */

@Data
public class PmsProductVo implements Serializable {

    private static final long serialVersionUID = -5479818911345372991L;
    /**
     * 商品id
     */
    private Long id;

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
     * 商品名称
     */
    private String name;

    /**
     * 商品图片
     */
    private String pic;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 商品详情描述
     */
    private String description;

    /**
     * 商品类型
     */
    private Integer productType;

    /**
     * 商品sku信息
     */
    private List productSkuList = new ArrayList<>(0);

    /**
     * 商品评论的信息
     */

    private List<PmsComment> comments = new ArrayList<>(0);
}
