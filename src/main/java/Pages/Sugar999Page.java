package Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Sugar999Page extends BasePage {

    WebDriver driver;

    public Sugar999Page() {
        driver = initDriver();
    }

    By searchByName = By.xpath("//input[@placeholder='Search by name...']");
    By selectAFile = By.xpath("(//div[@class='ellipsis_inline'])[2]/a");
    By fileNameElement = By.xpath("(//span[@class='nodata']//div[@class='ellipsis_inline'])[1]");
    By assignedToElement = By.xpath("//span[@class='normal index']//a)[1]");
    By fileStatusElement = By.xpath("(//span[@class='detail']//div[@class='ellipsis_inline'])[1]");
    By dooElement = By.xpath("(//span[@class='nodata']//div[@class='ellipsis_inline'])[2]");
    By cooElement = By.xpath("(//span[@class='nodata']//div[@class='ellipsis_inline'])[4]");
    By pooElement = By.xpath("(//span[@class='nodata']//div[@class='ellipsis_inline'])[3]");
    By testCaseComplete = By.xpath("//li[@id='userActions']//button[@class='btn btn-invisible btn-link dropdown-toggle']");

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

    public void selectMemberFilesMenu() throws Exception {
        Thread.sleep(4000);
        driver.get("https://qlsugqa01.cmpa.org/#FI_Files");
    }

    public void enterFileNumber(String fileNumber) throws Exception {
        Thread.sleep(3000);
        driver.findElement(searchByName).click();
        driver.findElement(searchByName).sendKeys(fileNumber);
    }

    public void selectAFile() throws Exception {
        Thread.sleep(3000);
        driver.findElement(selectAFile).click();
    }

    public void extractAndPrintFileName(String fileNumber) throws Exception {
        Thread.sleep(3000);
        String ActualStr_1 = fileNumber;
        String fileName = driver.findElement(fileNameElement).getAttribute("innerHTML").trim();
        fileName = fileName.substring(0, 10);
        System.out.println("This is the File Name: " + fileName);
        Assert.assertEquals(ActualStr_1, fileName);
        System.out.println("Assert Successful.");
    }

    public void extractAndPrintAssignedTo(String assignedTo) throws Exception {
        Thread.sleep(3000);
        String ActualStr_2 = assignedTo;
        String assignTo = driver.findElement(assignedToElement).getAttribute("innerHTML").trim();
        System.out.println("Assigned To: " + assignTo);
        Assert.assertEquals(ActualStr_2, assignTo);
        System.out.println("Assert Successful.");
    }

    public void extractVerifyStatus(String status) throws Exception {
        String ActualStr_3 = status;
        String fileStatus = driver.findElement(fileStatusElement).getAttribute("innerHTML").trim();
        System.out.println("This is the File Status: " + fileStatus);
        Assert.assertEquals(ActualStr_3, fileStatus);
        System.out.println("Assert Successful.");
    }

    public void enterAndVerifyDOO(String date) {
        String ActualStr_4 = date;
        String DOO = driver.findElement(dooElement).getAttribute("innerHTML").trim();
        System.out.println("This is the DOO: " + DOO);
        Assert.assertEquals(ActualStr_4, DOO);
        System.out.println("Assert Successful.");
    }

    public void enterAndVerifyCOO() {
        String ActualStr_5 = "na";
        String COO = driver.findElement(cooElement).getAttribute("innerHTML").trim();
        System.out.println("This is the COO: " + COO);
        Assert.assertEquals(ActualStr_5, COO);
        System.out.println("Assert Successful.");
    }

    public void extractAndPrintOfOccurence(String poo_code, String poo_name) {
        String POO = driver.findElement(pooElement).getAttribute("innerHTML").trim();
        String ActualStr_6 = poo_code + " - " + poo_name;
        System.out.println("This is the POO: " + POO);
        Assert.assertEquals(ActualStr_6, POO);
        System.out.println("Assert Successful.");
    }

    public void markTestCaseComplete() throws Exception {
        Thread.sleep(1500);
        driver.findElement(testCaseComplete).click();
        driver.findElement(By.xpath("//a[text()='Log Out']")).click();
        System.out.println("Test Completed - Logged-out.");
        driver.quit();
    }

}
