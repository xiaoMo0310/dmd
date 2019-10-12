package com.dmd.core.config;

import com.dmd.core.interceptor.SqlLogInterceptor;
import com.dmd.core.interceptor.TokenInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 加载LWR规则.
 *
 * @author yang
 */
@Configuration
public class CoreConfiguration {
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public SqlLogInterceptor sqlLogInterceptor() {
		return new SqlLogInterceptor();
	}

	@Bean
	@ConditionalOnMissingBean(HandlerInterceptor.class)
	public TokenInterceptor tokenInterceptor() {
		return new TokenInterceptor();
	}

}
