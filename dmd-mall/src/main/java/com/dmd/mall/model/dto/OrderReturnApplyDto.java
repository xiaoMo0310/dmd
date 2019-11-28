package com.dmd.mall.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/11/22 16:24
 * @Description 订单申请退款 dto
 */
@Data
@ApiModel
public class OrderReturnApplyDto {

    @ApiModelProperty("订单编号")
    private String orderSn;

    @ApiModelProperty("原因")
    private String reason;

    @ApiModelProperty("说明")
    private String description;

    @ApiModelProperty("图片")
    private String proofPics;

    private List<String> picList = new ArrayList<String>(0);

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;
}
