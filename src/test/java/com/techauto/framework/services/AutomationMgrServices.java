package com.techauto.framework.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.techauto.framework.Browser;
import com.techauto.framework.ExecutionMode;
import com.techauto.framework.FrameworkException;
import com.techauto.framework.FrameworkSettings;
import com.techauto.framework.GlobalParameters;
import com.techauto.framework.Utility;
import com.techauto.framework.dao.ExcelAccess;
import com.techauto.framework.model.TestParameters;
import com.techauto.framework.model.TestScripts;

public class AutomationMgrServices {
	
	private GlobalParameters globalParameters = GlobalParameters.getInstance();
	private Properties properties = FrameworkSettings.getInstance();
	
	public List<TestParameters> getRunInfo() {
		
		setupExecutionMode();
		
		String sheetName = globalParameters.getRunSuite();
		ExcelAccess runManagerAccess = new ExcelAccess(globalParameters.getRelativePath() + Utility.getFileSeperator()
				+ "src" + Utility.getFileSeperator() + "test" + Utility.getFileSeperator() + "resources",
				"AutomationManager");
		runManagerAccess.setDataSheetName(sheetName);

		List<TestParameters> testInstancesToRun = new ArrayList<TestParameters>();
		String[] keys = { "Run", "Module", "TestCaseId", "TestCaseName", "Description"};
		List<Map<String, String>> values = runManagerAccess.getValues(keys);

		for (int currentTestInstance = 0; currentTestInstance < values.size(); currentTestInstance++) {

			Map<String, String> row = values.get(currentTestInstance);
			String executeFlag = row.get("Run");

			if (executeFlag.equalsIgnoreCase("Y")) {

				String currentModuleName = row.get("Module");
				String currentTestcaseId = row.get("TestCaseId");

				TestParameters testParameters = new TestParameters(currentModuleName, currentTestcaseId);
				testParameters.setTestCaseName(row.get("TestCaseName"));
				testParameters.setDescription(row.get("Description"));


				testInstancesToRun.add(testParameters);
				runManagerAccess.setDataSheetName(sheetName);
			}
		}
		return testInstancesToRun;
	}

	private void setupExecutionMode() {
		
		String executionMode = properties.getProperty("ExecutionMode");
		
		if (!"".equals(executionMode)) {
			globalParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));
		} 

		String browser = properties.getProperty("Browser");
		if (!"".equals(browser)) {
			globalParameters.setBrowser(Browser.valueOf(browser));
		} 

	}

	// Get List of Test Script Keyword
	public TestScripts getScriptsTestAction(GlobalParameters globalParameters, Properties properties,
			TestParameters testConfiguration) {

			String testActionFeedPath = globalParameters.getRelativePath() + Utility.getFileSeperator() + "src"
				+ Utility.getFileSeperator() + "test" + Utility.getFileSeperator() + "resources"
				+ Utility.getFileSeperator() + "Modules";
		ExcelAccess testActionFeed = new ExcelAccess(testActionFeedPath, testConfiguration.getModule());
		testActionFeed.setDataSheetName("TestScripts");

		int rowNum = testActionFeed.getRowNum(testConfiguration.getTestCaseId(), 0, 0);
		if (rowNum == -1) {
			throw new FrameworkException(
					"The testCase Id \"" + testConfiguration.getTestCaseId() + "\" is not found in TestScripts sheet!");
		}

		String dataValue;

		TestScripts testActions = new TestScripts();
		testActions.setTestCaseId(testConfiguration.getTestCaseId());
		List<String> testAction = new ArrayList<String>();
		int currentColumnNum = 1;
		while (true) {
			dataValue = testActionFeed.getValue(rowNum, currentColumnNum);
			if ("".equals(dataValue)) {
				break;
			}
			testAction.add(dataValue);
			currentColumnNum++;
		}
		testActions.setActionKeyword(testAction);

		if (testActions.getActionKeyword()==null) {
			throw new FrameworkException("No test action methods are found against the testcase id \""
					+ testConfiguration.getTestCaseId() + "\" in datasheet " + testConfiguration.getModule());
		}

		return testActions;

	}

}
