package com.dmd.mall.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 积分好礼兑换表，兑换物品由后台管理员编辑。
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-13
 */
@Data
@Table(name = "dmd_integral_gift")
@Alias(value = "DmdIntegralGift")
@ApiModel
public class DmdIntegralGift implements Serializable {

    private static final long serialVersionUID = -810251235558254749L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty("商品的名称")
    private String name;

    @ApiModelProperty("商品展示的图片")
    private String picture;

    @ApiModelProperty("兑换物品所需要的积分")
    private Integer integral;

    @ApiModelProperty("商品的介绍，长图展示")
    private String introduce;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("发布时间")
    private Date createtime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("修改时间")
    private Date updatetime;

    @ApiModelProperty("状态0=上架 1=下架")
    private Integer status;


}
