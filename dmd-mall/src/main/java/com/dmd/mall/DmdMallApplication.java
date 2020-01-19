package com.dmd.mall;


import com.dmd.FileUploadUtil;
import com.dmd.IdWorker;
import com.dmd.sms.SendUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableScheduling
@EnableWebMvc
@EnableSwagger2
@EnableTransactionManagement
@EnableConfigurationProperties
@EnableCaching
@MapperScan("com.dmd.mall.mapper")
public class DmdMallApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return super.configure(builder);
    }

    public static void main(String[] args) {
        SpringApplication.run(DmdMallApplication.class, args);
        System.out.println(
            "            █████▒█    ██  ▄████▄   ██ ▄█▀       ██████╗ ██╗   ██╗ ██████╗\n" +
            "          ▓██   ▒ ██  ▓██▒▒██▀ ▀█   ██▄█▒        ██╔══██╗██║   ██║██╔════╝\n" +
            "          ▒████ ░▓██  ▒██░▒▓█    ▄ ▓███▄░        ██████╔╝██║   ██║██║  ███╗\n" +
            "          ░▓█▒  ░▓▓█  ░██░▒▓▓▄ ▄██▒▓██ █▄        ██╔══██╗██║   ██║██║   ██║\n" +
            "          ░▒█░   ▒▒█████▓ ▒ ▓███▀ ░▒██▒ █▄       ██████╔╝╚██████╔╝╚██████╔╝\n" +
            "           ▒ ░   ░▒▓▒ ▒ ▒ ░ ░▒ ▒  ░▒ ▒▒ ▓▒       ╚═════╝  ╚═════╝  ╚═════╝\n" +
            "           ░     ░░▒░ ░ ░   ░  ▒   ░ ░▒ ▒░\n" +
            "           ░ ░    ░░░ ░ ░ ░        ░ ░░ ░" + "               启 动 成 功\n" +
            "                    ░     ░ ░      ░  ░"
         );
    }


    @Bean
    public FileUploadUtil createFileUploadUtil(){
        return new FileUploadUtil();
    }
    @Bean
    public IdWorker createIdWorker(){
        return new IdWorker();
    }
    @Bean
    public SendUtil createSmsUtil(){
        return new SendUtil();
    }
}
