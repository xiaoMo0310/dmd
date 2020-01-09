/**
 * 
 */
package com.dmd.mall.security.social.weixin.config;

import com.dmd.mall.security.social.view.DmdResultView;
import com.dmd.mall.security.social.weixin.connect.WeixinConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.web.servlet.View;

/**
 * 微信登录配置
 * 
 * @author zhailiang
 *
 */
@Configuration
public class WeixinAutoConfiguration extends SocialConfigurerAdapter {

	@Autowired
	private SecurityProperties securityProperties;

	@Override
	public void addConnectionFactories(ConnectionFactoryConfigurer configurer,
									   Environment environment) {
		configurer.addConnectionFactory(createConnectionFactory());
	}
	public ConnectionFactory<?> createConnectionFactory() {
		return new WeixinConnectionFactory("weixin", "wxecf55d2536e35e80",
				"ab2156a97ca1dc6305d8427899c83a06");
	}

	@Bean({"connect/weixinConnected","connect/weixinConnect"})
	public View weixinConnectView(){
		return new DmdResultView();
	}
	
}
