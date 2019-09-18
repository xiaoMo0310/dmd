 package com.dmd.mall;

 import org.mybatis.spring.annotation.MapperScan;
 import org.springframework.boot.SpringApplication;
 import org.springframework.boot.autoconfigure.SpringBootApplication;
 import org.springframework.scheduling.annotation.EnableScheduling;
 import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
 import org.springframework.transaction.annotation.EnableTransactionManagement;
 import springfox.documentation.swagger2.annotations.EnableSwagger2;

 @SpringBootApplication
 @EnableScheduling
 @EnableWebSecurity
 @EnableSwagger2
 @EnableTransactionManagement
 @MapperScan("com.dmd.mall.mapper")
public class DmdMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(DmdMallApplication.class, args);
        System.out.println("商城服务启动成功");
    }

}
