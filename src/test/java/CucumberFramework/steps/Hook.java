package CucumberFramework.steps;

import Context.ThreadContextForScenarios;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Hook {

    private static String featureFilesPath = ".//src//test//resources//FeatureFiles";

    @Before
    public void initializeTest(Scenario scenario) {
        ThreadContextForScenarios.getInstance();
        System.out.println("Current scenario running is -" + scenario.getName());
        ThreadContextForScenarios.setScenarioContext("scenarioName", scenario.getName());
        System.out.println("ThreadContext Current scenario running is -" + ThreadContextForScenarios.getScenarioContext("scenarioName"));
    }

    @After
    public void embedScreenshot(Scenario scenario) {
        /*if (scenario.isFailed() ) {*/
            try {
                WebDriver driver= (WebDriver) ThreadContextForScenarios.getScenarioContext("driver");
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                System.out.println("**********Screenshot of scenario-"+ scenario.getName());
                scenario.embed(screenshot, "image/png");
                scenario.write(scenario.getName());
                driver.quit();
            } catch (WebDriverException wde) {
                System.err.println(wde.getMessage());
            } catch (ClassCastException cce) {
                cce.printStackTrace();
            }
        //}
    }


    public List<Path> listOfFeatureFiles() {
        List<Path> allFeatureFiles = new LinkedList<>();
        try (Stream<Path> paths = Files.walk(Paths.get(featureFilesPath))) {
            allFeatureFiles = paths.filter(Files::isRegularFile).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allFeatureFiles;
    }
}
