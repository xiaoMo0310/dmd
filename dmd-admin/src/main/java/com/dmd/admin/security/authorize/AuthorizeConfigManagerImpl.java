package com.dmd.admin.security.authorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author 王海成
 * @since
 */

public class AuthorizeConfigManagerImpl implements AuthorizeConfigManager {

    @Autowired
    private List<AuthorizeConfigProvider> authorizeConfigProviders;

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        for (AuthorizeConfigProvider provider:authorizeConfigProviders) {
            provider.config(config);
        }
        //config.anyRequest().authenticated();
    }
}
