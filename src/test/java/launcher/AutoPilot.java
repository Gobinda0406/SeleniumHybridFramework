package launcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.techauto.framework.FrameworkSettings;
import com.techauto.framework.GlobalParameters;
import com.techauto.framework.ReportSetup;
import com.techauto.framework.model.TestParameters;
import com.techauto.framework.model.TestScripts;
import com.techauto.framework.services.AutomationMgrServices;

import driver.DriverScripts;

public class AutoPilot {

	private Properties properties = FrameworkSettings.getInstance();
	private GlobalParameters globalParameters = GlobalParameters.getInstance();
	private AutomationMgrServices automationManagerServices = new AutomationMgrServices();
	private ReportSetup reportsetup=ReportSetup.getInstance();
	
	public static void main(String[] args) {
		AutoPilot launch = new AutoPilot();
		launch.startBatchExecution();
	}

	private void startBatchExecution() {
		reportsetup.setRelativePath();
		reportsetup.setResultPath();
		

		String suiteName;
		if (System.getProperty("RunWorksheet") != null) {
			suiteName = System.getProperty("RunWorksheet");
		} else {
			suiteName = properties.getProperty("RunWorksheet");
		}
		globalParameters.setRunSuite(suiteName);

		execute();
		reportsetup.tearDown();
		reportsetup.generateReport();
	}

	/**
	 * This function form Object {@link Automation Manager} & {@link Modules}
	 * 
	 */
	private void execute() {

		// AutomationManagerServices Object call getRunInfo Method to filter out
		// testcases marked as "Y"
		List<TestParameters> testInstances = automationManagerServices.getRunInfo();
		List<TestScripts> testScripts = new ArrayList<TestScripts>();

		for (int currentTestInstance = 0; currentTestInstance < testInstances.size(); currentTestInstance++) {

			TestScripts ts = automationManagerServices.getScriptsTestAction(globalParameters, properties,
					testInstances.get(currentTestInstance));
			testScripts.add(ts);
		}

		for (int currentTestInstance = 0; currentTestInstance < testInstances.size(); currentTestInstance++) {
			
			reportsetup.setReportSummary();
			DriverScripts driverScript = new DriverScripts(reportsetup,testInstances.get(currentTestInstance), testScripts);
			driverScript.driveTestExecution();
			reportsetup.addToResultSet();
			
		}
		
	
	}

	
}
