package com.dmd.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 应用启动入口
 * @author macro
 * @date 2018/4/26
 */
@SpringBootApplication
@MapperScan("com.dmd.mall.mapper")
public class MallAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallAdminApplication.class, args);
        System.out.println("后台启动成功");
    }
}
