package com.dmd.admin.model.vo;

import com.dmd.base.dto.BaseQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2020/1/9 11:06
 * @Description 话题vo类
 */
@Data
public class TopicVo extends BaseQuery {

    @ApiModelProperty(value = "话题id")
    private Integer id;

    @ApiModelProperty(value = "话题名称")
    private String topicName;

    @ApiModelProperty(value = "话题描述")
    private String topicDescribes;

    @ApiModelProperty(value = "动态数量")
    private Integer topicNum;

    @ApiModelProperty(value = "话题展示图片")
    private String topicPicture;

}
