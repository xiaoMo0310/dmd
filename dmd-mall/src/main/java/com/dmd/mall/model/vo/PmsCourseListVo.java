package com.dmd.mall.model.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/9/27 10:48
 * @Description 课程商品列表 vo
 */
@Data
public class PmsCourseListVo {

    /**
     * 课程产品id
     */
    private Long id;

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
    private Integer productType;

    /**
     * 产品类型名称
     */
    private String productTypeName;

}
