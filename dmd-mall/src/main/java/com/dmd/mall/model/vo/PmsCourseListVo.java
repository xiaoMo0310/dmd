package com.dmd.mall.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/9/27 10:48
 * @Description 课程商品列表 vo
 */
@Data
public class PmsCourseListVo implements Serializable {

    private static final long serialVersionUID = 2831200859177969124L;
    /**
     * 产品id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 产品图片(多个使用 , 隔开)
     */
    private String image;

    /**
     * 产品的价格
     */
    private BigDecimal price;

    /**
     * 其它产品的价格
     */
    private BigDecimal equipmentPrice;

    /**
     * 产品总的价格
     */
    private BigDecimal totalPrice;


    /**
     * 可用积分
     */
    private Integer availableIntegral;

    /**
     *  产品状态状态
     */
    private Integer status;

    /**
     * 是否是组团产品(0:否 1:是)
     */
    private Integer isGroup;

    /**
     * 销量
     */
    private Integer sales;

    /**
     * 标签
     */
    private List<PmsTagVo> tagList = new ArrayList<>( 0 );
}
