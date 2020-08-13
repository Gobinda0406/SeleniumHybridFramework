package features;

import org.openqa.selenium.Keys;

import com.techauto.framework.Router;
import com.techauto.framework.ScriptLibrary;

import uimap.UiLocators;

/**
 * Class contains Search functionality available in application under
 * test. It extends ScriptLibrary to get DataProvider & WebDriver access
 *
 * @author Computer
 * 
 */
public class Search extends ScriptLibrary {

	public Search(Router router) {
		super(router);
	}
	
	public void search(){
		
		try{
			String searchData=dataProvider.getData("Test_Data", "Search");
			driver.findElement(UiLocators.SEARCH_TEXTBOX).sendKeys(searchData);
			driver.findElement(UiLocators.SEARCH_TEXTBOX).sendKeys(Keys.RETURN);
			Thread.sleep(2000);
			System.out.println("Pass- search");
			// Here we have to call the report. Implementation is in progress
		}catch(Exception e){
			System.out.println("Fail- search"+e.toString());
			// Here we have to call the report. Implementation is in progress
			
		}
	}
	
}
