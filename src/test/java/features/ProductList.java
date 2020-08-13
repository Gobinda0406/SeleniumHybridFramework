package features;

import com.techauto.framework.Router;
import com.techauto.framework.ScriptLibrary;

import uimap.UiLocators;

/**
 * Class contains Product list page functionalities - Filters, product list,
 * Auto Suggested Brands etc.It extends ScriptLibrary to get DataProvider &
 * WebDriver access
 *
 * @author Computer
 * 
 */
public class ProductList extends ScriptLibrary {

	public ProductList(Router router) {
		super(router);
	}

	public void clickTodaysDeal() {

		try {
			driver.findElement(UiLocators.CLICK_TODAYS_DEAL).click();
			System.out.println("Pass-clickTodaysDeal");
			// Here we have to call the report. Implementation is in progress
		} catch (Exception e) {
			System.out.println("Fail-clickTodaysDeal");
			// Here we have to call the report. Implementation is in progress
		}
	}

	public void validateTodaysDeal() {
		try {
			Thread.sleep(3000);
			String text = driver.findElement(UiLocators.TODAYSDEAL_TEXT).getText();

			if (text.trim().equals(dataProvider.getData("Test_Data", "DealText"))) {
				System.out.println("Pass-todaysDeal");
				// Here we have to call the report. Implementation is in
				// progress
			} else {
				System.out.println("Fail-todaysDeal");
				// Here we have to call the report. Implementation is in
				// progress
			}
		} catch (Exception e) {
			System.out.println("Exception in -todaysDeal >> "+e.toString());
		}
	}
}
