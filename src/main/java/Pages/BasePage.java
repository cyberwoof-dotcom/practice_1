package Pages;

import Context.ThreadContextForScenarios;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class BasePage {
    WebDriver driver;

    public WebDriver initDriver(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\work\\chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        ThreadContextForScenarios.getInstance();
        ThreadContextForScenarios.setScenarioContext("driver",driver);
        return driver;
    }
}
