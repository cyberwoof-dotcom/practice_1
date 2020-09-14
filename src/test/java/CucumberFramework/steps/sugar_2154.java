package CucumberFramework.steps;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class sugar_2154 {
	
WebDriver driver;
@Before

public void setup() {
System.setProperty("webdriver.chrome.driver", "C:\\Users\\work\\chromedriver.exe");
this.driver = new ChromeDriver();
this.driver.manage().window().maximize();
this.driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);
}

	@Given("^I navigate to the SugarCRM QA Website$")
	public void i_navigate_to_the_SugarCRM_QA_Website() throws Throwable {
	driver.get("https://qlsugqa01.cmpa.org/");  
	}

	
	@When("^I login with my username \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void i_login_with_my_username_and_password(String username, String password) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']")));
		
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//a[@name='login_button']")).click();
	}
	
	@And("^I select the Member Events Subpanel$")
	public void i_select_the_Member_Events_Subpanel() throws Throwable {
		driver.get("https://qlsugqa01.cmpa.org/#CM_EV_MemberEvents");
	}

	@And("^I select the Create button$")
	public void i_select_the_Create_button() throws Throwable {
		driver.get("https://qlsugqa01.cmpa.org/#CM_EV_MemberEvents/create");
	}

	@And("^I should be able to select the Event Type \"([^\"]*)\" End Membership$")
	public void i_should_be_able_to_select_the_Event_Type_End_Membership(String eventtype) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"s2id_autogen1\"]/a/span[2]")));
		driver.findElement(By.xpath("//*[@id=\"s2id_autogen1\"]/a/span[2]")).click();
		driver.findElement(By.xpath("//*[@id=\'s2id_autogen2_search\']")).sendKeys(eventtype);
		driver.findElement(By.xpath("//*[@id=\'s2id_autogen2_search\']")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//*[@id=\'s2id_autogen2_search\']")).sendKeys(Keys.ENTER);
		//driver.findElement(By.xpath(("//span[@id='select2-chosen-32']"))).click();
	}

	@And("^I should be able to select the \"([^\"]*)\" Reason$")
	public void i_should_be_able_to_select_the_Reason(String reason) throws Throwable {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"s2id_autogen3\"]/a/span[2]")).click();
		driver.findElement(By.xpath("//*[@id=\'s2id_autogen4_search\']")).sendKeys(reason);
		driver.findElement(By.xpath("//*[@id=\'s2id_autogen4_search\']")).sendKeys(Keys.ENTER);
	}

	@And("^I should be able to select an \"([^\"]*)\" Effective Date$")
	public void i_should_be_able_to_select_an_Effective_Date(String effdate) throws Throwable {
		 Thread.sleep(600);
		 driver.findElement(By.name("effective_date_c")).click();
		 Thread.sleep(600);
		 driver.findElement(By.name("effective_date_c")).sendKeys(effdate);
	}

	@And("^I should be able to select a \"([^\"]*)\" Member$")
	public void i_should_be_able_to_select_a_Member(String member) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Select Member...')]")));
		driver.findElement(By.xpath("//span[contains(text(),'Select Member...')]")).click();
		driver.findElement(By.xpath("//*[@id='select2-drop']/ul[2]/li/div")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(("//table[@class='table table-striped dataTable search-and-select']/tbody/tr[1]"))).click();
	}

	@Then("^I should be able to select the Save button and Save the data$")
	public void i_should_be_able_to_select_the_Save_button_and_Save_the_data() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@name='save_button']")));
		driver.findElement(By.xpath("//a[@name='save_button']")).click();
	}
		
	@And("^I will logout of SugarCRM$")
	public void i_will_logout_of_SugarCRM() throws Throwable {
			Thread.sleep(2000);
			driver.findElement(By.xpath("//li[@id='userActions']//button[@class='btn btn-invisible btn-link dropdown-toggle']")).click();
			driver.findElement(By.xpath("//a[text()='Log Out']")).click();
	}
 }

