package in.expedite.utils;

import java.util.Map;

public class ExceptionHandlerMapping {

	private Map<Class<? extends Exception>,ExceptionErrorMapping> exceptionHandler;

	public Map<Class<? extends Exception>, ExceptionErrorMapping> getExceptionHandler() {
		return exceptionHandler;
	}

	public void setExceptionHandler(Map<Class<? extends Exception>, ExceptionErrorMapping> exceptionHandler) {
		this.exceptionHandler = exceptionHandler;
	}
	
	
}
