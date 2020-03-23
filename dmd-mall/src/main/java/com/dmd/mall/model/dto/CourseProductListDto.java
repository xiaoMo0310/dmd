package com.dmd.mall.model.dto;

import com.dmd.base.dto.BaseQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CourseProductListDto extends BaseQuery {

    @ApiModelProperty("产品类型(1:学证 2:潜水)")
    private Integer productType;
}
