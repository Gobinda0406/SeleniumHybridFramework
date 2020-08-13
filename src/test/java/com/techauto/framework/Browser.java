package com.techauto.framework;

public enum Browser {
	
	CHROME("chrome"),
	FIREFOX("firefox"),
	INTERNET_EXPLORER("internet explorer");
	
	
	private String value;
	
	Browser(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
