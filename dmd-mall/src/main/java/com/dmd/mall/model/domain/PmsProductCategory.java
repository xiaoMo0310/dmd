package com.dmd.mall.model.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;
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
 * @since 2019-10-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "pms_product_category")
@Alias(value = "PmsProductCategory")
@ApiModel
public class PmsProductCategory extends BaseEntity {

private static final long serialVersionUID = 1L;


    @Column(name = "parent_id")
    @ApiModelProperty("上机分类的编号：0表示一级分类")
    private Long parentId;
    @ApiModelProperty("")
    private String name;

    @ApiModelProperty("分类级别：0->1级；1->2级")
    private Integer level;
    @Column(name = "product_count")
    @ApiModelProperty("")
    private Integer productCount;
    @Column(name = "product_unit")
    @ApiModelProperty("")
    private String productUnit;

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
    @ApiModelProperty("")
    private String keywords;

    @ApiModelProperty("描述")
    private String description;


}
