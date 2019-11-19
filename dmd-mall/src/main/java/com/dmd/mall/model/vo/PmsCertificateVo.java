package com.dmd.mall.model.vo;

import lombok.Data;
/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/11/7 13:22
 * @Description 证书信息vo类
 */
@Data
public class PmsCertificateVo {

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
}
