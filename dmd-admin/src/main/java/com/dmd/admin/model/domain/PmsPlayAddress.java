package com.dmd.admin.model.domain;

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
 * 潜水学习地址表
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-12-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "pms_play_address")
@Alias(value = "PmsPlayAddress")
@ApiModel
public class PmsPlayAddress extends BaseEntity {

private static final long serialVersionUID = 1L;


    @ApiModelProperty("地址")
    private String address;

    @Column(name = "address_introduction")
    @ApiModelProperty("地址简介")
    private String addressIntroduction;

    @ApiModelProperty("图集")
    private String atlas;

    @Column(name = "is_default")
    @ApiModelProperty("是否默认(0:非默认 1:默认)")
    private Integer isDefault;

    @ApiModelProperty("状态(1:启用 2:禁用)")
    private Integer status;



}
