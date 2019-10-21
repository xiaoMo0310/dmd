package com.dmd.mall.security.social.weixin;

import com.dmd.mall.component.GoAuthenticationFailureHandler;
import com.dmd.mall.component.GoAuthenticationSuccessHandler;
import com.dmd.mall.security.social.SocialAuthenticationFilterPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class SocialAuthenticationFilterPostProcessorImpl implements SocialAuthenticationFilterPostProcessor {

    @Autowired
    private GoAuthenticationSuccessHandler successHandler;
    @Autowired
    private GoAuthenticationFailureHandler failureHandler;

    @Override
    public void process(SocialAuthenticationFilter socialAuthenticationFilter) {
        socialAuthenticationFilter.setAuthenticationSuccessHandler(successHandler);
        socialAuthenticationFilter.setAuthenticationFailureHandler(failureHandler);
    }
}
