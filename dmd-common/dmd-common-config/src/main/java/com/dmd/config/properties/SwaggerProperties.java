package com.dmd.config.properties;

import lombok.Data;

/**
 * The class Async task properties.
 *
 * @author paascloud.net @gmail.com
 */
@Data
public class SwaggerProperties {

	private String title;

	private String description;

	private String version = "1.0-SNAPSHOT";

	private String license = "Apache License 2.0";

	private String licenseUrl = "http://www.apache.org/licenses/LICENSE-2.0";

	private String contactName = "Yang";

	private String contactUrl = "www.baidu.com";

	private String contactEmail = "hello@qq.com";
}
