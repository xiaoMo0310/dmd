package com.dmd.mall.config;

//import com.dmd.mall.model.UmsMember;
//import com.dmd.mall.model.domain.MemberDetails;
//import com.dmd.mall.service.UmsMemberService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * SpringSecurity的配置
 * Created by macro on 2018/8/3.
 */
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private UmsMemberService memberService;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();//开启basic认证登录后可以调用需要认证的接口
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService())
//                .passwordEncoder(passwordEncoder());
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        //获取登录用户信息
//        return new UserDetailsService() {
//            @Override
//            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//                UmsMember member = memberService.getByUsername(username);
//                if (member != null) {
//                    return new MemberDetails(member);
//                }
//                throw new UsernameNotFoundException("用户名或密码错误");
//            }
//        };
//    }
//}
