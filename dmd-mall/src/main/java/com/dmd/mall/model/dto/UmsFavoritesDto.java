package com.dmd.mall.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;

/**
 * @author ChenYanbing
 * @title: UmsFavoritesDto
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/11/1315:51
 */
@Data
public class UmsFavoritesDto {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("关注类型(1:普通用户 2:教练用户 3:普通商品 4:课程或潜水商品 5:商铺)")
    private Integer favoriteType;

    @ApiModelProperty("对象id")
    private Long targetId;

    @ApiModelProperty("关注的状态(1:已关注 2:取消关注)")
    private Integer status;
}
