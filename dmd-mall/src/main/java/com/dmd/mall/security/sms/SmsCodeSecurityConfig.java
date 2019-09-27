package com.dmd.mall.security.sms;

import com.dmd.mall.component.GoAuthenticationFailureHandler;
import com.dmd.mall.component.GoAuthenticationSuccessHandler;
import com.dmd.mall.security.usesdetailsservice.MyUserDetailsService;
import com.dmd.mall.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class SmsCodeSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    private GoAuthenticationSuccessHandler goAuthenticationSuccessHandler;
    @Autowired
    private GoAuthenticationFailureHandler goAuthenticationFailureHandler;
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private UmsMemberService umsMemberService;
    @Override
    public void configure(HttpSecurity http) throws Exception {
        UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter=new UsernamePasswordAuthenticationFilter();
        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter=new SmsCodeAuthenticationFilter();
        smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(goAuthenticationSuccessHandler);
        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(goAuthenticationFailureHandler);
        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider=new SmsCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        smsCodeAuthenticationProvider.setUmsMemberService(umsMemberService);
        http.authenticationProvider(smsCodeAuthenticationProvider)
                .addFilterAfter(smsCodeAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);
    }
}
