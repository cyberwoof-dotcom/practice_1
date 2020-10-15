package CucumberFramework.steps;

import Beans.Sugar9999;
import Pages.Sugar999Page;
import cucumber.api.java.en.Given;

public class sugar_9999 {

    Sugar999Page sugar999Page;

    public sugar_9999() {
        sugar999Page = new Sugar999Page();
    }

    @Given("^I navigate to the SugarCRM Login Page$")
    public void i_navigate_to_the_SugarCRM_Login_Page() throws Throwable {
        sugar999Page.navigateToCRMLoginPage();
    }

    @Given("^I select the Member Files Menu$")
    public void i_select_the_Member_Files_Menu() throws Throwable {
        sugar999Page.selectMemberFilesMenu();
    }

    @Given("^I enter a File Number,select the file,extract and print filename,assignedto,verifyStatus,DOO,COO,POO for fileNumber \"([^\"]*)\"$")
    public void i_enter_a_File_NumberAndPerformAllRequireAction(String uniqueValue) throws Throwable {
        Sugar9999 sugar9999 = Sugar9999.getCSVData(uniqueValue);
        sugar999Page.enterFileNumber(sugar9999.getFileNumber());
        sugar999Page.selectAFile();
        sugar999Page.extractAndPrintFileName(sugar9999.getFileNumber());
        sugar999Page.extractAndPrintAssignedTo(sugar9999.getAssignedTo());
        sugar999Page.extractVerifyStatus(sugar9999.getStatus());
        sugar999Page.enterAndVerifyDOO(sugar9999.getDateOfOccurrence());
        sugar999Page.enterAndVerifyCOO();
        sugar999Page.extractAndPrintOfOccurence(sugar9999.getPoo_Code(), sugar9999.getPoo_Name());
        sugar999Page.markTestCaseComplete();
    }

}
