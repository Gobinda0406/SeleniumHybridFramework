package launcher;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.techauto.framework.FrameworkSettings;
import com.techauto.framework.GlobalParameters;
import com.techauto.framework.model.TestParameters;
import com.techauto.framework.model.TestScripts;
import com.techauto.framework.services.AutomationMgrServices;

import driver.DriverScripts;

public class AutoPilot {

	private Properties properties = FrameworkSettings.getInstance();
	private GlobalParameters globalParameters = GlobalParameters.getInstance();
	private AutomationMgrServices automationManagerServices = new AutomationMgrServices();

	public static void main(String[] args) {
		AutoPilot launch = new AutoPilot();
		launch.startBatchExecution();
	}

	private void startBatchExecution() {
		setRelativePath();

		String suiteName;
		if (System.getProperty("RunWorksheet") != null) {
			suiteName = System.getProperty("RunWorksheet");
		} else {
			suiteName = properties.getProperty("RunWorksheet");
		}
		globalParameters.setRunSuite(suiteName);

		execute();
	}

	/**
	 * This function form Object {@link Automation Manager} & {@link Modules}
	 * 
	 */
	private void execute() {

		// AutomationManagerServices Object call getRunInfo Method to filter out
		// testcases marked as "Y"
		List<TestParameters> testInstances = automationManagerServices.getRunInfo(globalParameters, properties);
		List<TestScripts> testScripts = new ArrayList<TestScripts>();

		for (int currentTestInstance = 0; currentTestInstance < testInstances.size(); currentTestInstance++) {

			TestScripts ts = automationManagerServices.getScriptsTestAction(globalParameters, properties,
					testInstances.get(currentTestInstance));
			testScripts.add(ts);
		}

		for (int currentTestInstance = 0; currentTestInstance < testInstances.size(); currentTestInstance++) {
			DriverScripts driverScript = new DriverScripts(testInstances.get(currentTestInstance), testScripts);
			driverScript.driveTestExecution();
			System.out.println("*********************Test Suite Run done***************************");
		}
	}

	/**
	 * 
	 * Convinient Methods
	 * 
	 */
	private void setRelativePath() {
		String encryptedPath = System.getProperty("user.dir");
		String relativePath = new File(encryptedPath).getAbsolutePath();
		if (relativePath.contains("supportlibraries")) {
			relativePath = new File(encryptedPath).getParent();
		}
		globalParameters.setRelativePath(relativePath);
	}
}
