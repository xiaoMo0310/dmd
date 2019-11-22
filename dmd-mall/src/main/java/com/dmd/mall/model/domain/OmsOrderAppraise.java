package com.dmd.mall.model.domain;

import com.dmd.core.mybatis.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * <p>
 * 
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "oms_order_appraise")
@Alias(value = "OmsOrderAppraise")
@ApiModel
public class OmsOrderAppraise extends BaseEntity {

private static final long serialVersionUID = 1L;


    @Column(name = "order_id")
    @ApiModelProperty("订单id")
    private Long orderId;

    @Column(name = "product_id")
    @ApiModelProperty("商品id")
    private Long productId;

    @Column(name = "user_id")
    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("评论内容")
    private String info;

    @ApiModelProperty("级别 -1差评 0中评 1好评")
    private String level;

    @Column(name = "desc_star")
    @ApiModelProperty("描述相符 1-5")
    private Integer descStar;

    @Column(name = "logistics_star")
    @ApiModelProperty("物流服务 1-5")
    private Integer logisticsStar;

    @Column(name = "attitude_star")
    @ApiModelProperty("服务态度 1-5")
    private Integer attitudeStar;

    @ApiModelProperty("评价图片")
    private String pic;


}
