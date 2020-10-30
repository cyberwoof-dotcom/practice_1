package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.ExcelReader;
import utilities.ExtentManager;
import utilities.TestUtil;


	public class TestBase {

    public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	
	@BeforeSuite
	public void setUp() {
	if (driver == null) {
	try {
		fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		config.load(fis);
		log.debug("Config file loaded!!!");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	try {
		fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	try {
		OR.load(fis);
		log.debug("OR file loaded!!!");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	  if (config.getProperty("browser").equals("firefox")){
	  	  System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\gecko.exe");
		  driver = new FirefoxDriver(); 	
	
	  } else if (config.getProperty("browser").equals("chrome")) {
	  	  System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver.exe");
		  driver = new ChromeDriver(); 
		  log.debug("Chrome Driver Launched !!!");
	}
	  driver.get(config.getProperty("testsiteurl"));
	    log.debug("Navigated to: "+ config.getProperty("testsiteurl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
				TimeUnit.SECONDS);
		wait = new WebDriverWait(driver,5);
  }   
}
	public void click(String locator) {
	 driver.findElement(By.xpath(OR.getProperty(locator))).click();
	 //test.log(LogStatus.INFO, "Clicking on: "+ locator);
	}

	public void type (String locator, String value) {
	 driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
	 if (value == config.getProperty("pwd")) {
		 value = "xxxxxxx";
		 test.log(LogStatus.INFO, "Entering data: "+ locator + " as: "+ value);
	 } else 
		 test.log(LogStatus.INFO, "Entering data: "+ locator + " as: "+ value);
	}


	
	public boolean isElementPresent (By by) {
		try {
			driver.findElement(by);
			return true;
			} catch(NoSuchElementException e) {
			  return false;
			}
		
	}
	
	public static void verifyEquals(String expected, String actual, String testname) throws IOException {
		
		try {
			 Assert.assertEquals(actual, expected, testname);
			 System.out.println("Expected value was "+ expected + " and found " + actual);
		} catch (Throwable t) {
			  //ReportNG
			  Reporter.log("<br>"+ "Failure to verify: "+ testname + t.getMessage() + "<br>");
			  System.out.println("Expected value was: " + expected + " but found " + actual);
		      //Extent Report
		      test.log(LogStatus.FAIL, "Failure to verify : " + t.getMessage());
		}
	}

	
	@AfterSuite
	public void tearDown() {
	 if (driver!=null) {
		driver.quit();
	 }
	 log.debug("Test Execution Completed.");
	}
}