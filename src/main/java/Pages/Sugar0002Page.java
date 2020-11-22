package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Sugar0002Page {
	
    WebDriver driver;

    public Sugar0002Page(WebDriver driver) {
        this.driver = driver;
    }

	By memberLabelLocator = By.xpath("(//div[@class='ellipsis_inline'])[1]");
	By memberNameSearchLocator = By.xpath("//*[@class='search-name']");
	By memberRecordSelect = By.xpath("//td[@data-type='name']");
	By createNewFileLocator = By.xpath("//a[@track='click:FI_Files_create_button']");
	By MemberIconLocator = By.xpath("//*[@class='label label-module label-module-lg label-Contacts']");
	By filePageIconLocator = By.xpath("//*[@class='label label-module label-module-lg label-FI_Files']");
	By clinicalSummaryLocator = By.xpath("//textarea['clinical_summary_c']");
	By assignedToFieldLocator = By.xpath("(//*[@class='select2-chosen']/div)[1]");
	By assignedToLocator =By.xpath(("(//*[@class='select2-input'])[27]"));
	By specialIssueLocator = By.xpath("(//ul[@class='select2-choices'])[1]");
	By saveFileLocator = By.xpath("//a[@class='btn btn-primary']");

	public void selectMemberSearchPage() {
		driver.get("https://plsugppap.cmpa.org/#Contacts");
	}
	
	public void searchMemberName(String membername) throws Exception {
	// Explicit for Member Search Page to be displayed...
	WebDriverWait wait = new WebDriverWait(driver, 15);
	wait.until(ExpectedConditions.visibilityOfElementLocated(memberLabelLocator));
	// Enter and search by Member Name...
	driver.findElement(memberNameSearchLocator).click();
	driver.findElement(memberNameSearchLocator).sendKeys(membername);
	}
	
	public void selectMember(String memberid) throws InterruptedException {
	//Select the Member to process...
		Thread.sleep(5000);
		driver.findElement(memberRecordSelect).click();
		driver.findElement(By.xpath("//a[contains(text(),'" + memberid + "')]")).click();
	}
	
	public void selectCreateNewFile() {
		//Close an Alert if it exists...
		//driver.findElement(By.xpath("//*[@id='alerts']/div/div/button")).click();
		//Check if the Member Icon is displayed at the top of the page...
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(MemberIconLocator));
		//Select the (+) icon to create a new file....
		driver.findElement(createNewFileLocator).click();
	}
	
	public void checkFilePageDisplayed() {
		//Verify if the File Menu is displayed...
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(filePageIconLocator));
	}
	
	public void enterClinicalSummary(String clinicalsummary) {
		//Wait for File Creation Module to be displayed...
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(clinicalSummaryLocator));
		driver.findElement(clinicalSummaryLocator).sendKeys(Keys.TAB);
		driver.findElement(clinicalSummaryLocator).clear();
		driver.findElement(clinicalSummaryLocator).sendKeys(clinicalsummary);
		System.out.println("I got to clincial Summary:" + clinicalsummary );
	}

	public void enterAssignedTo(String assignedto) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(assignedToLocator));
		driver.findElement(assignedToLocator).sendKeys(assignedto);
		driver.findElement(assignedToLocator).sendKeys(Keys.ENTER);
		
	}

	public void enterSpecialIssue(String specialissue) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(specialIssueLocator));
		driver.findElement(specialIssueLocator).click();
		driver.findElement(specialIssueLocator).sendKeys(specialissue);
		driver.findElement(specialIssueLocator).sendKeys(Keys.ENTER);
	}
	
    public void saveFile() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(saveFileLocator));
    	driver.findElement(saveFileLocator).click();
    }
	
}
