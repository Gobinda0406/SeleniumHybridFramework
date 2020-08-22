package features;

import com.techauto.framework.Router;

import uimap.UiLocators;

/**
 * Class contains Product list page functionalities - Filters, product list,
 * Auto Suggested Brands etc.It extends ScriptLibrary to get DataProvider &
 * WebDriver access
 *
 * @author Computer
 * 
 */
public class ProductList extends BaseFeature {

	public ProductList(Router router) {
		super(router);
	}

	public void clickTodaysDeal() {

		try {

			driver.findElement(UiLocators.CLICK_TODAYS_DEAL).click();
			result.updateReport("Click on Deal is successful", "Pass");
		} catch (Exception e) {
			result.updateReport("Click on Deal is unsuccessful", "Fail");

		}
	}

	public void validateTodaysDeal() {
		try {
			Thread.sleep(3000);
			String text = driver.findElement(UiLocators.TODAYSDEAL_TEXT).getText();

			if (text.trim().equals(dataProvider.getData("Test_Data", "DealText"))) {
				result.updateReport("Validate Deal is successful", "Pass");
			} else {
				result.updateReport("Validate Deal is unsuccessful", "fail");
			}
		} catch (Exception e) {
			System.out.println("Exception in -todaysDeal >> " + e.toString());
		}
	}
}
