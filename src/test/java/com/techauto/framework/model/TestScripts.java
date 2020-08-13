package com.techauto.framework.model;

import java.util.List;

public class TestScripts {

	private String testCaseId;
	private List<String> actionKeyword;
	
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
	 * @return the actionKeyword
	 */
	public List<String> getActionKeyword() {
		return actionKeyword;
	}
	/**
	 * @param actionKeyword the actionKeyword to set
	 */
	public void setActionKeyword(List<String> actionKeyword) {
		this.actionKeyword = actionKeyword;
	}
	
}
