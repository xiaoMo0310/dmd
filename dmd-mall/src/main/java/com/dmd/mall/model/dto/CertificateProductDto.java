package com.dmd.mall.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/11/8 10:42
 * @Description 证书产品 dto
 */
@Data
public class CertificateProductDto {

    @ApiModelProperty("证书id")
    private Long certificateId;

    @ApiModelProperty("地址id")
    private Long addressId;

    @ApiModelProperty("卖家id")
    private Long userId;
}
