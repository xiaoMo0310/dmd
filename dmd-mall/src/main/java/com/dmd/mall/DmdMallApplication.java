package com.dmd.mall;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableScheduling
@EnableSwagger2
@EnableTransactionManagement
@MapperScan("com.dmd.mall.mapper")
public class DmdMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(DmdMallApplication.class, args);
        System.out.println("商城服务启动成功");
    }
}
