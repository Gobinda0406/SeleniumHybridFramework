package com.techauto.framework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReport {

	private GlobalParameters globalParameters = GlobalParameters.getInstance();
	private static ExtentReport INSTANCE = new ExtentReport();
	private List<ReportSummary> reportSummary;

	private ExtentTest test;

	private ExtentReport() {
		// To Avoid external instantiation
	}

	public static ExtentReport getInstance() {
		return INSTANCE;
	}

	private void readJSONFile() {

		ObjectMapper objectMapper = new ObjectMapper();

		try {
			InputStream input = new FileInputStream(
					globalParameters.getReportFolderPath() + Utility.getFileSeperator() + "result.json");
			try {
				reportSummary = objectMapper.readValue(input, new TypeReference<List<ReportSummary>>() {
				});
			} catch (IOException e) {

			}

		} catch (FileNotFoundException e) {

		}

	}

	public void generateExtentReport() {

		readJSONFile();

		if (reportSummary == null) {
			throw new FrameworkException(
					"Unable to generate Extent Report. Report Summary Object is null after parsing JSON file");
		}
		//ExtentReports report;
		ExtentReports report = new ExtentReports(
				globalParameters.getReportFolderPath() + Utility.getFileSeperator() + "TestResult.html");
		
		reportSummary.forEach(summaryItem -> {
			
			
			test = report.startTest(summaryItem.getTestCaseId(), summaryItem.getTestcaseName());

			summaryItem.getReportSteps().forEach(stepItem -> {

				if (stepItem.getStatus().equals("Pass")) {
					test.log(LogStatus.PASS, stepItem.getTestSteps());
					test.log(LogStatus.PASS, "Snapshot below: " + test.addScreenCapture(stepItem.getScreenshot_path()));
				} else {
					test.log(LogStatus.FAIL, stepItem.getTestSteps());
					test.log(LogStatus.FAIL, "Snapshot below: " + test.addScreenCapture(stepItem.getScreenshot_path()));
				}

			});
			
			report.endTest(test);
			report.flush();
			
		});
		

	}
}
