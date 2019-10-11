package com.dmd.mall.model.dto;

import com.dmd.base.dto.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class SortDto extends BaseQuery {

    @ApiModelProperty("最低价格")
    private String minPrice;

    @ApiModelProperty("最高价格")
    private String maxPrice;

    @ApiModelProperty("发货(目的)地区")
    private String address;
}
