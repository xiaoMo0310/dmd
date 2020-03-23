package com.dmd.mall.model.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.persistence.*;
import java.io.Serializable;

/**
 * <p>
 * 产品标签关系表
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-17
 */
@Data
@Table(name = "pms_product_tag")
@Alias(value = "PmsProductTag")
@ApiModel
public class PmsProductTag implements Serializable {

    private static final long serialVersionUID = 5728082853716025569L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "product_id")
    @ApiModelProperty("产品id")
    private Long productId;

    @Column(name = "tag_id")
    @ApiModelProperty("标签id")
    private Long tagId;


}
