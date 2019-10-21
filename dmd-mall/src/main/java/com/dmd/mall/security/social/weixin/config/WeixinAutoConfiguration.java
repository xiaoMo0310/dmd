/**
 * 
 */
package com.dmd.mall.security.social.weixin.config;

import com.dmd.mall.security.social.view.DmdResultView;
import com.dmd.mall.security.social.weixin.connect.WeixinConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

	protected ConnectionFactory<?> createConnectionFactory() {
		return new WeixinConnectionFactory("weixin", "wxd99431bbff8305a0",
				"60f78681d063590a469f1b297feff3c4");
	}

	@Bean({"connect/weixinConnected","connect/weixinConnect"})
	public View weixinConnectView(){
		return new DmdResultView();
	}
	
}
