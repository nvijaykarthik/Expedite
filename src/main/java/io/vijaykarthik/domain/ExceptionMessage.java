package io.vijaykarthik.domain;

public class ExceptionMessage {

	private Integer errorCode;
	private String errorMessage;
	
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public ExceptionMessage() {}
	
	@Override
	public String toString() {
		return "ExceptionMessage [errorCode=" + errorCode + ", errorMessage=" + errorMessage + "]";
	}
	public ExceptionMessage(Integer errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
}
