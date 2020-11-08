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

    public void navigateToCRMLoginPage() throws Exception {
        Thread.sleep(3000);
        driver.get("https://qlsugqa01.cmpa.org/");
    }

    public void loginWithUserNameAndPassword(String userid, String password) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']")));

        //Login to Sugar...
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(userid);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//a[@name='login_button']")).click();
    }

    public void selectMemberEventsPanel() {
        driver.get("https://qlsugqa01.cmpa.org/#CM_EV_MemberEvents");
    }

    public void selectCreateButton() {
        driver.get("https://qlsugqa01.cmpa.org/#CM_EV_MemberEvents/create");
    }

    public void selectEventTypeEndMemberShip(String eventtype) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"s2id_autogen1\"]/a/span[2]")));
        driver.findElement(By.xpath("//*[@id=\"s2id_autogen1\"]/a/span[2]")).click();
        driver.findElement(By.xpath("//*[@id=\'s2id_autogen2_search\']")).sendKeys(eventtype);
        driver.findElement(By.xpath("//*[@id=\'s2id_autogen2_search\']")).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(By.xpath("//*[@id=\'s2id_autogen2_search\']")).sendKeys(Keys.ENTER);
    }

    public void selectReason(String reason) throws Exception {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"s2id_autogen3\"]/a/span[2]")).click();
        driver.findElement(By.xpath("//*[@id=\'s2id_autogen4_search\']")).sendKeys(reason);
        driver.findElement(By.xpath("//*[@id=\'s2id_autogen4_search\']")).sendKeys(Keys.ENTER);
    }

    public void selectEffectiveDate(String effdate) throws Exception {
        Thread.sleep(600);
        driver.findElement(By.name("effective_date_c")).click();
        Thread.sleep(600);
        driver.findElement(By.name("effective_date_c")).sendKeys(effdate);
    }

    public void selectMember(String member) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Select Member...')]")));
        driver.findElement(By.xpath("//span[contains(text(),'Select Member...')]")).click();
        driver.findElement(By.xpath("//*[@id='select2-drop']/ul[2]/li/div")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(("//table[@class='table table-striped dataTable search-and-select']/tbody/tr[1]"))).click();
    }

    public void saveTheDataAndDate() {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@name='save_button']")));
        driver.findElement(By.xpath("//a[@name='save_button']")).click();
    }

    public void logOut() throws Exception {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[@id='userActions']//button[@class='btn btn-invisible btn-link dropdown-toggle']")).click();
        driver.findElement(By.xpath("//a[text()='Log Out']")).click();
    }

}
