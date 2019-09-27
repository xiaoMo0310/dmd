package com.dmd.mall.security.server;

import com.dmd.mall.security.usesdetailsservice.MyUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class DmdAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.accessTokenConverter(jwtAccessTokenConverter)
                .userDetailsService(myUserDetailsService);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("dmd").secret("$2a$10$z6SpAlsdHhTRswyFa/R3mucOk8Dgp76TxdjITmR7pPUDAFeVx.4wm")
                .accessTokenValiditySeconds(600)
                .refreshTokenValiditySeconds(999999999)
                .authorizedGrantTypes("refresh_token","password","authorization_code")
                .scopes("all");
    }
}
