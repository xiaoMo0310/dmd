package com.dmd.mall.security.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
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
    @Autowired(required = false)
    private SocialAuthenticationFilterPostProcessor processor;
    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository repository=new JdbcUsersConnectionRepository(dataSource,connectionFactoryLocator, Encryptors.noOpText());
        repository.setTablePrefix("dmd_");
        repository.setConnectionSignUp(signUp);//配置自动生成的登录账号
        return repository;
    }
    @Bean
    public SpringSocialConfigurer getSpringSocialConfigurer(){
        DmdSpringSocialConfigurer dmdSpringSocialConfigurer=new DmdSpringSocialConfigurer();
        dmdSpringSocialConfigurer.setProcessor(processor);
        return dmdSpringSocialConfigurer;
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new UserIdSource() {
            public String getUserId() {
                return "anonymous";
            }
        };
    }
}
