package com.dmd.mall.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/10/9 17:17
 * @Description 潜水学证商品订单 dto
 */
@Data
@ApiModel
public class PmsCourseOrderDto {

    @ApiModelProperty("商品id")
    private Long productId;

    @ApiModelProperty("是否使用积分")
    private Boolean isUserIntegration = false;

    @ApiModelProperty("下单使用的积分数")
    private Integer useIntegration = 0;

    @ApiModelProperty("备注信息")
    private String remark;

    @ApiModelProperty("是否开具发票")
    private Boolean isInvoice = false;

    @ApiModelProperty("发票id 开户发票传id")
    private Long invoiceId;
}
