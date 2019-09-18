package com.dmd.config;


import com.dmd.config.properties.DmdProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * The class Aliyun core config.
 *
 * @author paascloud.net @gmail.com
 */
@Configuration
@EnableConfigurationProperties(DmdProperties.class)
public class DmdCoreConfig {
}
