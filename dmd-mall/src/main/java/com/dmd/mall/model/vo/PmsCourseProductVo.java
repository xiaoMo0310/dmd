package com.dmd.mall.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/11/18 13:53
 * @Description
 */
@Data
public class PmsCourseProductVo implements Serializable {

    private static final long serialVersionUID = -3408396945904034306L;
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
     * 关联产品(格式[{"parentId":22,id":33,"text":"眼镜","price":"200"}])
     */
    private String relatedProduct;

    /**
     * 产品总的价格
     */
    private BigDecimal equipmentPrice;

    /**
     * 产品总的价格
     */
    private BigDecimal totalPrice;

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
     * 产品介绍
     */
    private String productDescription;

    /**
     * 产品图片(多个使用 , 隔开)
     */
    private String image;

    @ApiModelProperty("销量")
    private Integer sales;

    private Integer numberIsLimit;

    private Integer numberLimit;

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

    private Integer status;
}
