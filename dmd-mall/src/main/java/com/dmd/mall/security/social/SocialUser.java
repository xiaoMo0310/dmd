package com.dmd.mall.security.social;

import com.dmd.mall.model.UmsMember;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.social.security.SocialUserDetails;

import java.util.Collection;

public class SocialUser implements SocialUserDetails {

    private UmsMember umsMember;

    public SocialUser(UmsMember umsMember) {
        this.umsMember=umsMember;
    }

    @Override
    public String getUserId() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return umsMember.getPassword();
    }

    @Override
    public String getUsername() {
        return umsMember.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return umsMember.getStatus()==1;
    }
    public UmsMember getUmsMember() {
        return umsMember;
    }
}
