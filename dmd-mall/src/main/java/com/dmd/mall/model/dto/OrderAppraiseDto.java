package com.dmd.mall.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/11/22 13:36
 * @Description 订单评价 dto
 */
@Data
public class OrderAppraiseDto implements Serializable {

    private static final long serialVersionUID = -5138784463405177024L;
    @ApiModelProperty("订单id")
    private Long orderId;

    @ApiModelProperty("商品id")
    private Long productId;

    @ApiModelProperty("评论内容")
    private String info;

    @ApiModelProperty("级别 -1差评 0中评 1好评")
    private String level;

    @ApiModelProperty("描述相符 1-5")
    private Integer descStar;

    @ApiModelProperty("物流服务 1-5")
    private Integer logisticsStar;

    @ApiModelProperty("服务态度 1-5")
    private Integer attitudeStar;

    @ApiModelProperty("评价图片")
    private String pic;
}
