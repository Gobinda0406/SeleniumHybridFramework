package features;

import org.openqa.selenium.Keys;

import com.techauto.framework.Router;

import uimap.UiLocators;

/**
 * Class contains Search functionality available in application under test. It
 * extends ScriptLibrary to get DataProvider & WebDriver access
 *
 * @author Computer
 * 
 */
public class Search extends BaseFeature {

	public Search(Router router) {
		super(router);
	}

	public void search() {

		try {
			String searchData = dataProvider.getData("Test_Data", "Search");
			driver.findElement(UiLocators.SEARCH_TEXTBOX).sendKeys(searchData);
			driver.findElement(UiLocators.SEARCH_TEXTBOX).sendKeys(Keys.RETURN);
			Thread.sleep(2000);
			result.updateReport("Search is successful", "Pass");
		
		} catch (Exception e) {
			result.updateReport("Search is unsuccessful", "Fail");
	

		}
	}

	public void searchI() {

		try {
			String searchData = dataProvider.getData("Test_Data", "Search");
			driver.findElement(UiLocators.SEARCH_TEXTBOX).sendKeys(searchData);
			driver.findElement(UiLocators.SEARCH_TEXTBOX).sendKeys(Keys.RETURN);
			Thread.sleep(2000);
			result.updateReport("TC 002 Search is successful", "Pass");

		} catch (Exception e) {
			System.out.println("Fail- search" + e.toString());
			result.updateReport("TC002 Search is unsuccessful", "Fail");


		}
	}

}
