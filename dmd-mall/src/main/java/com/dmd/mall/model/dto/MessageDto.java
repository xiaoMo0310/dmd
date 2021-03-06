package com.dmd.mall.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/10/29 9:29
 * @Description 通知消息 dto
 */
@Data
public class MessageDto implements Serializable {

    private static final long serialVersionUID = 7867532269208214133L;
    @ApiModelProperty(value = "标题", required = true)
    private String title;

    @ApiModelProperty(value = "内容", required = true)
    private String content;

    @ApiModelProperty(value = "跳转地址", required = true)
    private String jumpAddress;

}
