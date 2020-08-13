package com.techauto.framework.model;
import com.techauto.framework.Browser;
import com.techauto.framework.ExecutionMode;


public class ConfigLookup {

	private String configurationId;
	private ExecutionMode executionMode;
	private Browser browser;
	private String browserVersion;


	/**
	 *Constructor to initialize ConfigLookup class with valid configuration id 
	 * 
	 * @param configurationId The id to be searched
	 */
	public ConfigLookup(String configurationId) {
		this.configurationId=configurationId;
	
	}

	/**
	 * @return the executionMode
	 */
	public ExecutionMode getExecutionMode() {
		return executionMode;
	}

	/**
	 * @return the browser
	 */
	public Browser getBrowser() {
		return browser;
	}

	/**
	 * @return the browserVersion
	 */
	public String getBrowserVersion() {
		return browserVersion;
	}


	/**
	 * @return the configurationId
	 */
	public String getConfigurationId() {
		return configurationId;
	}

	/**
	 * @param configurationId the configurationId to set
	 */
	public void setConfigurationId(String configurationId) {
		this.configurationId = configurationId;
	}

	/**
	 * @param executionMode the executionMode to set
	 */
	public void setExecutionMode(ExecutionMode executionMode) {
		this.executionMode = executionMode;
	}

	/**
	 * @param browser the browser to set
	 */
	public void setBrowser(Browser browser) {
		this.browser = browser;
	}

	/**
	 * @param browserVersion the browserVersion to set
	 */
	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}


	
	
}
