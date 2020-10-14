package CucumberFramework.runner;

import TestRail.TestResult;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterSuite;

//@RunWith(CucumberT.class)
@CucumberOptions (
   features = {"src/test/java/CucumberFramework/featureFiles/sugar_9999.feature"},
   glue = {"CucumberFramework.steps"},
   monochrome = true,
   dryRun = false,
   tags = {"@sugar_2154,@sugar_2387,@sugar_9999"},
   plugin = {"pretty", "html:target/cucumber", "json:target/cucumber.json", "com.cucumber.listener.ExtentCucumberFormatter:target/report.html"}
   )

public class MainRunner extends AbstractTestNGCucumberTests {

    @AfterSuite
    public void populateTestResults() throws Exception {
       TestResult testResult=new TestResult();
       testResult.populateTestResult();
    }
}
