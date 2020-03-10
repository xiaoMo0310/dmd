package com.dmd.mall.model.domain;

import com.dmd.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import javax.persistence.Column;
import javax.persistence.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 产品分类
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "pms_course_product_category")
@Alias(value = "PmsCourseProductCategory")
@ApiModel
public class PmsCourseProductCategory extends BaseEntity {

private static final long serialVersionUID = 1L;


    @Column(name = "parent_id")
    @ApiModelProperty("上机分类的编号：0表示一级分类")
    private Long parentId;
    @ApiModelProperty("")
    private String name;

    @ApiModelProperty("分类级别：0->1级；1->2级")
    private Integer level;

    @Column(name = "nav_status")
    @ApiModelProperty("是否显示在导航栏：0->不显示；1->显示")
    private Integer navStatus;

    @Column(name = "show_status")
    @ApiModelProperty("显示状态：0->不显示；1->显示")
    private Integer showStatus;
    @ApiModelProperty("")
    private Integer sort;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("描述")
    private String description;


}
