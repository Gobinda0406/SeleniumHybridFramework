package com.techauto.framework;


public class GlobalParameters {
	
	private String relativePath;
	private String runSuite;
	private boolean stopExecution = false;
	
	private static final GlobalParameters GLOBAL_PARAMETERS = new GlobalParameters();

	private GlobalParameters() {
		// To prevent external instantiation of this class
	}

	/**
	 * Function to return the singleton instance of the
	 * {@link GLOBAL_PARAMETERS} object
	 * 
	 * @return Instance of the {@link GLOBAL_PARAMETERS} object
	 */
	public static GlobalParameters getInstance() {
		return GLOBAL_PARAMETERS;
	}
	
	/**
	 * @return the globalParameters
	 */
	public static GlobalParameters getGlobalParameters() {
		return GLOBAL_PARAMETERS;
	}
	

	/**
	 * @return the relativePath
	 */
	public String getRelativePath() {
		return relativePath;
	}

	/**
	 * @param relativePath the relativePath to set
	 */
	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}


	/**
	 * This function return suite name in Automation Manager datasheet to consider execution
	 * 
	 * @return the runSuite
	 */
	public String getRunSuite() {
		return runSuite;
	}

	/**
	 * This function set suite name to consider for execution in Automation Manager datasheet
	 * 
	 * @param runSuite the runSuite to set
	 */
	public void setRunSuite(String runSuite) {
		this.runSuite = runSuite;
	}

	/**
	 * @return the stopExecution
	 */
	public boolean isStopExecution() {
		return stopExecution;
	}

	/**
	 * @param stopExecution the stopExecution to set
	 */
	public void setStopExecution(boolean stopExecution) {
		this.stopExecution = stopExecution;
	}

	
	
}
