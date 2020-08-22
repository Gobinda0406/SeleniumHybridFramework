package testscripts;

import org.openqa.selenium.WebDriver;

import com.techauto.framework.Router;

/**
 * Class contains reuse method used by feature classes. Reuse method is declared
 * as static. It extends ScriptLibrary to get DataProvider & WebDriver access
 *
 * @author Computer
 * 
 */
public class ReuseCode extends BaseTestScripts {

	public ReuseCode(Router router) {
		super(router);
		// TODO Auto-generated constructor stub
	}

	public static void launchUrl(WebDriver driver, String url) {
		driver.get(url);
	}

}
