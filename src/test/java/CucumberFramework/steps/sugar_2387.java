package CucumberFramework.steps;

import java.util.List;
import java.util.concurrent.TimeUnit;

import Pages.Sugar2387Page;
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

    Sugar2387Page sugar2387Page;

    public sugar_2387() {
        sugar2387Page = new Sugar2387Page();
    }

    @Given("^I navigate to Sugar CRM$")
    public void i_navigate_to_Sugar_CRM() throws Throwable {
        sugar2387Page.navigateToCRMLoginPage();
    }

    @When("^I login using my userid \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void i_login_using_my_userid_and_password(String userid, String password) throws Throwable {
        sugar2387Page.loginWithUserNameAndPassword(userid, password);
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
        sugar2387Page.logOut();
    }
}
