package CucumberFramework.steps;

import Pages.BasePage;
import Pages.Sugar2387Page;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

public class sugar_2387 extends BasePage {

    WebDriver driver;
    Sugar2387Page sugar2387Page;

    public sugar_2387() {
        driver = initDriver();
        sugar2387Page = new Sugar2387Page(driver);
    }

    @Given("^I navigate to Sugar CRM$")
    public void i_navigate_to_Sugar_CRM() throws Throwable {
        navigateToCRMLoginPage(driver);
    }

    @When("^I login using my userid \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void i_login_using_my_userid_and_password(String userid, String password) throws Throwable {
        loginWithUserNameAndPassword(driver, userid, password);
    }

    @And("^I select Medical Coding$")
    public void i_select_Medical_Coding() throws Throwable {
        sugar2387Page.selectMedicalCoding();
    }

    @And("^I enter a Case ID \"([^\"]*)\"$")
    public void i_enter_a_Case_ID(String caseid) throws Throwable {
        sugar2387Page.enterCaseId(caseid);
    }

    @And("^I should be able to create a Mother record and verify the incremental number and interested party$")
    public void i_should_be_able_to_create_a_Mother_record_and_verify_the_incremental_number_and_interested_party() throws Throwable {
        sugar2387Page.createMotherRecordAndVerifyIncrementalNumberAndInterestedParty();
    }

    @Then("^I Logout of Sugar CRM$")
    public void i_Logout_of_Sugar_CRM() throws Throwable {
        logOut(driver);
    }
}
