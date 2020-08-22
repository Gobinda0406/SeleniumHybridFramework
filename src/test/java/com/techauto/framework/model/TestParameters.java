package com.techauto.framework.model;

/**
 * Model Class of Automation Manager. POJO class to create new Object It
 * encapsulates input parameters of script
 * 
 * @author https://www.linkedin.com/in/gobinda-roy
 *
 */
public class TestParameters {
	
	private String testCaseId;
	private String module;
	private String testCaseName;
	private String description;
	//private ConfigLookup configLookup;
	
	/**
	 * Constructor to allow external instanciation to create object {@link TestParameters}
	 * @param testCaseId The testcase id to be searched
	 * @param moduleName The module name to be searched
	 * 
	 */
	public TestParameters(String moduleName,String testCaseId) {
		this.module=moduleName;
		this.testCaseId = testCaseId;
	
		//set default as NA
		this.description="NA";

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
	 * @return the module
	 */
	public String getModule() {
		return module;
	}

	/**
	 * @param module the module to set
	 */
	public void setModule(String module) {
		this.module = module;
	}

	/**
	 * @return the testCaseName
	 */
	public String getTestCaseName() {
		return testCaseName;
	}

	/**
	 * @param testCaseName the testCaseName to set
	 */
	public void setTestCaseName(String testCaseName) {
		this.testCaseName = testCaseName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	

	



	
	

}
