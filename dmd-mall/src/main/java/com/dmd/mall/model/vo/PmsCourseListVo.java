package com.dmd.mall.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    /**
     * 可用积分
     */
    private Integer availableIntegral;
}
