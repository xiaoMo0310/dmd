package com.dmd.mall.model.domain;

import com.dmd.core.mybatis.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * <p>
 * 潜水产品模板表
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "pms_course_product_template")
@Alias(value = "PmsCourseProductTemplate")
@ApiModel
public class PmsCourseProductTemplate extends BaseEntity {

private static final long serialVersionUID = 1L;


    @Column(name = "user_id")
    @ApiModelProperty("用户id(0:平台)")
    private Long userId;

    @Column(name = "shop_id")
    @ApiModelProperty("商铺id(0:平台)")
    private Long shopId;

    @Column(name = "product_name")
    @ApiModelProperty("产品名称")
    private String productName;

    @Column(name = "address_id")
    @ApiModelProperty("潜水地址id")
    private Long addressId;

    @Column(name = "play_address")
    @ApiModelProperty("潜水地址")
    private String playAddress;

    @Column(name = "diving_time")
    @ApiModelProperty("潜水时间(单位:天)")
    private String divingTime;

    @Column(name = "related_product")
    @ApiModelProperty("关联产品(格式[{\"parentId\":22,id\":33,\"text\":\"眼镜\",\"price\":\"200\"}])")
    private String relatedProduct;

    @Column(name = "product_price")
    @ApiModelProperty("产品价格")
    private BigDecimal productPrice;

    @Column(name = "equipment_price")
    @ApiModelProperty("其他装备价格")
    private BigDecimal equipmentPrice;

    @Column(name = "total_price")
    @ApiModelProperty("产品总的价格")
    private BigDecimal totalPrice;

    @Column(name = "diving_description")
    @ApiModelProperty("潜水点描述")
    private String divingDescription;

    @Column(name = "show_status")
    @ApiModelProperty("是否显示(0:不显示 1:显示)")
    private Integer showStatus;

    @ApiModelProperty("排序")
    private Integer sort;


}
