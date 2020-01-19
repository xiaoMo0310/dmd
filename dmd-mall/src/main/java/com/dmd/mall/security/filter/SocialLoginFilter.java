package com.dmd.mall.security.filter;

import com.dmd.core.utils.ThreadLocalUtil;
import com.dmd.mall.security.social.SocialConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author 王海成
 * @since
 */
@Component
public class SocialLoginFilter extends OncePerRequestFilter {
    private String[] urls={"/oauth/token","/sso/mobile","/sso/login","/auth/weixin"};
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String loginType=request.getParameter("loginType");
        Map<String,String> map=new HashMap<>();
        if (contain(request.getRequestURI())&& org.apache.commons.lang3.StringUtils.containsIgnoreCase(request.getMethod(),"post")){
            if (loginType!=null){
                if (loginType.equals("member")){
                    map.put("prefix","ums_m_");
                }else {
                    map.put("prefix","ums_c_");
                }
                map.put("loginType",loginType);
                ThreadLocalUtil.threadLocal.set(map);
            }
        }


        chain.doFilter(request,response);
    }

    private boolean contain(String url){
        for (String s : urls) {
            if (url.equals(s)) {
                return true;
            }
        }
        return false;
    }
}
