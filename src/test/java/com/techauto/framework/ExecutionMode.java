package com.techauto.framework;

public enum ExecutionMode {
	/**
	 * Execute on the local machine
	 */
	LOCAL,

	/**
	 * Execute on a selenium grid
	 */
	GRID,

	/**
	 * Execute on a Docker Container {@link RemoteWebDriver}
	 */
	CONTAINER
}
