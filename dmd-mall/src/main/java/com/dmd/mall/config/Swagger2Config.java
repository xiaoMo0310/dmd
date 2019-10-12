package com.dmd.mall.config;

import com.dmd.config.properties.DmdProperties;
import com.dmd.config.properties.SwaggerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Swagger2API文档的配置
 *
 * @author Yang
 * @date 2018/4/26
 */
@Configuration
public class Swagger2Config {

    @Bean
    public Docket createUserApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("用户API")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dmd.mall.web.ums"))
                .paths(PathSelectors.any())
                .build()
                //配置鉴权信息
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
//				.globalOperationParameters(pars)
                .enable(true);
    }

    @Bean
    public Docket createMallApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("商城API")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dmd.mall.web.oms"))
                .paths(PathSelectors.any())
                .build()
                //配置鉴权信息
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                .enable(true);
    }

    @Bean
    public Docket createPlatAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("论坛API")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dmd.mall.web.cms"))
                .paths(PathSelectors.any())
                .build()
                //配置鉴权信息
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                .enable(true);
    }

    @Bean
    public Docket createOtherAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("全部API")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dmd.mall.web"))
                .paths(PathSelectors.any())
                .build()
                //配置鉴权信息
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                .enable(true);
    }

    private ApiInfo apiInfo() {
        SwaggerProperties swagger = new DmdProperties().getSwagger();
        return new ApiInfoBuilder()
                .title(swagger.getTitle())
                .description(swagger.getDescription())
                .version(swagger.getVersion())
                .license(swagger.getLicense())
                .licenseUrl(swagger.getLicenseUrl())
                .contact(new Contact(swagger.getContactName(), swagger.getContactUrl(), swagger.getContactEmail()))
                .build();
    }


    /**
     * 认证信息配置
     *
     * @return
     */
    private List<ApiKey> securitySchemes() {
        return new ArrayList(Collections.singleton(new ApiKey("Authorization", "Authorization", "header")));
    }

    private List<SecurityContext> securityContexts() {
        return new ArrayList(
                Collections.singleton(SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("^(?!sso).*$"))
                        .build())
        );
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return new ArrayList(Collections.singleton(new SecurityReference("Authorization", authorizationScopes)));
    }
}
