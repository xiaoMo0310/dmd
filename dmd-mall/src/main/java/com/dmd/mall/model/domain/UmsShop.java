package com.dmd.mall.model.domain;

import com.dmd.core.mybatis.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * <p>
 * 店铺表
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "ums_shop")
@Alias(value = "UmsShop")
@ApiModel
public class UmsShop extends BaseEntity {

private static final long serialVersionUID = 1L;


    @Column(name = "member_id")
    @ApiModelProperty("用户id")
    private Integer memberId;

    @ApiModelProperty("店铺名称")
    private String name;

    @ApiModelProperty("店铺logo")
    private String logo;

    @ApiModelProperty("店铺电话")
    private String telephone;

    @ApiModelProperty("店铺级别")
    private String level;

    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("开店时间")
    private Date createTime;

    @ApiModelProperty("店铺类型")
    private String type;

    @ApiModelProperty("店铺介绍")
    private String introduction;

    @ApiModelProperty("店铺地址")
    private String address;

    @ApiModelProperty("营业执照")
    private String qualification;


}
