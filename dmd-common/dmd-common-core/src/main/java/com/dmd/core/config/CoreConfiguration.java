package com.dmd.core.config;

import com.dmd.core.interceptor.SqlLogInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 加载LWR规则.
 *
 * @author paascloud.net@gmail.com
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

}
