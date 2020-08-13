package com.techauto.framework;

import com.techauto.framework.dao.ExcelAccess;
import com.techauto.framework.model.TestParameters;

public class DataProvider {

	private String currentTestScript;
	private int currentIteration, currentSubIteration;
	private GlobalParameters globalParameters;
	private TestParameters testParameters;

	/**
	 * @param datatablePath
	 * @param datatableName
	 */
	public DataProvider(GlobalParameters globalParameters, TestParameters testParameters) {
		this.globalParameters = globalParameters;
		this.testParameters = testParameters;
	}

	public void setCurrentRow(String currentTestcase) {
		this.currentTestScript = currentTestcase;
	}

	public void setIteration(int currentIteration, int currentSubIteration) {
		this.currentIteration = currentIteration;
		this.currentSubIteration = currentSubIteration;
	}

	private void checkPreRequisites() {
		if (currentTestScript == null) {
			throw new FrameworkException("CurrentTestCase is not set!");
		}
		if (currentIteration == 0) {
			throw new FrameworkException("CurrentIteration is not set!");
		}

		if (currentSubIteration == 0) {
			throw new FrameworkException("CurrentSubIteration is not set!");
		}

	}

	public String getData(String datasheetName, String fieldName) {
		checkPreRequisites();

		ExcelAccess testDataAccess = new ExcelAccess(
				globalParameters.getRelativePath() + Utility.getFileSeperator() + "src" + Utility.getFileSeperator()
						+ "test" + Utility.getFileSeperator() + "resources" + Utility.getFileSeperator() + "Modules",
				testParameters.getModule());
		testDataAccess.setDataSheetName(datasheetName);

		int rowNum = testDataAccess.getRowNum(currentTestScript, 0, 1);
		if (rowNum == -1) {
			throw new FrameworkException(
					"The test script \"" + currentTestScript + "\"" + "is not found in \"" + datasheetName + "\"!");
		}
		rowNum = testDataAccess.getRowNum(Integer.toString(currentIteration), 1, rowNum);
		if (rowNum == -1) {
			throw new FrameworkException("The iteration number \"" + currentIteration + "\"" + "of the test case \""
					+ currentTestScript + "\"" + "is not found in the test data sheet \"" + datasheetName + "\"!");
		}

		rowNum = testDataAccess.getRowNum(Integer.toString(currentSubIteration), 2, rowNum);
		if (rowNum == -1) {
			throw new FrameworkException("The sub iteration number \"" + currentSubIteration + "\""
					+ "under iteration number \"" + currentIteration + "\"" + "of the test case \"" + currentTestScript
					+ "\"" + "is not found in the test data sheet \"" + datasheetName + "\"!");
		}

		String dataValue = testDataAccess.getValue(rowNum, fieldName);

		return dataValue;
	}

}
