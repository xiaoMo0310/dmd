package com.dmd.mall.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/12/16 13:20
 * @Description 教练信息 dto
 */
@Data
@ApiModel
public class UmsShopReplyDto implements Serializable{

    private static final long serialVersionUID = -794366959881313883L;

    @ApiModelProperty("回复消息id")
    private Long id;

    @ApiModelProperty("问题名称")
    private String questionName;

    @ApiModelProperty("问题回答")
    private String questionAnswering;

    @ApiModelProperty("是否删除(1:删除 2:未删除)")
    private Integer isDelete;
}
