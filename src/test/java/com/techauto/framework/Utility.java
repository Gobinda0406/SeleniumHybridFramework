package com.techauto.framework;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utility {

	/**
	 * To avoid any External instantiation of class private constructor is used
	 * 
	 * @author https://www.linkedin.com/in/gobinda-roy
	 */
	private Utility() {

	}

	/**
	 * It is to seperate string using //
	 * 
	 * @return File Seperator
	 */
	public static String getFileSeperator() {
		return System.getProperty("file.separator");
	}

	/**
	 * Function to return Date and Time in format <b>"yyyy-MM-dd-HH-mm-ss"</b>
	 * 
	 * @return dateTimeStamp 
	 * 				Date & Time
	 */
	public static String dateTimeStamp() {

		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
		String dateTimeStamp = currentTime.format(formatter);
		return dateTimeStamp;
	}
}
