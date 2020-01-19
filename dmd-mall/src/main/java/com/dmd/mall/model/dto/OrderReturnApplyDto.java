package com.dmd.mall.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
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
public class OrderReturnApplyDto implements Serializable {

    private static final long serialVersionUID = -8013189198872143385L;

    @ApiModelProperty("退款申请id")
    private Long returnApplyId;

    @ApiModelProperty("订单编号")
    private String orderSn;

    @ApiModelProperty("用户申请退款原因")
    private String reason;

    @ApiModelProperty("用户申请退款说明")
    private String description;

    @ApiModelProperty("图片")
    private String proofPics;

    private List<String> picList = new ArrayList<String>(0);

    @ApiModelProperty("申请状态：0->待处理；1->退款(货)中；2->已完成；3->已拒绝")
    private Integer status;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;

    @ApiModelProperty("退款金额")
    private BigDecimal returnAmount;

    @ApiModelProperty("审核不通过原因")
    private String failureReason;
}
