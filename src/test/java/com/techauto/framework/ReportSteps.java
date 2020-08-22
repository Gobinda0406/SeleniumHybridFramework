package com.techauto.framework;

public class ReportSteps {

	private String testSteps;
	private String status;
	private String screenshot_path;

	public ReportSteps() {
		this.testSteps="NA";
		this.status="NA";
		this.screenshot_path="NA";
	}

	/**
	 * @return the testSteps
	 */
	public String getTestSteps() {
		return testSteps;
	}

	/**
	 * @param testSteps
	 *            the testSteps to set
	 */
	public void setTestSteps(String testSteps) {
		this.testSteps = testSteps;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the screenshot_path
	 */
	public String getScreenshot_path() {
		return screenshot_path;
	}

	/**
	 * @param screenshot_path the screenshot_path to set
	 */
	public void setScreenshot_path(String screenshot_path) {
		this.screenshot_path = screenshot_path;
	}
	
	
}
