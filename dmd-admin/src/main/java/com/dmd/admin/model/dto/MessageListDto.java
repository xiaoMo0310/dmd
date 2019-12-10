package com.dmd.admin.model.dto;

import com.dmd.base.dto.BaseQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/10/29 16:40
 * @Description 分页查询通知 dto类
 */
@Data
public class MessageListDto extends BaseQuery {

    @ApiModelProperty("用户类型")
    private String userType;

    @ApiModelProperty("发送时间")
    private Date sendTime;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("是否删除")
    private Integer isDelete;

    @ApiModelProperty("消息类型")
    private Integer messageType;
}
