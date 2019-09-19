package com.dmd.mall.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


/**
 * <p>
 * 全局异常记录
 * </p>
 *
 * @author YangAnsheng123
 * @since 2019-09-18
 */
@Data
@Table(name = "exc_exception_log")
@Alias(value = "ExcExceptionLog")
@ApiModel
public class ExcExceptionLog{

private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "application_name")
    @ApiModelProperty("系统应用名")
    private String applicationName;

    @Column(name = "exception_simple_name")
    @ApiModelProperty("异常类型")
    private String exceptionSimpleName;

    @Column(name = "exception_message")
    @ApiModelProperty("异常信息(通过exception.getMessage()获取到的内容)")
    private String exceptionMessage;

    @Column(name = "exception_cause")
    @ApiModelProperty("异常原因(通过exception.getCause()获取到的内容)")
    private String exceptionCause;

    @Column(name = "exception_stack")
    @ApiModelProperty("异常堆栈信息")
    private String exceptionStack;

    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("创建时间")
    private Date createTime;


}
