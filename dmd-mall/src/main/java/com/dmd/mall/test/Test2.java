package com.dmd.mall.test;

import com.dmd.core.utils.ThreadLocalUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 *
 * </p>
 *
 * @author 王海成
 * @since
 */
public class Test2 implements Runnable{
    private static Test1 test1=new Test1();
    @Override
    public void run() {
        Map<String,String> map=new HashMap<>();
        map.put("name",Thread.currentThread().getName());
        ThreadLocalUtil.threadLocal.set(map);
        if (!test1.getName().equals(Thread.currentThread().getName())){
            System.out.println("两个名字不一样");
        }

    }
}
