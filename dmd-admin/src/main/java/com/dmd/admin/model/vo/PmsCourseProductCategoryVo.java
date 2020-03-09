package com.dmd.admin.model.vo;

import com.dmd.admin.model.domain.PmsCourseProductCategory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2020/03/03 16:43
 * @Description 潜水学证分类 vo
 */
@Data
public class PmsCourseProductCategoryVo implements Serializable {

    private static final long serialVersionUID = -8275196507545545394L;
    private Long id;

    @ApiModelProperty("上机分类的编号：0表示一级分类")
    private Long parentId;
    @ApiModelProperty("")
    private String name;

    @ApiModelProperty("分类级别：0->1级；1->2级")
    private Integer level;

    @ApiModelProperty("是否显示在导航栏：0->不显示；1->显示")
    private Integer navStatus;

    @ApiModelProperty("显示状态：0->不显示；1->显示")
    private Integer showStatus;
    @ApiModelProperty("")
    private Integer sort;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("描述")
    private String description;


    private List<PmsCourseProductCategory> children = new ArrayList<>(0);
}
