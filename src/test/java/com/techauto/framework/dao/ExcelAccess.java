package com.techauto.framework.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.techauto.framework.FrameworkException;
import com.techauto.framework.Utility;

/**
 * Excel DA Layer of Framework
 * 
 * @author https://www.linkedin.com/in/gobinda-roy
 *
 */
public class ExcelAccess {

	private final String filePath, fileName;
	private String dataSheetName;

	/**
	 * Constructor to initialize filePath and fileName when Object is created
	 * for the first time
	 * 
	 * @param filePath
	 *            the abosolute path of file
	 * @param fileName
	 */
	public ExcelAccess(String filePath, String fileName) {
		this.filePath = filePath;
		this.fileName = fileName;
	}

	/**
	 * @return the dataSheetName
	 */
	public String getDataSheetName() {
		return dataSheetName;
	}

	/**
	 * @param dataSheetName
	 *            the dataSheetName to set
	 */
	public void setDataSheetName(String dataSheetName) {
		this.dataSheetName = dataSheetName;
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * This method is defined to search specified key within a column, and
	 * return the corresponding row number
	 * 
	 * @param key
	 *            Search Value
	 * @param columnNum
	 *            The column number in which the key should be searched
	 * @param startRowNum
	 *            The row number where search should start
	 * @return The row number in which the specified key is found
	 * 
	 */
	public int getRowNum(String key, int columnNum, int startRowNum) {
		checkBeforeAccessExcel();

		XSSFWorkbook workbook = openFileForReading();
		XSSFSheet worksheet = getWorkSheet(workbook);
		FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

		String currentValue;
		for (int currentRowNum = startRowNum; currentRowNum <= worksheet.getLastRowNum(); currentRowNum++) {

			XSSFRow row = worksheet.getRow(currentRowNum);
			XSSFCell cell = row.getCell(columnNum);
			currentValue = getCellValueAsString(cell, formulaEvaluator);

			if (currentValue.equals(key)) {
				return currentRowNum;
			}
		}

		return -1;
	}

	/**
	 * This method return value in the cell identified by the specified row
	 * number and column header
	 * 
	 * @param rowNum
	 *            The row number of the cell
	 * @param columnHeader
	 *            The column header of the cell
	 * @return The value present in the cell
	 */
	public String getValue(int rowNum, String columnHeader) {
		checkBeforeAccessExcel();

		XSSFWorkbook workbook = openFileForReading();
		XSSFSheet worksheet = getWorkSheet(workbook);
		FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

		// Header is always at first row
		XSSFRow row = worksheet.getRow(0);

		int columnNum = -1;
		String currentValue;
		for (int currentColumnNum = 0; currentColumnNum < row.getLastCellNum(); currentColumnNum++) {

			XSSFCell cell = row.getCell(currentColumnNum);
			currentValue = getCellValueAsString(cell, formulaEvaluator);

			if (currentValue.equals(columnHeader)) {
				columnNum = currentColumnNum;
				break;
			}
		}

		if (columnNum == -1) {
			throw new FrameworkException("The specified column header \"" + columnHeader + "\""
					+ "is not found in the sheet \"" + dataSheetName + "\"!");
		} else {
			row = worksheet.getRow(rowNum);
			XSSFCell cell = row.getCell(columnNum);
			return getCellValueAsString(cell, formulaEvaluator);
		}
	}

	/**
	 * This method return value in the cell identified by the specified row
	 * number and column number
	 * 
	 * @param rowNum
	 *            The row number of the cell
	 * @param colNum
	 *            The column Number of the cell
	 * @return The value present in the cell
	 */
	public String getValue(int rowNum, int colNum) {
		checkBeforeAccessExcel();

		try {
			XSSFWorkbook workbook = openFileForReading();
			XSSFSheet worksheet = getWorkSheet(workbook);
			FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

			XSSFRow row = worksheet.getRow(rowNum);
			XSSFCell cell = row.getCell(colNum);
			return getCellValueAsString(cell, formulaEvaluator);

		} catch (Exception e) {
			throw new FrameworkException("The specified column number & row number does not return any value \""
					+ "is not found in the sheet \"" + dataSheetName + "\"!");
		}

	}

	/**
	 * 
	 * This function return all the Cell value, considering keys (the header
	 * name of excel)
	 * 
	 * @param Array
	 *            of String
	 * @return List of [{K=V,K=V,K=V},{K=V,K=V,K=V}]
	 */
	public List<Map<String, String>> getValues(String[] keys) {

		XSSFWorkbook workbook = openFileForReading();
		XSSFSheet worksheet = getWorkSheet(workbook);
		FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

		XSSFRow hrow = worksheet.getRow(0);
		List<Map<String, String>> values = new ArrayList<Map<String, String>>();
		for (int i = 1; i <= worksheet.getLastRowNum(); i++) {
			Map<String, String> valueMap = new HashMap<String, String>();
			XSSFRow row = worksheet.getRow(i);
			for (int j = 0; j < keys.length; j++) {
				String value = getValue(hrow, row, keys[j], formulaEvaluator);
				valueMap.put(keys[j], value);
			}
			values.add(valueMap);
		}

		return values;
	}

	public Map<String, String> getValuesForSpecificRow(String[] keys, int rowNum) {
		checkBeforeAccessExcel();

		XSSFWorkbook workbook = openFileForReading();
		XSSFSheet worksheet = getWorkSheet(workbook);
		FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

		XSSFRow hrow = worksheet.getRow(0);

		Map<String, String> valueMap = new HashMap<String, String>();
		XSSFRow row = worksheet.getRow(rowNum);
		for (int j = 0; j < keys.length; j++) {
			String value = getValue(hrow, row, keys[j], formulaEvaluator, keys.length);
			valueMap.put(keys[j], value);
		}

		return valueMap;
	}

	/**
	 * Convinient method to all other Excel Utility methods
	 * 
	 */

	private void checkBeforeAccessExcel() {
		if (dataSheetName == null) {
			throw new FrameworkException("dataSheetName is not found!");
		}
	}

	private XSSFWorkbook openFileForReading() {

		String absoluteFilePath = filePath + Utility.getFileSeperator() + fileName + ".xlsm";

		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream(absoluteFilePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new FrameworkException("The specified file \"" + absoluteFilePath + "\" does not exist!");
		}

		XSSFWorkbook workbook;
		try {
			workbook = new XSSFWorkbook(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
			throw new FrameworkException(
					"Error while opening the specified Excel workbook \"" + absoluteFilePath + "\"");
		}

		return workbook;
	}

	private XSSFSheet getWorkSheet(XSSFWorkbook workbook) {
		XSSFSheet worksheet = workbook.getSheet(dataSheetName);
		if (worksheet == null) {
			throw new FrameworkException("The specified sheet \"" + dataSheetName + "\""
					+ "does not exist within the workbook \"" + fileName + ".xls\"");
		}

		return worksheet;
	}

	@SuppressWarnings("deprecation")
	private String getCellValueAsString(XSSFCell cell, FormulaEvaluator formulaEvaluator) {
		if (cell == null || cell.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
			return "";
		} else {
			if (formulaEvaluator.evaluate(cell).getCellType() == XSSFCell.CELL_TYPE_ERROR) {
				throw new FrameworkException(
						"Error in formula within this cell! " + "Error code: " + cell.getErrorCellValue());
			}

			DataFormatter dataFormatter = new DataFormatter();
			return dataFormatter.formatCellValue(formulaEvaluator.evaluateInCell(cell));
		}
	}

	private String getValue(XSSFRow hrow, XSSFRow row, String columnHeader, FormulaEvaluator formulaEvaluator) {
		int columnNum = -1;
		String currentValue;

		for (int currentColumnNum = 0; currentColumnNum < row.getLastCellNum(); currentColumnNum++) {
			XSSFCell cell = hrow.getCell(currentColumnNum);

			currentValue = getCellValueAsString(cell, formulaEvaluator);

			if (currentValue.equals(columnHeader)) {
				columnNum = currentColumnNum;
				break;
			}
		}

		if (columnNum == -1) {
			throw new FrameworkException("The specified column header \"" + columnHeader + "\""
					+ "is not found in the sheet \"" + dataSheetName + "\"!");
		} else {

			XSSFCell cell = row.getCell(columnNum);
			return getCellValueAsString(cell, formulaEvaluator);
		}
	}

	private String getValue(XSSFRow hrow, XSSFRow row, String columnHeader, FormulaEvaluator formulaEvaluator,
			int length) {
		int columnNum = -1;
		String currentValue;

		for (int currentColumnNum = 0; currentColumnNum < length; currentColumnNum++) {
			XSSFCell cell = hrow.getCell(currentColumnNum);

			currentValue = getCellValueAsString(cell, formulaEvaluator);

			if (currentValue.equals(columnHeader)) {
				columnNum = currentColumnNum;
				break;
			}
		}

		if (columnNum == -1) {
			throw new FrameworkException("The specified column header \"" + columnHeader + "\""
					+ "is not found in the sheet \"" + dataSheetName + "\"!");
		} else {

			XSSFCell cell = row.getCell(columnNum);
			return getCellValueAsString(cell, formulaEvaluator);
		}
	}

}
