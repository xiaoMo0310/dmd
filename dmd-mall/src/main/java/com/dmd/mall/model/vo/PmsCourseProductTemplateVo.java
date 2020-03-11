package com.dmd.mall.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class PmsCourseProductTemplateVo {

    private Long id;

    @ApiModelProperty("用户id(0:平台)")
    private Long userId;

    @ApiModelProperty("商铺id(0:平台)")
    private Long shopId;

    @ApiModelProperty("产品名称")
    private String productName;

    @ApiModelProperty("潜水地址id")
    private Long addressId;

    @ApiModelProperty("潜水地址")
    private String playAddress;

    @ApiModelProperty("潜水时间(单位:天)")
    private String divingTime;

    @ApiModelProperty("关联产品(格式[{\"parentId\":22,id\":33,\"text\":\"眼镜\",\"price\":\"200\"}])")
    private String relatedProduct;

    private List<Map> relatedProductList = new ArrayList<Map>( 0 );

    @ApiModelProperty("产品价格")
    private BigDecimal productPrice;

    @ApiModelProperty("其他装备价格")
    private BigDecimal equipmentPrice;

    @ApiModelProperty("产品总的价格")
    private BigDecimal totalPrice;

    @ApiModelProperty("潜水点描述")
    private String divingDescription;

}
