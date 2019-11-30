package com.dmd.mall.model.domain;

import com.dmd.core.mybatis.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * <p>
 * 首页轮播广告表
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "sms_home_advertise")
@Alias(value = "SmsHomeAdvertise")
@ApiModel
public class SmsHomeAdvertise extends BaseEntity {

private static final long serialVersionUID = 1L;

    @ApiModelProperty("")
    private String name;

    @ApiModelProperty("轮播位置：0->PC首页轮播；1->app首页轮播")
    private Integer type;
    @ApiModelProperty("")
    private String pic;
    @Column(name = "start_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("")
    private Date startTime;
    @Column(name = "end_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("")
    private Date endTime;

    @ApiModelProperty("上下线状态：0->下线；1->上线")
    private Integer status;

    @Column(name = "click_count")
    @ApiModelProperty("点击数")
    private Integer clickCount;

    @Column(name = "order_count")
    @ApiModelProperty("下单数")
    private Integer orderCount;

    @ApiModelProperty("链接地址")
    private String url;

    @ApiModelProperty("备注")
    private String note;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty(value = "链接类型(1-学证商品 2-话题 3-页面 4-潜水商品)")
    @Column(name = "link_type")
    private Integer linkType;

}
