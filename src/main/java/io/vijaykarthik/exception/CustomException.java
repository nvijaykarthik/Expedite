package io.vijaykarthik.exception;

public class CustomException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6394109808195570056L;

	public CustomException() {
		super();
	}
	
	public CustomException(String message) {
		super(message);
	}
}
