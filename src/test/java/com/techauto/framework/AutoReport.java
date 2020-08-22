package com.techauto.framework;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

public class AutoReport {

	ReportSetup reportSetup = ReportSetup.getInstance();
	ReportSummary reportSummary;
	WebDriver driver;
	List<ReportSteps> reportSteps = new ArrayList<ReportSteps>();

	public AutoReport(ReportSummary reportSummary, WebDriver driver) {

		this.reportSummary = reportSummary;
		this.driver = driver;
	}

	public void updateReport(String expectedResult, String status) {
		ReportSteps reportStep = new ReportSteps();
		reportStep.setTestSteps(expectedResult);
		
		if (!status.equals("SKIP_SCREENSHOT")) {
			reportStep.setStatus(status);
			reportStep.setScreenshot_path(reportSetup.captureScreenshot(driver));

		}
		
		reportSteps.add(reportStep);

	}

	public void updateReportSummary(String testCaseId, String testcaseName) {
		reportSummary.setTestCaseId(testCaseId);
		reportSummary.setTestcaseName(testcaseName);
	}

	public void endReportSummary() {

		if (reportSummary != null) {

			reportSummary.setReportSteps(reportSteps);
		}

	}

}
