package com.dmd.mall.security.authorize;

import org.springframework.security.core.Authentication;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
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

    private AntPathMatcher antPathMatcher=new AntPathMatcher();

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {

        boolean hasPermission=false;
        String username=(String) authentication.getPrincipal();
        Set<String> urls=null;
        for (String s:urls) {
            if (antPathMatcher.match(s,request.getRequestURI())){
                hasPermission=true;
                break;
            }
        }
        return hasPermission;
    }
}
