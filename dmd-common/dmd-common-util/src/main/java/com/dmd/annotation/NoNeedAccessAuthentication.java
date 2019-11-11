package com.dmd.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/11/4 19:03
 * @Description 不需要认证注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoNeedAccessAuthentication {

}
