package com.dmd.core.annotation;

import com.dmd.core.enums.LogTypeEnum;

import java.lang.annotation.*;


/**操作日志管理
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/12/26 16:21
 * @Description
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
	/**
	 * 日志类型
	 * @return the log type enum
	 */
	LogTypeEnum logType() default LogTypeEnum.OPERATION_LOG;

	/**
	 * 是否保存请求的参数
	 *
	 * @return the boolean
	 */
	boolean isSaveRequestData() default false;

	/**
	 * 是否保存响应的结果
	 *
	 * @return the boolean
	 */
	boolean isSaveResponseData() default false;
}
