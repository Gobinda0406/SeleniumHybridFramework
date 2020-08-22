package com.techauto.framework;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReportSetup {

	private GlobalParameters globalParameters = GlobalParameters.getInstance();
	ReportSummary reportSummary;
	List<ReportSummary> reportSummaryList = new ArrayList<>();
	private ExtentReport extentReport = ExtentReport.getInstance();

	private static ReportSetup REPORT_SETUP = new ReportSetup();

	private ReportSetup() {

		// To Prevent External Instantiation
	}

	public static ReportSetup getInstance() {
		return REPORT_SETUP;
	}

	public void setRelativePath() {
		String encryptedPath = System.getProperty("user.dir");
		String relativePath = new File(encryptedPath).getAbsolutePath();
		if (relativePath.contains("supportlibraries")) {
			relativePath = new File(encryptedPath).getParent();
		}
		globalParameters.setRelativePath(relativePath);
	}

	public void setResultPath() {
		String encryptedReportPath = System.getProperty("user.dir");
		File directory = new File(encryptedReportPath + Utility.getFileSeperator() + "Reports"
				+ Utility.getFileSeperator() + "Report_Automation_" + Utility.dateTimeStamp());
		if (!directory.exists()) {
			directory.mkdir();
		}
		globalParameters.setReportFolderPath(directory.getAbsolutePath());
	}

	/**
	 * @return the reportSummary
	 */
	public ReportSummary getReportSummary() {
		return reportSummary;
	}

	/**
	 * @param reportSummary
	 *            the reportSummary to set
	 */
	public void setReportSummary() {
		reportSummary = new ReportSummary();
	}

	public void addToResultSet() {
		reportSummaryList.add(reportSummary);

	}

	/**
	 * Function to serialize JAVA object into JSON file
	 * 
	 */
	public void tearDown() {
		File file = new File(globalParameters.getReportFolderPath() + Utility.getFileSeperator() + "result.json");
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(file, reportSummaryList);
		} catch (JsonGenerationException e) {
			System.out.println("Tear Down= " + e.toString());
		} catch (JsonMappingException e) {
			System.out.println("Tear Down= " + e.toString());
		} catch (IOException e) {
			System.out.println("Tear Down= " + e.toString());
		}

	}

	/**
	 * Function to generate report in multiple format. Currently only Extent
	 * Report is implemented
	 * 
	 */
	public void generateReport() {

		extentReport.generateExtentReport();

	}

	public String captureScreenshot(WebDriver driver) {
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		
		String dest = globalParameters.getReportFolderPath() + Utility.getFileSeperator() + "Screenshot"
				+ Utility.getFileSeperator() + "Screenshot_" + ThreadLocalRandom.current().nextInt() + "_"
				+ Utility.dateTimeStamp() + ".png";
	
		File destination = new File(dest);
		try {
			FileUtils.copyFile(source, destination);
		} catch (IOException e) {
			throw new FrameworkException("Error in capturing screenshot");
		}

		return dest;
	}

}
