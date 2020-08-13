package com.techauto.framework;

import java.io.FileInputStream;
import java.util.Properties;


public class FrameworkSettings {

	private static final Properties prop=loadFromWebSettings();

	
	private FrameworkSettings(){
		//private constructor to prevent external instantiation
	}
	
	/**
	 * Function to return Singleton instance of WebSettings Object 
	 * 
	 * @return Instance of Properties Object {@link prop}
	 */
	public static Properties getInstance(){
		return prop;
	}
	
	private static Properties loadFromWebSettings(){
		Properties prop=new Properties();
		
		try{
			String pathToLocateWebSettingsFile="src"+Utility.getFileSeperator()+"test"+Utility.getFileSeperator()+"resources"+Utility.getFileSeperator()+"WebSettings.properties";
			prop.load(new FileInputStream(pathToLocateWebSettingsFile));
		}catch(Exception ex){
			ex.printStackTrace();
			throw new FrameworkException("Exception while loading the Web Settings file");
		}
		return prop;
		
	}
}
