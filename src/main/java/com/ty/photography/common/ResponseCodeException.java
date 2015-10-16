package com.ty.photography.common;

/**
 * http非200异常
 * @author wits
 *
 */
public class ResponseCodeException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResponseCodeException() {
		super();
	}

	public ResponseCodeException(String msg) {
		super(msg);
	}

	public ResponseCodeException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ResponseCodeException(Throwable cause) {
		super(cause);
	}


}
