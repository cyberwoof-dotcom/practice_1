package testcases;

import TestRail.TestResult;
import org.testng.annotations.Test;

import Enums.Constants;

public class TestRailPublish {

	@Test
	public void publishInTestRail() {
		try {
			TestResult testResult = new TestResult();
			testResult.populateTestResult(Constants.TEST_RAIL_PROJECT_NAME.value, "DataConversionValidation",
					"LoginSugarTest", "DataConversionValidation");
//testResult.populateTestResult(Constants.TEST_RAIL_PROJECT_NAME.value,"DataConversionValidation","2018003393","DataConversionValidation_TestRun");
//testResult.populateTestResult(Constants.TEST_RAIL_PROJECT_NAME.value,"DataConversionValidation","2016014912","DataConversionValidation_TestRun");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


