package com.dmd.mall.model.domain;

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
 * 证书数据表
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "pms_certificate")
@Alias(value = "PmsCertificate")
@ApiModel
public class PmsCertificate extends BaseEntity {

private static final long serialVersionUID = 1L;


    @Column(name = "chinese_name")
    @ApiModelProperty("证书名称(中文)")
    private String chineseName;

    @Column(name = "english_name")
    @ApiModelProperty("证书名称(英文)")
    private String englishName;

    @Column(name = "english_shorthand")
    @ApiModelProperty("英文简写")
    private String englishShorthand;

    @Column(name = "certificate_level")
    @ApiModelProperty("证书等级")
    private String certificateLevel;

    @ApiModelProperty("简介")
    private String introduction;

    @ApiModelProperty("图片")
    private String pic;

    @ApiModelProperty("状态(1:启用 2:禁用)")
    private Integer status;

    @Column(name = "certificate_type")
    @ApiModelProperty("证书类型(1:水肺潜水 2:自由潜水)")
    private Integer certificateType;


}
