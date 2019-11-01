package com.dmd.admin;

import com.dmd.FileUploadUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 应用启动入口
 * @author macro
 * @date 2018/4/26
 */
@SpringBootApplication
public class MallAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallAdminApplication.class, args);
        System.out.println("后台启动成功");
    }

    @Bean
    public FileUploadUtil createFileUploadUtil(){
        return new FileUploadUtil();
    }
}
