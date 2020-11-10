package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Sugar2154Page extends BasePage {

    WebDriver driver;

    public Sugar2154Page() {
        driver = initDriver();
    }

    By eventTypeMemberShip = By.xpath("//*[@id=s2id_autogen1]/a/span[2]");
    By eventTypeMemberShip1 = By.xpath("//*[@id=\"s2id_autogen1\"]/a/span[2]");
    By eventTypeMemberShipSearch = By.xpath("//*[@id=\'s2id_autogen2_search\']");
    By reasonElement = By.xpath("//*[@id=\"s2id_autogen3\"]/a/span[2]");
    By reasonSearch = By.xpath("//*[@id=\'s2id_autogen4_search\']");
    By effective_Date = By.name("effective_date_c");
    By selectMemberElement = By.xpath("//span[contains(text(),'Select Member...')]");
    By selectMemberElement1 = By.xpath("//*[@id='select2-drop']/ul[2]/li/div");
    By selectMemberTable = By.xpath(("//table[@class='table table-striped dataTable search-and-select']/tbody/tr[1]"));
    By saveButton = By.xpath("//a[@name='save_button']");


    public void selectMemberEventsPanel() {
        driver.get("https://qlsugqa01.cmpa.org/#CM_EV_MemberEvents");
    }

    public void selectCreateButton() {
        driver.get("https://qlsugqa01.cmpa.org/#CM_EV_MemberEvents/create");
    }

    public void selectEventTypeEndMemberShip(String eventtype) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(eventTypeMemberShip));
        driver.findElement(eventTypeMemberShip1).click();
        driver.findElement(eventTypeMemberShipSearch).sendKeys(eventtype);
        driver.findElement(eventTypeMemberShipSearch).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(eventTypeMemberShipSearch).sendKeys(Keys.ENTER);
    }

    public void selectReason(String reason) throws Exception {
        Thread.sleep(2000);
        driver.findElement(reasonElement).click();
        driver.findElement(reasonSearch).sendKeys(reason);
        driver.findElement(reasonSearch).sendKeys(Keys.ENTER);
    }

    public void selectEffectiveDate(String effdate) throws Exception {
        Thread.sleep(600);
        driver.findElement(effective_Date).click();
        Thread.sleep(600);
        driver.findElement(effective_Date).sendKeys(effdate);
    }

    public void selectMember(String member) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(selectMemberElement));
        driver.findElement(selectMemberElement).click();
        driver.findElement(selectMemberElement1).click();
        Thread.sleep(2000);
        driver.findElement(selectMemberTable).click();
    }

    public void saveTheDataAndDate() {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(saveButton));
        driver.findElement(saveButton).click();
    }

}
