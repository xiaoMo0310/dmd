package com.dmd.admin.security.authorize;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * <p>
 *
 * </p>
 *
 * @author 王海成
 * @since
 */

public class AuthorizeConfigProvicerImpl implements AuthorizeConfigProvider {
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers(
                HttpMethod.OPTIONS
                ).permitAll()
                .antMatchers(
                    "/sso/*",
                    "/*.html",
                    "/favicon.ico",
                    "/**/*.html",
                    "/**/*.css",
                    "/**/*.js",
                    "/swagger-resources/**",
                    "/v2/api-docs/**"
                ).permitAll()
                .anyRequest().access("@rbacService.hasPermission(request, authentication)");
    }
}
