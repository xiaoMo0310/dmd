package com.dmd.config.properties;


import com.dmd.base.constant.GlobalConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * The class Paascloud properties.
 * @author paascloud.net @gmail.com
 */
@Data
@ConfigurationProperties(prefix = GlobalConstant.ROOT_PREFIX)
public class DmdProperties {
	private AliyunProperties aliyun = new AliyunProperties();
	private AsyncTaskProperties task = new AsyncTaskProperties();
	private SwaggerProperties swagger = new SwaggerProperties();
	private GaodeProperties gaode = new GaodeProperties();
}
