package Pages;

import cucumber.api.java.Before;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Sugar999Page {

    WebDriver driver;

    public Sugar999Page() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\work\\chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

    public void navigateToCRMLoginPage() throws Exception {
        Thread.sleep(3000);
        driver.get("https://qlsugqa01.cmpa.org/");
    }

    public void selectMemberFilesMenu() throws Exception {
        Thread.sleep(4000);
        driver.get("https://qlsugqa01.cmpa.org/#FI_Files");
    }

    public void enterFileNumber(String fileNumber) throws Exception {
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@placeholder='Search by name...']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Search by name...']")).sendKeys(fileNumber);
    }

    public void selectAFile() throws Exception {
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//div[@class='ellipsis_inline'])[2]/a")).click();
    }

    public void extractAndPrintFileName(String fileNumber) throws Exception {
        Thread.sleep(3000);
        String ActualStr_1 = fileNumber;
        String fileName = driver.findElement(By.xpath(("(//span[@class='nodata']//div[@class='ellipsis_inline'])[1]"))).getAttribute("innerHTML").trim();
        fileName = fileName.substring(0, 10);
        System.out.println("This is the File Name: " + fileName);
        Assert.assertEquals(ActualStr_1, fileName);
        System.out.println("Assert Successful.");
    }

    public void extractAndPrintAssignedTo(String assignedTo) throws Exception {
        Thread.sleep(3000);
        String ActualStr_2 = assignedTo;
        String assignTo = driver.findElement(By.xpath(("(//span[@class='normal index']//a)[1]"))).getAttribute("innerHTML").trim();
        System.out.println("Assigned To: " + assignTo);
        Assert.assertEquals(ActualStr_2, assignTo);
        System.out.println("Assert Successful.");
    }

    public void extractVerifyStatus(String status) throws Exception {
        String ActualStr_3 = status;
        String fileStatus = driver.findElement(By.xpath(("(//span[@class='detail']//div[@class='ellipsis_inline'])[1]"))).getAttribute("innerHTML").trim();
        System.out.println("This is the File Status: " + fileStatus);
        Assert.assertEquals(ActualStr_3, fileStatus);
        System.out.println("Assert Successful.");
    }

    public void enterAndVerifyDOO(String date) {
        String ActualStr_4 = date;
        String DOO = driver.findElement(By.xpath(("(//span[@class='nodata']//div[@class='ellipsis_inline'])[2]"))).getAttribute("innerHTML").trim();
        System.out.println("This is the DOO: " + DOO);
        Assert.assertEquals(ActualStr_4, DOO);
        System.out.println("Assert Successful.");
    }

    public void enterAndVerifyCOO() {
        String ActualStr_5 = "na";
        String COO = driver.findElement(By.xpath(("(//span[@class='nodata']//div[@class='ellipsis_inline'])[4]"))).getAttribute("innerHTML").trim();
        System.out.println("This is the COO: " + COO);
        Assert.assertEquals(ActualStr_5, COO);
        System.out.println("Assert Successful.");
    }

    public void extractAndPrintOfOccurence(String poo_code, String poo_name) {
        String POO = driver.findElement(By.xpath(("(//span[@class='nodata']//div[@class='ellipsis_inline'])[3]"))).getAttribute("innerHTML").trim();
        String ActualStr_6 = poo_code + " - " + poo_name;
        System.out.println("This is the POO: " + POO);
        Assert.assertEquals(ActualStr_6, POO);
        System.out.println("Assert Successful.");
    }

    public void markTestCaseComplete() throws Exception {
        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[@id='userActions']//button[@class='btn btn-invisible btn-link dropdown-toggle']")).click();
        driver.findElement(By.xpath("//a[text()='Log Out']")).click();
        System.out.println("Test Completed - Logged-out.");
    }

}
