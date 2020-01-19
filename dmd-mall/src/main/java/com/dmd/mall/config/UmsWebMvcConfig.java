package com.dmd.mall.config;

import com.dmd.core.config.PcObjectMapper;
import com.dmd.core.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/10/11 13:27
 * @Description 
 */
@Configuration
public class UmsWebMvcConfig extends WebMvcConfigurationSupport {

	@Resource
	private TokenInterceptor vueViewInterceptor;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")
				.addResourceLocations("classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(vueViewInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/swagger-resources/**", "*.js", "/**/*.js", "*.css", "/**/*.css", "/*.html", "/**/*.html", "*.html", "/webjars/**","/sso/**");
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
				//.serializationInclusion(JsonInclude.Include.NON_NULL);
		converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
		PcObjectMapper.buidMvcMessageConverter(converters);
	}

	@Override
	public RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
		ApiVersionHandlerMapping handlerMapping = new ApiVersionHandlerMapping();
		handlerMapping.setOrder(0);
		return handlerMapping;
	}
}
