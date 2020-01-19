package com.dmd.mall.test;

import com.dmd.core.utils.ThreadLocalUtil;

import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author 王海成
 * @since
 */
public class Test1 {
    private ThreadLocal<Map<String,String>> name=ThreadLocalUtil.threadLocal;

    public String getName() {
        return name.get().get("name");
    }
}
