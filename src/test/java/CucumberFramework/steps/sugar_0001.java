package CucumberFramework.steps;

import org.openqa.selenium.WebDriver;

import Pages.BasePage;
import Pages.Sugar0001Page;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class sugar_0001 extends BasePage {
	
	Sugar0001Page sugar0001Page;
    WebDriver driver;

    public sugar_0001() {
        driver = initDriver();
        sugar0001Page = new Sugar0001Page(driver);
    }

    @Given("^user navigates to the SugarCRM QA Website$")
    public void i_navigate_to_the_SugarCRM_QA_Website() throws Exception {
        navigateToCRMLoginPage(driver);
    }
       
    @When("^user logs in with username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void user_logins_in_with_username_and_password(String username, String password) throws Throwable {
    	loginWithUserNameAndPassword(driver, username, password);
    }
    
	@And("^user navigates to Members Search Page$")
	public void navigate_to_the_Members_Search_Page() throws Throwable {
		sugar0001Page.selectMemberSearchPage();
	}
	
	@And("^user searches for a \"([^\"]*)\" Member$")
	public void user_searches_for_a_type_Member(String membername) throws Throwable {
		sugar0001Page.searchMemberName(membername);
	}
	

	@And("^user selects the member to process using \"([^\"]*)\" member id$")
	public void user_selects_the_member_to_process_using_member_id(String memberid) throws Throwable {
		sugar0001Page.selectMember(memberid);
	}
		
	
	@And("^user creates a new file using the plus on the file panel$")
	public void user_creates_a_new_file_using_the_plus_on_the_file_panel() throws Throwable {
		sugar0001Page.selectCreateNewFile();
	}
	
	@And("^verify the new file is displayed for data entry$")
	public void verify_the_new_file_is_displayed_for_data_entry() throws Throwable {
		sugar0001Page.checkFilePageDisplayed();
	}
	
	@And("^enters the \"([^\"]*)\" Clinical Summary Field$")
	public void enters_the_clinicalSummary_Clinical_Summary_Field(String clinicalsummary) throws Throwable {
		sugar0001Page.enterClinicalSummary(clinicalsummary);
	}

	@And("^enters the \"([^\"]*)\" Assigned To Field$")
	public void enters_the_assignedTo_Assigned_To_Field(String assignedto) throws Throwable {
		sugar0001Page.enterAssignedTo(assignedto);
	}

	@And("^enters the \"([^\"]*)\" Special Issue Field$")
	public void enters_the_specialIssues_Special_Issue_Field(String specialissue) throws Throwable {
		sugar0001Page.enterSpecialIssue(specialissue);
	}
	
	@And("^saves the record$")
	public void save_the_record() throws Throwable {
		sugar0001Page.saveFile();
	}
	
}

