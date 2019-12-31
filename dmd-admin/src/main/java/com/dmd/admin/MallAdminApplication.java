package com.dmd.admin;

import com.dmd.FileUploadUtil;
import com.dmd.sms.SendUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 应用启动入口
 * @author macro
 * @date 2018/4/26
 */
@EnableTransactionManagement
@SpringBootApplication
public class MallAdminApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(MallAdminApplication.class, args);
        System.out.println("后台启动成功");
    }

    @Bean
    public FileUploadUtil createFileUploadUtil(){
        return new FileUploadUtil();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return super.configure(builder);
    }

    @Bean
    public SendUtil createSmsUtil(){
        return new SendUtil();
    }
}
