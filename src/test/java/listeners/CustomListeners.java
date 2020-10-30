package listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.relevantcodes.extentreports.LogStatus;

import base.TestBase;
import utilities.TestUtil;

public class CustomListeners extends TestBase implements ITestListener {

	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		test = rep.startTest(arg0.getName().toUpperCase());
	}

	public void onTestSuccess(ITestResult arg0) {
	test.log(LogStatus.PASS, arg0.getName()+ " :Passed");
	rep.endTest(test);
	rep.flush();
	}

	public void onTestFailure(ITestResult arg0) {
	System.setProperty("org.uncommons.reportng.escape-output","false");	
		try {
			TestUtil.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	test.log(LogStatus.FAIL, arg0.getName()+" Failed with Exception: " + arg0.getThrowable());
	test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
	Reporter.log("Click here for screenshot");
	Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+">Screenshot</a>");
	Reporter.log("<a target=\"_blank\"href="+TestUtil.screenshotName+"><img src="+TestUtil.screenshotName + " height=20 width=20></img></a>");
	rep.endTest(test);
	rep.flush();
	}

	public void onTestSkipped(ITestResult arg0) {
		test.log(LogStatus.SKIP, arg0.getName() + " Skipped.");
		rep.endTest(test);
		rep.flush();
	}
	

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext arg0) {
		test = rep.startTest(arg0.getName());
		
	}

	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

}
