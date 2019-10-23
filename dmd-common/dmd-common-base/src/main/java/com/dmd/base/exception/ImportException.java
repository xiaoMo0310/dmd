package com.dmd.base.exception;


/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/10/23 16:47
 * @Description 
 */
public class ImportException extends RuntimeException {

	private static final long serialVersionUID = -4740091660440744697L;

	/**
	 * Instantiates a new Import exception.
	 *
	 * @param message the message
	 */
	public ImportException(String message) {
		super(message);
	}
}
