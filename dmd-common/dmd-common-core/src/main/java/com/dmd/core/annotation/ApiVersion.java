package com.dmd.core.annotation;


import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;
/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2020/1/13 10:45
 * @Description 版本控制注解
 */

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface ApiVersion {

    /**
     * version
     *
     * @return
     */
    int value() default 1;
}

