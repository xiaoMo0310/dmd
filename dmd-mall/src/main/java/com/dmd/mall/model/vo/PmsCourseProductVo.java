package com.dmd.mall.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PmsCourseProductVo {

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

}
