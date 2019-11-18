package com.dmd.mall.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/11/18 13:53
 * @Description
 */
@Data
public class PmsCourseProductVo {

    /**
     * 商品id
     */
    private Long id;

    /**
     * 卖家id
     */
    private Long userId;

    /**
     * 地址id
     */
    private Long addressId;

    /**
     * 标题
     */
    private String title;

    /**
     * 产品的价格
     */
    private BigDecimal price;

    /**
     * 产品类型(1:学证 2:潜水)
     */
    private Integer productType;

    /**
     * 地点
     */
    private String location;

    /**
     * 游玩时长
     */
    private String lengthPlay;

    /**
     * 费用包含
     */
    private String costIncludes;

    /**
     * 费用不含
     */
    private String costNotIncludes;

    /**
     * 购买须知
     */
    private String purchaseNotes;

    /**
     * 产品介绍
     */
    private String productDescription;

    /**
     * 产品图片(多个使用 , 隔开)
     */
    private String image;

    /**
     * 内容安排(数据格式 [{"day01":"内容"}, {"day02":"内容"}])
     */
    private String contentArrangement;

    @ApiModelProperty("销量")
    private Integer sales;

    /**
     * 证书id
     */
    private Long certificateId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

}
