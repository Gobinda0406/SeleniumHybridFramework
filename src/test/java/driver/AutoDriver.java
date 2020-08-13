package driver;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.techauto.framework.model.TestParameters;

public class AutoDriver {

	private TestParameters testConfiguration;
	private WebDriver driver;

	public AutoDriver(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * @return the testConfiguration
	 */
	public TestParameters getTestConfiguration() {
		return testConfiguration;
	}

	/**
	 * @param testConfiguration
	 *            the testConfiguration to set
	 */
	public void setTestConfiguration(TestParameters testConfiguration) {
		this.testConfiguration = testConfiguration;
	}

	/**
	 * Function returns WebDriver Object {@link WebDriver} object
	 * 
	 * @return the driver
	 */
	public WebDriver getWebDriver() {
		return (WebDriver) driver;
	}

	// WebDriver Methods
	/**
	 * Function to find {@link WebElement}
	 * 
	 * @param arg0
	 *            The locator used to identify the element {@link WebDriver}
	 * @return the WebElement object
	 */
	public WebElement findElement(By arg0) {
		return driver.findElement(arg0);
	}
	
	
	/**
	 * Function to find all elements matching locator passed as argument
	 * 
	 * @param by
	 *            The locator used to identify the list of elements
	 *            {@link WebDriver}
	 */
	public List<WebElement> findElements(By arg0) {
		return driver.findElements(arg0);
	}

	/**
	 * Function to Load a new web page in the current browser
	 * {@link WebDriver}
	 */
	public void get(String arg0) {
		driver.get(arg0);
	}
	/**
	 * Function to close the driver Object {@link WebDriver}
	 */
	public void close() {
		driver.close();
	}
	/**
	 * Function to Get a string representing the current URL that the browser is
	 * looking at. {@link WebDriver}
	 */
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	/**
	 * Function to Get the source of the last loaded page. {@link WebDriver}
	 */
	public String getPageSource() {
		return driver.getPageSource();
	}

	/**
	 * Function to get The title of the current page. {@link WebDriver}
	 */
	public String getTitle() {
		return driver.getTitle();
	}

	/**
	 * Function to returns a handle of the current page (a unique identifier)
	 */
	public String getWindowHandle() {
		return driver.getWindowHandle();
	}

	/**
	 * Function to returns set of handles of the current page (a unique identifier)
	 */
	public Set<String> getWindowHandles() {
		return driver.getWindowHandles();
	}

	public int hashCode() {
		return driver.hashCode();
	}
	public String toString() {
		return driver.toString();
	}

	public void wait_Driver() throws InterruptedException {
		driver.wait();
	}

	public void wait_Driver(long timeout) throws InterruptedException {
		driver.wait(timeout);
	}

	public void wait_Driver(long timeout, int nanos) throws InterruptedException {
		driver.wait(timeout, nanos);
	}
	/**
	 * Function to wait until the specified element is visible
	 * 
	 * @param by
	 *            The locator used to identify the element {@link WebDriver}
	 */
	public boolean isElementVisible(By arg0) {
		boolean elementVisible = false;
		try {
			(new WebDriverWait(driver, 60)).until(ExpectedConditions.visibilityOfElementLocated(arg0));
			elementVisible = true;

		} catch (TimeoutException ex) {
			elementVisible = false;
		}
		return elementVisible;
	}

}
