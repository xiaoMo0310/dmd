package com.dmd.mall.test;

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
public class Test3 {
    public static void main(String[] args) {
        ExecutorService service= Executors.newFixedThreadPool(20);
        for (int i=0;i<10000;i++){
            service.execute(new Test2());
        }
        service.shutdownNow();
    }
}
