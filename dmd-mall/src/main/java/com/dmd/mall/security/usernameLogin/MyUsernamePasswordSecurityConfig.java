package com.dmd.mall.security.usernameLogin;

import com.dmd.mall.component.GoAuthenticationFailureHandler;
import com.dmd.mall.component.GoAuthenticationSuccessHandler;
import com.dmd.mall.security.sms.SmsCodeAuthenticationFilter;
import com.dmd.mall.security.sms.SmsCodeAuthenticationProvider;
import com.dmd.mall.security.usesdetailsservice.MyUserDetailsService;
import com.dmd.mall.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author 王海成
 * @since
 */
@Component
public class MyUsernamePasswordSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
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
        org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter=new org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter();
        MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter=new MyUsernamePasswordAuthenticationFilter();
        myUsernamePasswordAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        MyDaoAuthenticationProvider daoAuthenticationProvider=new MyDaoAuthenticationProvider();
        myUsernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(goAuthenticationSuccessHandler);
        myUsernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(goAuthenticationFailureHandler);
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        http.authenticationProvider(daoAuthenticationProvider)
                .addFilterAfter(myUsernamePasswordAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);
    }
}
