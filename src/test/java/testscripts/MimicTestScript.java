package testscripts;

import com.techauto.framework.Router;

import features.ProductList;
import features.Search;

/**
 * Class contains Test Actions defined in Module Excel. Test Action method calls
 * feature level methods from classes under {@link features} package. <br>It extends
 * ScriptLibrary to get DataProvider & WebDriver access </br>
 *
 * @author Computer
 * 
 */
public class MimicTestScript extends BaseTestScripts {

	public MimicTestScript(Router router) {
		super(router);
	}

	public void login() {

	}

	public void searchItem() {
		Search search = new Search(router);
		search.search();
	}
	
	public void searchItemP() {
		Search search = new Search(router);
		search.searchI();
	}


	public void validateDeal() {

		ProductList prodlist = new ProductList(router);
		prodlist.clickTodaysDeal();
		prodlist.validateTodaysDeal();
		//result.updateReport(expectedResult, status);

	}

	public void navigateToDashboard() {
		System.out.println("Navigate To Dashbaord");
	}

	public void submitOrder() {
		System.out.println("Submit Order");
	}

	public void approveOrder() {
		System.out.println("Approve Order");
	}

	public void logout() {
		System.out.println("Logout from web app");
	}

	public void navigateToProductList() {
		System.out.println("Navigate To Product List");
	}

}
