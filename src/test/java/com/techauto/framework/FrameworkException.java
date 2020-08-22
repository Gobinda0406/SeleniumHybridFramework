package com.techauto.framework;

@SuppressWarnings("serial")
public class FrameworkException extends RuntimeException {
	
	private ReportSetup reportsetup=ReportSetup.getInstance();
	/**
	 * The step name to be specified for the exception
	 */
	public String errorName = "Error";
	
	
	/**
	 * Handle Exception from Framework
	 * @param errorDescription The Exception Message to throw
	 */
	public FrameworkException(String errorDescription) {
		super(errorDescription);
		reportsetup.tearDown();
	}
	
	/**
	 * Handle Exception with parameters
	 * @param errorName The step name for the error
	 * @param errorDescription The Exception message to be thrown
	 */
	public FrameworkException(String errorName, String errorDescription) {
		super(errorDescription);
		this.errorName = errorName;
		reportsetup.tearDown();
	}

	public String getErrorName() {
		return errorName;
	}
}
