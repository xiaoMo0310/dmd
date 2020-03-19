package com.dmd.mall.model.domain;

import com.dmd.core.mybatis.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * <p>
 * 教练店铺店铺表
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-02-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "ums_coach_shop")
@Alias(value = "UmsCoachShop")
@ApiModel
public class UmsCoachShop extends BaseEntity {

    private static final long serialVersionUID = -6281586987289004925L;
    @Column(name = "coach_id")
    @ApiModelProperty("教练id")
    private Long coachId;

    @ApiModelProperty("店铺名称")
    private String name;

    @ApiModelProperty("店铺logo")
    private String logo;

    @ApiModelProperty("店铺电话")
    private String telephone;

    @ApiModelProperty("店铺级别")
    private String level;

    @ApiModelProperty("店铺介绍")
    private String introduction;

    @ApiModelProperty("店铺公告")
    private String announcement;

    @ApiModelProperty("营业执照")
    private String qualification;

    @Column(name = "is_auto_reply")
    @ApiModelProperty("是否开启自动回复(1:开启 2:未开启)")
    private Integer isAutoReply;

    @Column(name = "reply_id")
    @ApiModelProperty("自动回复ID")
    private Long replyId;

    @ApiModelProperty("状态(1:正常 2:关闭)")
    private Integer status;


}
