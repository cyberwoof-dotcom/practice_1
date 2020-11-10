package Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Sugar2387Page extends BasePage {
    WebDriver driver;

    public Sugar2387Page() {
        driver = initDriver();
    }

    By caseNameSearch = By.xpath("//input[@class='search-name']");
    By motherTableCurrentCount = By.xpath("//h4[contains(text(),'Mother')]/following-sibling::span");
    By motherRecordCreate = By.xpath("//h4[contains(text(),'Mother')]/following::a[1]");
    By motherScreenData = By.xpath("//input[@name='cmpa_gestationweeks_c']");
    By interestedPartyElement = By.xpath("//span[@class='detail disabled']/div");
    By motherRecordNewCount = By.xpath("//h4[contains(text(),'Mother')]/following-sibling::span");
    By motherTableCondensed = By.xpath(("//table[@class='table table-striped table-bordered table-condensed']"));


    public void selectMedicalCoding() {
        // Write code here that turns the phrase above into concrete actions
        driver.get("https://qlsugqa01.cmpa.org/#Quotes");
    }

    public void enterCaseId(String caseid) throws Exception {
        // Write code here that turns the phrase above into concrete actions
        //Explicit Wait the Medical Coding Page to be Displayed
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(caseNameSearch));
        //Enter and search by CaseID
        driver.findElement(caseNameSearch).click();
        driver.findElement(caseNameSearch).sendKeys(caseid);
        Thread.sleep(3500);
        driver.findElement(By.xpath("//a[contains(text(),'" + caseid + "')]")).click();
    }

    public void createMotherRecordAndVerifyIncrementalNumberAndInterestedParty() throws Exception {
        // Write code here that turns the phrase above into concrete actions
        int countMotherRecordBefore;
        int countMotherRecordAfter;

        //Explicit Wait before you click on Create a Mother Record
        Thread.sleep(4000);

        //Get the Count of Current in the Mother Data Table convert to Integer for Assertion
        String beforeSave = driver.findElement(motherTableCurrentCount).getText();

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
        driver.findElement(motherRecordCreate).click();

        //Add Data to the Mother Screen
        WebDriverWait wait_1 = new WebDriverWait(driver, 15);
        wait_1.until(ExpectedConditions.visibilityOfElementLocated(motherScreenData));

        //Get the Interested Party from the Mother Record and Extract the Case ID & Interested Party
        //String interestedParty = driver.findElement(By.xpath(("(//span[@class='normal index']//div[@class='ellipsis_inline'])[8]"))).getText();
        String interestedParty = driver.findElement(interestedPartyElement).getText();
        String subCaseID = interestedParty.substring(0, 9).trim();
        String subParty = interestedParty.substring(11).trim();

        //Save the Data in the Mother Module
        driver.findElement(By.xpath("//a[@class='btn btn-primary']")).click();

        //Get the New Record Count of Mother Records and convert to Integer..
        Thread.sleep(3500);
        String afterSave = driver.findElement(motherRecordNewCount).getText();
        char c2 = afterSave.charAt(1);
        countMotherRecordAfter = Integer.parseInt(String.valueOf(c2));
        WebElement table = driver.findElement(motherTableCondensed);
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        //Check for the CaseID and match it to the Case Interested Party
        for (int i = 1; i <= rows.size() - 1; i++) {
            String caseID = driver.findElement(By.xpath("//table[@class='table table-striped table-bordered table-condensed']/tbody/tr[" + i + "]/td[2]")).getText();
            if (caseID.equals(subCaseID)) {
                String intPartyName = driver.findElement(By.xpath("//table[@class='table table-striped table-bordered table-condensed']/tbody/tr[" + i + "]/td[6]")).getText();
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

}