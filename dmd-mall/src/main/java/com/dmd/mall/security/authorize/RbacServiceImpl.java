package com.dmd.mall.security.authorize;

import org.springframework.security.core.Authentication;
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
@Component
public class RbacServiceImpl implements RbacService{
    private AntPathMatcher antPathMatcher=new AntPathMatcher();
    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal=authentication.getPrincipal();
        boolean hasPermission=false;
        if (principal instanceof UserDetails){
            String username=((UserDetails)principal).getUsername();
            Set<String> urls=new HashSet<>();
            for (String s:urls) {
                if (antPathMatcher.match(s,request.getRequestURI())){
                    hasPermission=true;
                    break;
                }
            }
        }
        return hasPermission;
    }
}
