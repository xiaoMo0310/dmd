package com.dmd.mall.security.filter;

import com.dmd.mall.security.usesdetailsservice.MyUserDetailsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginFilter extends OncePerRequestFilter{

    @Autowired
    private MyUserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (StringUtils.equals("/oauth/token",httpServletRequest.getRequestURI())&&StringUtils.containsIgnoreCase(httpServletRequest.getMethod(),"post")){
            String loginType=httpServletRequest.getParameter("loginType");
            userDetailsService.setLoginType(loginType);
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
