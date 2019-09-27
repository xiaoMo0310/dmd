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
 * 字典表 
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-09-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "pms_dict")
@Alias(value = "PmsDict")
@ApiModel
public class PmsDict extends BaseEntity {

private static final long serialVersionUID = 1L;


    @Column(name = "dict_key")
    @ApiModelProperty("属性key值")
    private String dictKey;

    @Column(name = "dict_value")
    @ApiModelProperty("属性value值")
    private String dictValue;

    @ApiModelProperty("父id")
    private Long pid;

    @Column(name = "dict_name")
    @ApiModelProperty("属性的名称")
    private String dictName;

    @ApiModelProperty("状态(0:禁用 1:可用)")
    private Integer status;


}
