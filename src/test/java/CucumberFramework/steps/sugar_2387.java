package CucumberFramework.steps;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class sugar_2387 {

	WebDriver driver;
	//@Before
		public void setup() {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\work\\chromedriver.exe");
			this.driver = new ChromeDriver();
			this.driver.manage().window().maximize();
			this.driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);	
		}
/*
	@After()
			public void tearDown() {
			driver.manage().deleteAllCookies();
			driver.close();
			driver.quit();
	}
*/
	@Given("^I navigate to Sugar CRM$")
	public void i_navigate_to_Sugar_CRM() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.get("https://qlsugqa01.cmpa.org/"); 
	}

	@When("^I login using my userid \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void i_login_using_my_userid_and_password(String userid, String password) throws Throwable {
		//Explicit Wait the UserName and Password are displayed
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']")));
		
		//Login to Sugar...  
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(userid);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//a[@name='login_button']")).click();
	}

	@And("^I select Medical Coding$")
	public void i_select_Medical_Coding() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.get("https://qlsugqa01.cmpa.org/#Quotes"); 
	}

	@And("^I enter a Case ID \"([^\"]*)\"$")
	public void i_enter_a_Case_ID(String caseid) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		//Explicit Wait the Medical Coding Page to be Displayed
		WebDriverWait wait = new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='search-name']")));
		//Enter and search by CaseID
		driver.findElement(By.xpath("//input[@class='search-name']")).click();
		driver.findElement(By.xpath("//input[@class='search-name']")).sendKeys(caseid);
		Thread.sleep(3500);
		driver.findElement(By.xpath("//a[contains(text(),'"+ caseid +"')]")).click();	
		}

	@And("^I should be able to create a Mother record and verify the incremental number and interested party$")
	public void i_should_be_able_to_create_a_Mother_record_and_verify_the_incremental_number_and_interested_party() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		 int countMotherRecordBefore;
		 int countMotherRecordAfter;
		
		 //Explicit Wait before you click on Create a Mother Record
		 Thread.sleep(4000);
			   		
		//Get the Count of Current in the Mother Data Table convert to Integer for Assertion
		String beforeSave = driver.findElement(By.xpath("//h4[contains(text(),'Mother')]/following-sibling::span")).getText();
			
		//Getting Record Count and converting it to integer...
		Thread.sleep(4000);
		//If there are no records, set the record count to 0
		if (beforeSave == null || beforeSave.length() <= 0) {
	    	 countMotherRecordBefore = 0;
		} else { 
			char c1 = beforeSave.charAt(1);
			countMotherRecordBefore = Integer.parseInt(String.valueOf(c1));
		}
		
		//Select the + Button to Create a Mother Record...
		driver.findElement(By.xpath("//h4[contains(text(),'Mother')]/following::a[1]")).click();
	   	
		//Add Data to the Mother Screen
		WebDriverWait wait_1 = new WebDriverWait(driver,15);
		wait_1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='cmpa_gestationweeks_c']")));
	   
	    //Get the Interested Party from the Mother Record and Extract the Case ID & Interested Party
	    //String interestedParty = driver.findElement(By.xpath(("(//span[@class='normal index']//div[@class='ellipsis_inline'])[8]"))).getText();
	    String interestedParty = driver.findElement(By.xpath("//span[@class='detail disabled']/div")).getText();
		String subCaseID = interestedParty.substring(0,9).trim();
	    String subParty = interestedParty.substring(11).trim();
	    
	    //Save the Data in the Mother Module
	    driver.findElement(By.xpath("//a[@class='btn btn-primary']")).click(); 
	    
	    //Get the New Record Count of Mother Records and convert to Integer..
	    Thread.sleep(3500);
		String afterSave = driver.findElement(By.xpath("//h4[contains(text(),'Mother')]/following-sibling::span")).getText();
		char c2 = afterSave.charAt(1);
		countMotherRecordAfter = Integer.parseInt(String.valueOf(c2));
		WebElement table = driver.findElement(By.xpath(("//table[@class='table table-striped table-bordered table-condensed']")));
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		
		//Check for the CaseID and match it to the Case Interested Party
		for (int i=1; i<=rows.size()-1; i++) {
		String caseID = driver.findElement(By.xpath("//table[@class='table table-striped table-bordered table-condensed']/tbody/tr["+ i +"]/td[2]")).getText(); 
		if (caseID.equals(subCaseID)) {
		  String intPartyName = driver.findElement(By.xpath("//table[@class='table table-striped table-bordered table-condensed']/tbody/tr["+ i +"]/td[6]")).getText(); 
		  System.out.println("Record Count Before Record Creation: " + countMotherRecordBefore); 
		  System.out.println("Record Count  After Record Creation: " + countMotherRecordAfter); 
		 //Assert the before and after records don't match
		  Assert.assertNotEquals(countMotherRecordAfter, countMotherRecordBefore);
		  System.out.println("Assert Total Case Check: Passed.");
		  //Assert the CIP value exists in the Dashlet
		  Assert.assertEquals(intPartyName, subParty);
		  System.out.println("Assert CIP is in the Dashlet: Passed.");
		  
	}
  }
}
	@Then("^I Logout of Sugar CRM$")
	public void i_Logout_of_Sugar_CRM() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.xpath("//li[@id='userActions']//button[@class='btn btn-invisible btn-link dropdown-toggle']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
	}
}
