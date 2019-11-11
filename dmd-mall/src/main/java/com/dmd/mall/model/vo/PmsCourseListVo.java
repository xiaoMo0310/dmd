package com.dmd.mall.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

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
     * 产品名称
     */
    private String productName;

    /**
     * 位置
     */
    private String location;

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
     * 教练名称
     */
    private String coachName;

    /**
     * 教练头像
     */
    private String coachIcon;

    /**
     * 教练id
     */
    private Long coachId;

    /**
     * 教练等级
     */
    private String coachGrade;
}
