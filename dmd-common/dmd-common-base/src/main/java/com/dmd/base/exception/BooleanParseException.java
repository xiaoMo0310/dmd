package com.dmd.base.exception;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/10/23 16:37
 * @Description 解析异常
 */
public class BooleanParseException extends RuntimeException {

	/**
	 * Instantiates a new Boolean parse exception.
	 */
	public BooleanParseException() {
		super();
	}

	/**
	 * Instantiates a new Boolean parse exception.
	 *
	 * @param message the message
	 */
	public BooleanParseException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new Boolean parse exception.
	 *
	 * @param message the message
	 * @param cause   the cause
	 */
	public BooleanParseException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new Boolean parse exception.
	 *
	 * @param cause the cause
	 */
	public BooleanParseException(Throwable cause) {
		super(cause);
	}

}
