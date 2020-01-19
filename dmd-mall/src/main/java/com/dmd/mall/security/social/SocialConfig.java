package com.dmd.mall.security.social;

import com.dmd.mall.security.social.weixin.config.JdbcUsersConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private DmdConnectionSignUp signUp;
    //成功或失败处理器
    @Autowired
    private SocialAuthenticationFilterPostProcessor processor;
    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository repository=new JdbcUsersConnectionRepository(dataSource,connectionFactoryLocator, Encryptors.noOpText());
//        repository.setTablePrefix("ums_m_");
        repository.setConnectionSignUp(signUp);//配置自动生成的登录账号
        return repository;
    }
    @Bean
    public SpringSocialConfigurer getSpringSocialConfigurer(){
        DmdSpringSocialConfigurer dmdSpringSocialConfigurer=new DmdSpringSocialConfigurer();
        dmdSpringSocialConfigurer.userIdSource(getUserIdSource());
        dmdSpringSocialConfigurer.setProcessor(processor);
        return dmdSpringSocialConfigurer;
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }
    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator){
        return new ProviderSignInUtils(connectionFactoryLocator,getUsersConnectionRepository(connectionFactoryLocator)){};
    }
}
