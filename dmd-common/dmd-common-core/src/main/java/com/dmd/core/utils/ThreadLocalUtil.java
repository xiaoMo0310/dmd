package com.dmd.core.utils;

import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author 王海成
 * @since
 */
public class ThreadLocalUtil {
    public static ThreadLocal<Map<String,String>> threadLocal=new ThreadLocal<>();
}
