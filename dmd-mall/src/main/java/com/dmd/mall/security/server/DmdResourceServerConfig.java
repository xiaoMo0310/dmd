package com.dmd.mall.security.server;

import com.dmd.mall.component.*;
import com.dmd.mall.security.authorize.AuthorizeConfigManager;
import com.dmd.mall.security.redis.ValidateCodeRepository;
import com.dmd.mall.security.sms.SmsCodeFilter;
import com.dmd.mall.security.sms.SmsCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

@Configuration
@EnableResourceServer
public class DmdResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    private GoAuthenticationSuccessHandler goAuthenticationSuccessHandler;
    @Autowired
    private SmsCodeSecurityConfig smsCodeSecurityConfig;
    @Autowired
    private ValidateCodeRepository validateCodeRepository;
    @Autowired
    private SpringSocialConfigurer springSocialConfigurer;
    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    public void configure(HttpSecurity http) throws Exception {
        SmsCodeFilter smsCodeFilter=new SmsCodeFilter();
        smsCodeFilter.setValidateCodeRepository(validateCodeRepository);
        http.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginProcessingUrl("/sso/login")
                .successHandler(goAuthenticationSuccessHandler)
                .failureHandler(new GoAuthenticationFailureHandler())
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new GoAccessDeniedHandler())
                .authenticationEntryPoint(new GoAuthenticationEntryPoint())
                .and()
                .logout()
                .logoutUrl("/sso/logout")
                .logoutSuccessHandler(new GoLogoutSuccessHandler())
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
                .csrf()
                .disable()
                .apply(smsCodeSecurityConfig)
                .and()
                .apply(springSocialConfigurer);//开启basic认证登录后可以调用需要认证的接口
        authorizeConfigManager.config(http.authorizeRequests());
    }
}
