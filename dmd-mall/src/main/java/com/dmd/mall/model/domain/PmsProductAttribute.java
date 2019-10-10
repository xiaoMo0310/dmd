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
 * 商品属性参数表
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "pms_product_attribute")
@Alias(value = "PmsProductAttribute")
@ApiModel
public class PmsProductAttribute extends BaseEntity {

private static final long serialVersionUID = 1L;

    @ApiModelProperty("")
    private String name;

    @Column(name = "select_type")
    @ApiModelProperty("属性选择类型：0->唯一；1->单选；2->多选")
    private Integer selectType;

    @Column(name = "input_type")
    @ApiModelProperty("属性录入方式：0->手工录入；1->从列表中选取")
    private Integer inputType;

    @Column(name = "input_list")
    @ApiModelProperty("可选值列表，以逗号隔开")
    private String inputList;

    @ApiModelProperty("排序字段：最高的可以单独上传图片")
    private Integer sort;

    @Column(name = "filter_type")
    @ApiModelProperty("分类筛选样式：1->普通；1->颜色")
    private Integer filterType;

    @Column(name = "search_type")
    @ApiModelProperty("检索类型；0->不需要进行检索；1->关键字检索；2->范围检索")
    private Integer searchType;

    @Column(name = "related_status")
    @ApiModelProperty("相同属性产品是否关联；0->不关联；1->关联")
    private Integer relatedStatus;

    @Column(name = "hand_add_status")
    @ApiModelProperty("是否支持手动新增；0->不支持；1->支持")
    private Integer handAddStatus;

    @ApiModelProperty("属性的类型；0->规格；1->参数")
    private Integer type;


}
