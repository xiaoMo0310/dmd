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
 * 会员登录记录
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-12-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "ums_member_login_log")
@Alias(value = "UmsMemberLoginLog")
@ApiModel
public class UmsMemberLoginLog extends BaseEntity {

private static final long serialVersionUID = 1L;

    @Column(name = "member_id")
    @ApiModelProperty("")
    private Long memberId;

    @Column(name = "user_type")
    @ApiModelProperty("用户类型(普通用户:member 教练:coach)")
    private String userType;

    @Column(name = "login_ip")
    @ApiModelProperty("登录ip")
    private String loginIp;

    @Column(name = "login_location")
    @ApiModelProperty("登录地址")
    private String loginLocation;

    @ApiModelProperty("操作系统")
    private String os;

    @ApiModelProperty("浏览器类型")
    private String browser;

    @Column(name = "access_token")
    @ApiModelProperty("访问token")
    private String accessToken;

    @Column(name = "refresh_token")
    @ApiModelProperty("刷新token")
    private String refreshToken;

    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("创建时间")
    private Date createTime;


}
