package driver;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.techauto.framework.Browser;
import com.techauto.framework.FrameworkException;
import com.techauto.framework.FrameworkSettings;

public class WebDriverFactory {
	private static Properties properties;

	// private constructor to avoid multiple instantiation
	private WebDriverFactory() {

	}
	
	
	/**
	 * This function take Browser as input parameter and return WebDriver of type {@link Browser}
	 * 
	 * @param browser
	 * 
	 * @return The Webdriver Object {@link WenDriver} object
	 */
	public static WebDriver getWebDriver(Browser browser) {
		WebDriver driver = null;
		properties = FrameworkSettings.getInstance();
		switch (browser) {
		case CHROME:
			System.setProperty("webdriver.chrome.driver", properties.getProperty("ChromeDriverPath"));
			driver = new ChromeDriver();
			break;

		case FIREFOX:
			System.setProperty("webdriver.gecko.driver", properties.getProperty("FireFoxDriverPath"));
			driver = new FirefoxDriver();
			break;

		case INTERNET_EXPLORER:
			System.setProperty("webdriver.ie.driver", properties.getProperty("InternetExplorerPath"));
			driver = new InternetExplorerDriver();
			break;

		default:
			throw new FrameworkException("Unhandled browser!");
		}

		return driver;
	}
	

}
