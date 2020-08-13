package driver;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;

import com.techauto.framework.Browser;
import com.techauto.framework.DataProvider;
import com.techauto.framework.ExecutionMode;
import com.techauto.framework.FrameworkException;
import com.techauto.framework.FrameworkSettings;
import com.techauto.framework.GlobalParameters;
import com.techauto.framework.Router;
import com.techauto.framework.Utility;
import com.techauto.framework.model.ConfigLookup;
import com.techauto.framework.model.TestParameters;
import com.techauto.framework.model.TestScripts;

public class DriverScripts {

	private final TestParameters testConfiguration;
	private final List<TestScripts> testScripts;
	private GlobalParameters globalParameters = GlobalParameters.getInstance();
	private Router route;
	private DataProvider dataProvider;
	private Properties properties;
	private AutoDriver driver;
	private WebDriver wd;


	/**
	 * Driver Scripts constructor
	 * 
	 * @param {@link
	 * 			ConfigLookup} object
	 */
	public DriverScripts(TestParameters testConfiguration, List<TestScripts> testScripts) {
		this.testConfiguration = testConfiguration;
		this.testScripts = testScripts;
	}

	/**
	 * Function to execute the test case
	 */
	public void driveTestExecution() {
		startUp();
		initializeWebDriver();
		initializeTestScripts();
		quitWebDriver();

	}

	private void startUp() {
		properties = FrameworkSettings.getInstance();
		defaultSetup();
	}

	private void defaultSetup() {
		if (testConfiguration.getConfigLookup().getExecutionMode() == null) {
			testConfiguration.getConfigLookup()
					.setExecutionMode(ExecutionMode.valueOf(properties.getProperty("DefaultExecutionMode")));
		}
		if (testConfiguration.getConfigLookup().getBrowser() == null) {
			testConfiguration.getConfigLookup().setBrowser(Browser.valueOf(properties.getProperty("DefaultBrowser")));
		}
	}

	private void initializeWebDriver() {

		switch (testConfiguration.getConfigLookup().getExecutionMode()) {

		case LOCAL:
			WebDriver webDriver = WebDriverFactory.getWebDriver(testConfiguration.getConfigLookup().getBrowser());
			driver = new AutoDriver(webDriver);
			wd = webDriver;
			driver.setTestConfiguration(testConfiguration);

			maximizeWindow();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
			driver.get(properties.getProperty("URL"));

			break;
		default:
			throw new FrameworkException("Unhandled Execution Mode!");

		}

	}

	private String initializeDataProvider(String eachTestAction) {

		dataProvider = new DataProvider(globalParameters, testConfiguration);
		dataProvider.setCurrentRow(testConfiguration.getTestCaseId());
		String testAction = eachTestAction;
		int subIteration;
		try {

			String REGEX = ":";
			Pattern pattern = Pattern.compile(REGEX);
			try {
				String[] str = pattern.split(eachTestAction);
				testAction = str[0];
				subIteration = Integer.parseInt(str[1]);
				dataProvider.setIteration(1, subIteration);
			} catch (Exception e) {
				testAction = eachTestAction;
				subIteration = 1;
				dataProvider.setIteration(1, subIteration);
			}
		
			route = new Router(dataProvider, driver);

		} catch (Exception e) {
			throw new FrameworkException(
					"Test Scripts not able to initialize DataProvider. Issue faced for Test Script ID= "
							+ eachTestAction);
		}
		return testAction;
	}

	private void initializeTestScripts() {

		testScripts.forEach(testcase -> {

			if (testcase.getTestCaseId().equals(testConfiguration.getTestCaseId())) {

				testcase.getActionKeyword().forEach(eachTestAction -> {
	
					try {
						String cleanedTestAction = initializeDataProvider(eachTestAction);
						invokeTestAction(cleanedTestAction);
					} catch (Exception e) {
						throw new FrameworkException(
								"Issue with invokeTestAction method for Test Action = " + eachTestAction);
					}

				});

			}

		});

	}

	private void invokeTestAction(String testAction) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, ClassNotFoundException {

		final String CLASS_FILE_EXTENSION = ".class";
		boolean isTestActionFound = false;

		String testScriptPath = globalParameters.getRelativePath() + Utility.getFileSeperator() + "target"
				+ Utility.getFileSeperator() + "test-classes" + Utility.getFileSeperator() + "testscripts";
		File packageDirectory = new File(testScriptPath);
		File[] packageFiles = packageDirectory.listFiles();
		String packageName = packageDirectory.getName();

		for (int i = 0; i < packageFiles.length; i++) {
			File packageFile = packageFiles[i];
			String fileName = packageFile.getName();

			// Fetch .class files
			if (fileName.endsWith(CLASS_FILE_EXTENSION)) {
				// Remove the .class extension to get the class name
				String className = fileName.substring(0, fileName.length() - CLASS_FILE_EXTENSION.length());

				String encryptedtestActionClass = packageName + "." + className;
				Class<?> testActionClass = Class.forName(encryptedtestActionClass);
				Method executeTestActionMethod;

				try {
					executeTestActionMethod = testActionClass.getMethod(testAction, (Class<?>[]) null);
				} catch (NoSuchMethodException ex) {
					continue; // If method is not found in current class then
								// search other classes
				}

				isTestActionFound = true;

				Constructor<?> ctor = testActionClass.getDeclaredConstructors()[0];
				Object businessComponent = ctor.newInstance(route);

				executeTestActionMethod.invoke(businessComponent, (Object[]) null);

				break;
			}
		}

		if (!isTestActionFound) {
			throw new FrameworkException(
					"Test Action " + testAction + " not found within any class " + "inside the testscripts package");
		}

	}

	private void quitWebDriver() {
		switch (testConfiguration.getConfigLookup().getExecutionMode()) {

		case LOCAL:
			wd.quit();
			break;
		default:
			throw new FrameworkException("Unhandled Execution Mode!");
		}

	}

	private void maximizeWindow() {
		wd.manage().window().maximize();
	}
}
