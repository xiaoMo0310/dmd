package com.dmd.admin.model.dto;

import com.dmd.base.dto.BaseQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PmsCourseProductTemplateDto extends BaseQuery {
    @ApiModelProperty("产品名称")
    private Long productName;

    @ApiModelProperty(value = "潜水地点")
    private String playAddress;
}
