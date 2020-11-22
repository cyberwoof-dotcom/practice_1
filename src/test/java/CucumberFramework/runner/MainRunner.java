package CucumberFramework.runner;

import TestRail.TestResult;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterSuite;

@CucumberOptions (
   features = {"src/test/java/CucumberFramework/featureFiles/sugar_0001.feature"},
   glue = {"CucumberFramework.steps"},
   monochrome = true,
   dryRun = false,
   tags = {"@sugar_0001"},
   plugin = {"pretty", "html:target/cucumber", "json:target/cucumber.json", "com.cucumber.listener.ExtentCucumberFormatter:target/report.html"}
   )

public class MainRunner extends AbstractTestNGCucumberTests {

    @AfterSuite
    public void populateTestResults() throws Exception {
       TestResult testResult=new TestResult();
       testResult.populateTestResult();
    }
    
}
