package com.dmd.mall.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ChenYanbing
 * @title: UmsFavoritesVo
 * @projectName dmd-masters
 * @description:
 * @date 2019/11/1314:57
 */
@Data
public class UmsFavoritesVo implements Serializable {

    private static final long serialVersionUID = -6259262356335308458L;
    private Long userId;

    private String nickname;

    private String icon;

    private String topicName;

    private Long targetId;

    private Integer status;

    private Integer topicNum;

    private String topicPicture;

}
