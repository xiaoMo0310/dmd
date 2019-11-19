package com.dmd.mall.model.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.persistence.*;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-19
 */
@Data
@Table(name = "dmd_integral_gift_spe")
@Alias(value = "DmdIntegralGiftSpe")
@ApiModel
public class DmdIntegralGiftSpe implements Serializable {

    private static final long serialVersionUID = -4268165355122373170L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty("积分好礼id")
    @Column(name = "giftId")
    private Long giftId;

    @ApiModelProperty("库存")
    @Column(name = "specStock")
    private Integer specStock;

    @ApiModelProperty("尺码")
    private String size;

    @ApiModelProperty("颜色")
    private String color;


}
