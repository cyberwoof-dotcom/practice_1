package CucumberFramework.steps;

import Pages.BasePage;
import Pages.Sugar2154Page;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

public class sugar_2154 extends BasePage {


    Sugar2154Page sugar2154Page;
    WebDriver driver;

    public sugar_2154() {
        driver = initDriver();
        sugar2154Page = new Sugar2154Page(driver);
    }


    @Given("^I navigate to the SugarCRM QA Website$")
    public void i_navigate_to_the_SugarCRM_QA_Website() throws Exception {
        navigateToCRMLoginPage(driver);
    }


    @When("^I login with my username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void i_login_with_my_username_and_password(String username, String password) {
        loginWithUserNameAndPassword(driver, username, password);
    }

    @And("^I select the Member Events Subpanel$")
    public void i_select_the_Member_Events_Subpanel() {
        sugar2154Page.selectMemberEventsPanel();
    }

    @And("^I select the Create button$")
    public void i_select_the_Create_button() {
        sugar2154Page.selectCreateButton();
    }

    @And("^I should be able to select the Event Type \"([^\"]*)\" End Membership$")
    public void i_should_be_able_to_select_the_Event_Type_End_Membership(String eventtype) {
        sugar2154Page.selectEventTypeEndMemberShip(eventtype);
    }

    @And("^I should be able to select the \"([^\"]*)\" Reason$")
    public void i_should_be_able_to_select_the_Reason(String reason) throws Exception {
        sugar2154Page.selectReason(reason);
    }

    @And("^I should be able to select an \"([^\"]*)\" Effective Date$")
    public void i_should_be_able_to_select_an_Effective_Date(String effdate) throws Exception {
        sugar2154Page.selectEffectiveDate(effdate);
    }

    @And("^I should be able to select a \"([^\"]*)\" Member$")
    public void i_should_be_able_to_select_a_Member(String member) throws Exception {
        sugar2154Page.selectMember(member);
    }

    @Then("^I should be able to select the Save button and Save the data$")
    public void i_should_be_able_to_select_the_Save_button_and_Save_the_data() {
        sugar2154Page.saveTheDataAndDate();
    }

    @And("^I will logout of SugarCRM$")
    public void i_will_logout_of_SugarCRM() throws Exception {
        logOut(driver);
    }
}

