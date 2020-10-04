package CucumberFramework.runner;

import TestRail.TestResult;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions (
   features = {"src/test/java/CucumberFramework/featureFiles/"},
   glue = {"CucumberFramework.steps"},
   monochrome = true,
   dryRun = false,
   tags = {"@sugar_2154","@sugar_2387"},
   plugin = {"pretty", "html:target/cucumber", "json:target/cucumber.json", "com.cucumber.listener.ExtentCucumberFormatter:target/report.html"}
   )

public class MainRunner {

   @AfterClass
   public static void populateResultsInTestRail(){
      TestResult testResult=new TestResult();
      try {
         testResult.populateTestResult();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
