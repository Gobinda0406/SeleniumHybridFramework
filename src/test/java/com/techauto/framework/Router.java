package com.techauto.framework;


import driver.AutoDriver;

public class Router {
	
	private final DataProvider dataProvider;
	private AutoReport result;
	private AutoDriver autoDriver;

	
	public Router(AutoReport result,DataProvider dataProvider,AutoDriver autoDriver){
		
		this.result=result;
		this.dataProvider=dataProvider;	
		this.autoDriver=autoDriver;

	}


	/**
	 * This function is to return dataProvider
	 * 
	 * @return the dataProvider
	 */
	public DataProvider getDataProvider() {
		return dataProvider;
	}


	/**
	 * @return the autoDriver
	 */
	public AutoDriver getAutoDriver() {
		return autoDriver;
	}


	/**
	 * @return the result
	 */
	public AutoReport getResult() {
		return result;
	}

	
	
}
