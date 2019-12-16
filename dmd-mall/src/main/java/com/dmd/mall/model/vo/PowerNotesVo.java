package com.dmd.mall.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.util.Date;

/**
 * @author ChenYanbing
 * @title: PowerNotesVo
 * @projectName dmd-masters
 * @description:
 * @date 2019/11/1414:04
 */
@Data
public class PowerNotesVo {

    @Column(name = "product_name")
    @ApiModelProperty("商品名称")
    private String productName;

    @Column(name = "user_id")
    @ApiModelProperty("用户id")
    private Long userId;

    @Column(name = "start_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("产品图片(多个使用 , 隔开)")
    private String image;

    @ApiModelProperty("报名人数")
    private Integer num;


}
