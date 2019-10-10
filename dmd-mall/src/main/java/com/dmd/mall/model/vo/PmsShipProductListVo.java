package com.dmd.mall.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/10/10 14:25
 * @Description 船宿商品列表信息vo类
 */

@Data
public class PmsShipProductListVo implements Serializable {

    private String id;

    /**
     * 商品名称
     */
    private String namne;

    /**
     * 商品图片
     */
    private String pic;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 商品的地址
     */
    private String shipAddress;

}
