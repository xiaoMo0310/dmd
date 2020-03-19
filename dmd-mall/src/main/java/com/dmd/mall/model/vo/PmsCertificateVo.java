package com.dmd.mall.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/11/7 13:22
 * @Description 证书信息vo类
 */
@Data
public class PmsCertificateVo implements Serializable {

    private static final long serialVersionUID = -7591416725045592185L;
    /**
     * 主键id
     */
    private Long id;

    /**
     * 英文名称
     */
    private String englishName;

    /**
     * 英文简写
     */
    private String englishShorthand;

    /**
     * 等级
     */
    private String certificateLevel;

    /**
     * 图片
     */
    private String pic;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 证书下商品数量
     */
    private Long productNum;

    /**
     * 是否是组团产品(0:否 1:是)
     */
    private Integer isGroup;
}
