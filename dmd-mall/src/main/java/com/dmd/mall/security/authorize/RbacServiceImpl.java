package com.dmd.mall.security.authorize;

import com.dmd.mall.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 *
 * </p>
 *
 * @author 王海成
 * @since
 */

public class RbacServiceImpl implements RbacService{
    @Autowired
    private UmsMemberService memberService;

    private AntPathMatcher antPathMatcher=new AntPathMatcher();

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {

        boolean hasPermission=false;
        String username=(String) authentication.getPrincipal();
        Set<String> urls=memberService.getPermission(username);
        for (String s:urls) {
            if (antPathMatcher.match(s,request.getRequestURI())){
                hasPermission=true;
                break;
            }
        }
        return hasPermission;
    }
}
