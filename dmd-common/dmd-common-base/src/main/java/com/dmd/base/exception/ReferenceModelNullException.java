package com.dmd.base.exception;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/10/23 16:48
 * @Description 
 */
public class ReferenceModelNullException extends RuntimeException {
	private static final long serialVersionUID = -318154770875589045L;

	/**
	 * Instantiates a new Reference model null exception.
	 *
	 * @param message the message
	 */
	public ReferenceModelNullException(String message) {
		super(message);
	}
}
