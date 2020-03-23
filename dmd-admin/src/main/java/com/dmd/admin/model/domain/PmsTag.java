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
 * 标签表
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "pms_tag")
@Alias(value = "PmsTag")
@ApiModel
public class PmsTag extends BaseEntity {

private static final long serialVersionUID = 1L;


    @Column(name = "tag_name")
    @ApiModelProperty("标签名称")
    private String tagName;

    @Column(name = "tag_type")
    @ApiModelProperty("标签类型(1:产品)")
    private Integer tagType;

    @Column(name = "show_status")
    @ApiModelProperty("显示状态(0:不显示 1:显示)")
    private Integer showStatus;

    @ApiModelProperty("排序")
    private Integer sort;


}
