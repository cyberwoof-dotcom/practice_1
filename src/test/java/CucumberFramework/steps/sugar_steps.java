package CucumberFramework.steps;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class sugar_steps {
	WebDriver driver = null;
	private static String chromeDriverExecutablePath=".//chromedriver.exe";
	
	@Before
		public void setup() {
			System.setProperty("webdriver.chrome.driver", chromeDriverExecutablePath);
			this.driver = new ChromeDriver();
			this.driver.manage().window().maximize();
			this.driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);	
		}

	@After()
			public void tearDown() {
			driver.manage().deleteAllCookies();
			driver.close();
			driver.quit();
	}

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
	

		@Given("^I navigate to the SugarCRM Login Page$")
		public void i_navigate_to_the_SugarCRM_Login_Page() throws Throwable {
			Thread.sleep(3000);
			driver.get("https://qlsugqa01.cmpa.org/"); 
		  }

		@When("^I submit my User Name and Password$")
		public void i_submit_my_User_Name_and_Password() throws Throwable {
			Thread.sleep(3000);
			driver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Password123");
			driver.findElement(By.xpath("//a[@name='login_button']")).click();
		  }

		@Then("^I should be directed to the SugarCRM Main Page$")
		public void i_should_be_directed_to_the_SugarCRM_Main_Page() throws Throwable {
		        Thread.sleep(4000);
			if (driver.findElement(By.xpath("//a[@class='cube btn btn-invisible btn-link']//img")) != null) {
		          System.out.println("Login successful.");
		       } else
		    	  System.out.println("Login failed.");
		  }

		@Given("^I select the Member Files Menu$")
		public void i_select_the_Member_Files_Menu() throws Throwable {
			Thread.sleep(4000);
			driver.get("https://qlsugqa01.cmpa.org/#FI_Files");
		  }

		@Given("^I enter a File Number$")
		public void i_enter_a_File_Number() throws Throwable {
			Thread.sleep(3000);
			driver.findElement(By.xpath("//input[@placeholder='Search by name...']")).click();
			driver.findElement(By.xpath("//input[@placeholder='Search by name...']")).sendKeys("2000000075");
		 }

		@Given("^I select the File$")
		public void i_select_the_File() throws Throwable {
			Thread.sleep(3000);
			driver.findElement(By.xpath("(//div[@class='ellipsis_inline'])[2]/a")).click();
		  }

		@Given("^I extract and print the File Name$")
		public void i_extract_and_print_the_File_Name() throws Throwable {
			Thread.sleep(3000);
			String ActualStr_1 = "2000000075";
			String fileName = driver.findElement(By.xpath(("(//span[@class='nodata']//div[@class='ellipsis_inline'])[1]"))).getAttribute("innerHTML").trim();
			fileName = fileName.substring(0,10);
			System.out.println("This is the File Name: " + fileName);
			Assert.assertEquals(ActualStr_1, fileName);
			System.out.println("Assert Successful.");
		  }

		@Given("^I extract and print the Clinical Summary$")
		public void i_extract_and_print_the_Clinical_Summary() throws Throwable {
			 //Thread.sleep(3000);
			 //WebElement textArea = driver.findElement(By.xpath("//textarea[@name='clinical_summary_c']"));
			 //String clinicSum = driver.findElement(By.xpath(("//textarea[@name='clinical_summary_c']"))).getAttribute("innerHTML").trim();
		   	 System.out.println("Clinical Summary - Not Implemented");
		  }

		@Given("^I extract and print the Assigned to$")
		public void i_extract_and_print_the_Assigned_to() throws Throwable {
			Thread.sleep(3000);
			String ActualStr_2 = "Marcel Valade";
			String assignTo = driver.findElement(By.xpath(("(//span[@class='normal index']//a)[1]"))).getAttribute("innerHTML").trim();
			System.out.println("Assigned To: " + assignTo);
			Assert.assertEquals(ActualStr_2, assignTo);
			System.out.println("Assert Successful.");
		  }

		@Given("^I extract Verify Status$")
		public void i_extract_Verify_Status() throws Throwable {
			String ActualStr_3 = "Closed";
			String fileStatus = driver.findElement(By.xpath(("(//span[@class='detail']//div[@class='ellipsis_inline'])[1]"))).getAttribute("innerHTML").trim();
			System.out.println("This is the File Status: " + fileStatus);
			Assert.assertEquals(ActualStr_3, fileStatus);
			System.out.println("Assert Successful.");
		  }

		@Given("^I extract and print the DOO$")
		public void i_extract_and_print_the_Date_of_Occurance() throws Throwable {
			String ActualStr_4 = "11/11/2019";
			String DOO = driver.findElement(By.xpath(("(//span[@class='nodata']//div[@class='ellipsis_inline'])[2]"))).getAttribute("innerHTML").trim();
			System.out.println("This is the DOO: " + DOO);
			Assert.assertEquals(ActualStr_4, DOO);
			System.out.println("Assert Successful.");
		  }

		@Given("^I extract and print the COO$")
		public void i_extract_and_print_the_COO() throws Throwable {
			String ActualStr_5 = "na";
			String COO = driver.findElement(By.xpath(("(//span[@class='nodata']//div[@class='ellipsis_inline'])[4]"))).getAttribute("innerHTML").trim();
			System.out.println("This is the COO: " + COO);
			Assert.assertEquals(ActualStr_5, COO);
			System.out.println("Assert Successful.");
		  }

		@Given("^I extract and print the POO$")
		public void i_extract_and_print_the_POO() throws Throwable {
			String POO = driver.findElement(By.xpath(("(//span[@class='nodata']//div[@class='ellipsis_inline'])[3]"))).getAttribute("innerHTML").trim();
			String ActualStr_6 = "QC - Quebec";
			System.out.println("This is the POO: " + POO);
			Assert.assertEquals(ActualStr_6, POO);
			System.out.println("Assert Successful.");
		  }

		@Then("^I will mark this test as Complete$")
		public void i_will_mark_this_test_as_Complete() throws Throwable {
			Thread.sleep(1500);
			driver.findElement(By.xpath("//li[@id='userActions']//button[@class='btn btn-invisible btn-link dropdown-toggle']")).click();
			driver.findElement(By.xpath("//a[text()='Log Out']")).click();
			System.out.println("Test Completed - Logged-out.");
		 }
	}

