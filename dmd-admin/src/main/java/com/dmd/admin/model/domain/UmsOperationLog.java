package com.dmd.admin.model.domain;

import java.util.Date;
import com.dmd.core.mybatis.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "ums_operation_log")
@Alias(value = "UmsOperationLog")
@ApiModel
public class UmsOperationLog extends BaseEntity {

private static final long serialVersionUID = 1L;


    @ApiModelProperty("操作机器的ip")
    private String ip;

    @Column(name = "login_user")
    @ApiModelProperty("操作人的用户名")
    private String loginUser;

    @ApiModelProperty("执行操作的url")
    private String url;

    @ApiModelProperty("所做操作")
    private String operation;

    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("")
    private Date createTime;

    @Transient
    private String startTime;

    @Transient
    private String endTime;


}
