package com.dmd.admin.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/10/29 9:29
 * @Description 通知消息 dto
 */
@Data
public class MessageDto {

    @ApiModelProperty(value = "标题", required = true)
    private String title;

    @ApiModelProperty(value = "内容", required = true)
    private String content;

}
