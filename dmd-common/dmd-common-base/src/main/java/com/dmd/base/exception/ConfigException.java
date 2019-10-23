package com.dmd.base.exception;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/10/23 16:37
 * @Description 配置异常
 */
public class ConfigException extends RuntimeException {

	private static final long serialVersionUID = 6480772904575978373L;

	/**
	 * Instantiates a new Config exception.
	 *
	 * @param message the message
	 */
	public ConfigException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new Config exception.
	 */
	public ConfigException() {

	}
}
