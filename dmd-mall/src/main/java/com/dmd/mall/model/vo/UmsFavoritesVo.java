package com.dmd.mall.model.vo;

import lombok.Data;

/**
 * @author ChenYanbing
 * @title: UmsFavoritesVo
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/11/1314:57
 */
@Data
public class UmsFavoritesVo {

    private String nickname;

    private String icon;

    private Long targetId;

    private Integer status;

}
