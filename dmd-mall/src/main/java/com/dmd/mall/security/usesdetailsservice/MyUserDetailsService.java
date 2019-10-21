package com.dmd.mall.security.usesdetailsservice;

import com.dmd.mall.model.domain.MemberDetails;
import com.dmd.mall.model.domain.UmsMember;
import com.dmd.mall.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService, SocialUserDetailsService {
    private String type="";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Autowired
    private UmsMemberService memberService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UmsMember member = memberService.getByUsername(username);
            if (member==null&&type.equals("smsCode")){
                member=memberService.register(username);
            }else if (member==null){
                throw new InternalAuthenticationServiceException("用户信息不存在，请先注册");
            }
            return new MemberDetails(member);
        }finally {
            type="";
        }

    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        UmsMember member = memberService.getByUsername(userId);
        return new MemberDetails(member);
    }
}
