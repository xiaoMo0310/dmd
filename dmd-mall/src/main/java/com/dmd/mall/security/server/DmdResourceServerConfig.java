package com.dmd.mall.security.server;

import com.dmd.mall.component.*;
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
                .loginProcessingUrl("/sso/login")//登陆的url
                .successHandler(goAuthenticationSuccessHandler)//登陆成功处理器
                .failureHandler(new GoAuthenticationFailureHandler())//登陆失败处理器
                .and()
                .exceptionHandling()//添加自定义未登录或未授权的返回结果
                .accessDeniedHandler(new GoAccessDeniedHandler())
                .authenticationEntryPoint(new GoAuthenticationEntryPoint())
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS)//跨域请求会先进行一次options请求
                .permitAll()
                .antMatchers(
                        "/sso/*"//登录注册
                        ,"/*.html"
                        ,"/**/*.html"
                        ,"/home/test"
                        ,"/qqLogin/weixin"
                        ,"/swagger-resources/**"
                        ,"/v2/api-docs/**"
                        ,"/favicon.ico"
                        ,"/**/*.css"
                        ,"/**/*.js"
                )
                .permitAll()
                /*.antMatchers("/shop/getShop")
                .hasAnyAuthority("coach","customer")//拥有教练或者客户任意一个角色可以访问
                .antMatchers("/shop/shopProductDetails")//拥有教练角色能访问
                .hasAuthority("coach")*/
                .anyRequest()// 除上面外的所有请求全部需要鉴权认证
                .authenticated()
                .and()
                .logout()
                .logoutUrl("/sso/logout")
                .logoutSuccessHandler(new GoLogoutSuccessHandler())
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
                .csrf()
                .disable()
                .apply(smsCodeSecurityConfig)//验证码登陆配置
                .and()
                .apply(springSocialConfigurer);//第三方登陆
    }
}
