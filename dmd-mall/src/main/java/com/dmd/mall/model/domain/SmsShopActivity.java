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
 * 活动表
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "sms_shop_activity")
@Alias(value = "SmsShopActivity")
@ApiModel
public class SmsShopActivity extends BaseEntity {

private static final long serialVersionUID = 1L;


    @ApiModelProperty("活动标题")
    private String title;

    @Column(name = "activity_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("活动时间")
    private Date activityTime;

    @Column(name = "activity_image")
    @ApiModelProperty("活动图片")
    private String activityImage;

    @Column(name = "activity_detail")
    @ApiModelProperty("活动详情")
    private String activityDetail;

    @Column(name = "show_status")
    @ApiModelProperty("是否显示(0:不显示 1:显示)")
    private Integer showStatus;

    @ApiModelProperty("排序")
    private Integer sort;


}
