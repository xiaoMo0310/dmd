package com.dmd.mall.security.social;

import org.springframework.social.UserIdSource;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;
/**
 * 默认的授权请求时/oauth/qq，这里将/oauth配置成自己想要的/qqLogin
 * */
public class DmdSpringSocialConfigurer extends SpringSocialConfigurer {
    private SocialAuthenticationFilterPostProcessor processor;

    public SocialAuthenticationFilterPostProcessor getProcessor() {
        return processor;
    }

    public void setProcessor(SocialAuthenticationFilterPostProcessor processor) {
        this.processor = processor;
    }

    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter socialAuthenticationFilter=(SocialAuthenticationFilter)super.postProcess(object);
        socialAuthenticationFilter.setFilterProcessesUrl("/auth");
        if (processor!=null){
            processor.process(socialAuthenticationFilter);
        }
        return (T) socialAuthenticationFilter;
    }
}
