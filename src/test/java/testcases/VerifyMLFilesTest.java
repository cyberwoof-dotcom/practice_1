package testcases;

import base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import utilities.TestUtil;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class VerifyMLFilesTest extends TestBase {

	@Test(dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 1)
	public void verifyMLFilesTest(Hashtable<String, String> data) throws Exception {
		List<String> listOfErrors = new ArrayList<String>();
		// Run only if the TestCase is set to RUNMODE = Y...
		if (!TestUtil.isTestRunnable("VerifyMLFilesTest", excel)) {
			throw new SkipException("Skipping test: " + "VerifyMLFilesTest");
		}

		// Run only if the Test Data is set to RUNMODE = Y...
		if (!data.get("RUNMODE").equals("Y")) {
			throw new SkipException("Skipping test.");
		}

		// Navigate to the Files URL and select the File for processing...
		driver.get(config.getProperty("ml_filenavgurl"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("memberfilenumber"))));
		type("memberfilenumber", data.get("CASE_ID"));
		Thread.sleep(1500);
		click("casefilenumberselect");

		// Wait until the File Page is displayed...
		WebDriverWait wait0 = new WebDriverWait(driver, 30);
		wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(("casepagelogo")))));

		// Select the Edit Button
		click("selecteditbutton");
		Thread.sleep(500);

		// Verify the File Number...
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(("typerecordlabel")))));
		String ActualStr_1 = (data.get("ML_FILE_ID"));
		listOfErrors.add("verifyMLFilesTest:" + data.get("ML_FILE_ID"));
		String fileName = driver.findElement(By.xpath(OR.getProperty(("mainfilename")))).getAttribute("innerHTML")
				.trim();
		// fileName = fileName.substring(0, 10);
		System.out.println("Sugar - File Number: " + fileName);
		String fName = verifyEquals(ActualStr_1, fileName, "File Number");
		listOfErrors.add("File Number-----" + fName);

		// Verify the Case ID
		String ActualStr_2 = (data.get("CASE_ID"));
		listOfErrors.add("verifyCase:" + data.get("CASE_ID") + "#");
		String caseID = driver.findElement(By.xpath(OR.getProperty(("filecaseid")))).getAttribute("innerHTML").trim();
		caseID = caseID.substring(0, 7);
		System.out.println("Sugar - Case Number: " + caseID);
		String caseid = verifyEquals(ActualStr_2, caseID, "Case Number");
		listOfErrors.add("File Number-----" + caseid);

		// Verify the Appealed Field...
		boolean isChecked;
		String isAppealed;
		// If the data in the spreadsheet is blank then select the data to 0
		String ActualStr_3 = (data.get("APPEALED"));
		if (ActualStr_3 == "") {
			ActualStr_3 = "0";
		}
		// If the checkbox is checked or unchecked set a string value accordingly...
		isChecked = driver.findElement(By.xpath(OR.getProperty("appealedtoname"))).isSelected();
		if (!isChecked) {
			System.out.println("Sugar - Appealed checkbox is not selected");
			isAppealed = "0";
		} else {
			System.out.println("Sugar - Appealed checkbox is checked.");
			isAppealed = "1";
		}
		String isappealed = verifyEquals(ActualStr_3, isAppealed, "Appealed");
		listOfErrors.add("Appealed-----" + isappealed);

		// Verify the Appellant...
		String appellant = null;
		String ActualStr_4 = (data.get("APPELLANT"));
		// If the Appealed checkbox is true... then get the value else set Appellant to
		// false...
		if (isAppealed == "1") {
			appellant = driver.findElement(By.xpath(OR.getProperty(("appellantname")))).getAttribute("innerHTML")
					.trim();
			System.out.println("Sugar - Appellant: " + appellant);
			String appelnt = verifyEquals(ActualStr_4, appellant, "Appellant");
			listOfErrors.add("Appellant-----" + appelnt);
		} else {
			appellant = "";
			System.out.println("Sugar - Appellant: " + appellant);
			String appelnt = verifyEquals(ActualStr_4, appellant, "Appellant");
			listOfErrors.add("Appellant-----" + appelnt);
		}

		// Verify Share Damages...
		String ActualStr_5 = (data.get("CMPA_SHARE_DAMAGES"));
		String shareDamages = driver.findElement(By.xpath(OR.getProperty("cmpasharedamages"))).getAttribute("value")
				.trim();
		System.out.println("Sugar - Shared Damages: " + shareDamages);
		String sharedamage = verifyEquals(ActualStr_5, shareDamages, "Shared Damages");
		listOfErrors.add("CMPA Shared Damages-----" + sharedamage);

		// Total Damages...
		String ActualStr_6 = (data.get("TOTAL_DAMAGES"));
		String totalDamages = driver.findElement(By.xpath(OR.getProperty("totaldamages"))).getAttribute("value").trim();
		System.out.println("Sugar -Total Damages: " + totalDamages);
		String totaldamage = verifyEquals(ActualStr_6, totalDamages, "Total Damages");
		listOfErrors.add("Total Damages-----" + totaldamage);

		// Date Case Created...
		String ActualStr_7 = (data.get("DATE_CREATED"));
		String dateCaseCreated = driver.findElement(By.xpath(OR.getProperty("datecreated"))).getAttribute("innerHTML")
				.trim();
		dateCaseCreated = dateCaseCreated.substring(0, 10);
		System.out.println("Sugar - Creation Date: " + dateCaseCreated);
		String dtcasecreated = verifyEquals(ActualStr_7, dateCaseCreated, "Date Case Created");
		listOfErrors.add("Creation Date-----" + dtcasecreated);

		// Date Case Opened...
		String ActualStr_8 = (data.get("DATE_OPENED"));
		String dateCaseOpened = driver.findElement(By.xpath(OR.getProperty("datecaseoipened"))).getAttribute("value")
				.trim();
		System.out.println("Sugar - Date Case Opened: " + dateCaseOpened);
		String dtcaseopened = verifyEquals(ActualStr_8, dateCaseOpened, "Date Case Opened");
		listOfErrors.add("Date Case Opened-----" + dtcaseopened);

		// Date Case Modified...
		String ActualStr_9 = (data.get("DATE_MODIFIED"));
		String dateCaseMod = driver.findElement(By.xpath(OR.getProperty("datecasemodified"))).getAttribute("innerHTML")
				.trim();
		dateCaseMod = dateCaseMod.substring(0, 10);
		System.out.println("Sugar - Date Case Modified: " + dateCaseMod);
		String dtcasemod = verifyEquals(ActualStr_9, dateCaseMod, "Date Case Modified");
		listOfErrors.add("Case Modified Date-----" + dtcasemod);

		// Date Case Closed...
		String ActualStr_10 = (data.get("DATE_CLOSED"));
		String dateCaseClosed = driver.findElement(By.xpath(OR.getProperty("datecaseclosed"))).getAttribute("value")
				.trim();
		System.out.println("Sugar - Date Case Closed: " + dateCaseClosed);
		String cdtcaseclosed = verifyEquals(ActualStr_10, dateCaseClosed, "Date Case Closed");
		listOfErrors.add("Case Closed Date-----" + cdtcaseclosed);

		// Trial Date...
		String ActualStr_11 = (data.get("TRIAL_DATE"));
		String trialDate = driver.findElement(By.xpath(OR.getProperty("trialdate"))).getAttribute("value").trim();
		System.out.println("Sugar - Trial Date: " + trialDate);
		String trdate = verifyEquals(ActualStr_11, trialDate, "Trial Date");
		listOfErrors.add("Trial Date-----" + trdate);

		// Jury...
		String jurySelect;
		String ActualStr_12 = (data.get("JURY"));
		// If the checkbox is checked or unchecked set a string value accordingly...
		isChecked = driver.findElement(By.xpath(OR.getProperty("jurycheck"))).isSelected();
		if (!isChecked) {
			System.out.println("Sugar - Jury checkbox is not selected");
			jurySelect = "0";
		} else {
			System.out.println("Sugar - Jury checkbox is checked.");
			jurySelect = "1";
		}
		System.out.println("Sugar - Jury: " + jurySelect);
		String jurystat = verifyEquals(ActualStr_12, jurySelect, "Jury");
		listOfErrors.add("Jury-----" + jurystat);

		// Province of Issue...
		String ActualStr_13 = (data.get("PROVINCE_OF_ISSUE"));
		String provinceOfIssue = driver.findElement(By.xpath(OR.getProperty("provinceofissue")))
				.getAttribute("innerHTML").trim();
		System.out.println("Sugar - Province of Issue: " + provinceOfIssue);
		String provofissue = verifyEquals(ActualStr_13, provinceOfIssue, "Province of Issue");
		listOfErrors.add("Province of Issue-----" + provofissue);

		// Case Status
		String ActualStr_14 = (data.get("STATUS"));
		String caseStatus = driver.findElement(By.xpath(OR.getProperty("casestatus"))).getText().trim();
		switch (caseStatus) {
		case ("Open"):
			caseStatus = driver.findElement(By.xpath(OR.getProperty("casestatus"))).getText().trim();
			System.out.println("Sugar - Case Status: " + caseStatus);
			String casestat = verifyEquals(ActualStr_14, caseStatus, "Case Status");
			listOfErrors.add("Case Status-----" + casestat);
			break;
		case ("Closed"):
			caseStatus = driver.findElement(By.xpath(OR.getProperty("casestatus"))).getText().trim();
			System.out.println("Sugar - Case Status: " + caseStatus);
			casestat = verifyEquals(ActualStr_14, caseStatus, "Case Status");
			listOfErrors.add("Case Status-----" + casestat);
			break;
		case ("$ (USD)"):
				caseStatus = driver.findElement(By.xpath(OR.getProperty("casestatus1"))).getText().trim();
				System.out.println("Sugar - Case Status: " + caseStatus);
				casestat = verifyEquals(ActualStr_14, caseStatus, "Case Status");
				listOfErrors.add("Case Status-----" + casestat);
				break;
			    }

		StringBuilder stringBuilder = new StringBuilder();
		for (String error : listOfErrors) {
			stringBuilder.append(error + " \n");
		}

		Files.write(Paths.get("src/test/resources/fileForTestRail.txt"),
				(stringBuilder.toString() + System.lineSeparator()).getBytes(StandardCharsets.UTF_8),
				StandardOpenOption.APPEND);
		// ThreadContextForScenarios.setScenarioContext("verifyFilesTest-"+data.get("ML_FILE_ID"),listOfErrors);
		System.out.println("------------------------------------------------------------------------------------");
		for (String error : listOfErrors) {
			if (error.contains("but")) {
				Assert.fail();
			}
		}
	}
}