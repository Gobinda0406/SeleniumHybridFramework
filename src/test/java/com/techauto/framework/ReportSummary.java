package com.techauto.framework;

import java.util.List;

public class ReportSummary {

	private String testCaseId;
	private String testcaseName;
	private List<ReportSteps> reportSteps;
	/**
	 * @param testCaseId
	 * @param reportSteps
	 */
	public ReportSummary() {
		this.testCaseId = "NA";
		this.testcaseName="NA";
	}
	
	/**
	 * @return the testCaseId
	 */
	public String getTestCaseId() {
		return testCaseId;
	}
	/**
	 * @param testCaseId the testCaseId to set
	 */
	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}

	/**
	 * @return the testcaseName
	 */
	public String getTestcaseName() {
		return testcaseName;
	}

	/**
	 * @param testcaseName the testcaseName to set
	 */
	public void setTestcaseName(String testcaseName) {
		this.testcaseName = testcaseName;
	}

	/**
	 * @return the reportSteps
	 */
	public List<ReportSteps> getReportSteps() {
		return reportSteps;
	}

	/**
	 * @param reportSteps the reportSteps to set
	 */
	public void setReportSteps(List<ReportSteps> reportSteps) {
		this.reportSteps = reportSteps;
	}
	
	
	
	
}
