package Pages;

import Context.ThreadContextForScenarios;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BasePage {
    WebDriver driver;

    By userNameElement = By.xpath("//input[@name='username']");
    By passwordElement = By.xpath("//input[@name='password']");
    By loginButton = By.xpath("//a[@name='login_button']");
    By logoutIcon = By.xpath("//li[@id='userActions']//button[@class='btn btn-invisible btn-link dropdown-toggle']");
    By logout = By.xpath("//a[text()='Log Out']");

    public WebDriver initDriver() {
        if (System.getProperty("os.name").contains("Windows")) {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\work\\chromedriver.exe");
            this.driver = new ChromeDriver();
            this.driver.manage().window().maximize();
            this.driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            ThreadContextForScenarios.getInstance();
            ThreadContextForScenarios.setScenarioContext("driver", driver);
        } else {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setHeadless(true);
            this.driver = new ChromeDriver(chromeOptions);
        }
        return driver;
    }

    public void navigateToCRMLoginPage() throws Exception {
        Thread.sleep(3000);
        driver.get("https://qlsugqa01.cmpa.org/");
    }

    public void loginWithUserNameAndPassword(String userid, String password) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameElement));

        //Login to Sugar...
        driver.findElement(userNameElement).sendKeys(userid);
        driver.findElement(passwordElement).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public void logOut() throws Exception {
        Thread.sleep(2000);
        driver.findElement(logoutIcon).click();
        driver.findElement(logout).click();
    }

    public void crmLogin(String userid, String password) throws Exception {
        navigateToCRMLoginPage();
        loginWithUserNameAndPassword(userid, password);
    }
}
