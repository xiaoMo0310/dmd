package com.dmd.mall.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 退货原因表
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-22
 */
@Data
@Table(name = "oms_order_return_reason")
@Alias(value = "OmsOrderReturnReason")
@ApiModel
public class OmsOrderReturnReason implements Serializable {

    private static final long serialVersionUID = -2703135827889162657L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty("退货类型")
    private String name;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("状态：0->不启用；1->启用")
    private Integer status;

    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("添加时间")
    private Date createTime;
}
