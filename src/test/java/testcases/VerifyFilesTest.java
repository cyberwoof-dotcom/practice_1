package testcases;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.Test;

import Enums.Constants;
import TestRail.TestResult;


import base.TestBase;
import utilities.TestUtil;

public class VerifyFilesTest extends TestBase {
	
@Test(dataProviderClass=TestUtil.class,dataProvider="dp")
public void verifyFilesTest(Hashtable<String,String>data) throws Exception{
	
	//Run only if the TestCase is set to RUNMODE = Y...
	if(!TestUtil.isTestRunnable("VerifyFilesTest",excel)) {
		throw new SkipException("Skipping test: " + "VerifyFilesTest");
	}
	
	//Run only if the Test Data is set to RUNMODE = Y...
	if (!data.get("RUNMODE").equals("Y")) {
	  throw new SkipException("Skipping test.");
	}
	
	//Navigate to the Files URL and select the File for processing...
	driver.get(config.getProperty("filenavgurl"));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("memberfilenumber"))));
	type("memberfilenumber",data.get("ML_FILE_ID"));
	Thread.sleep(1000);
	click("memberfilenumberselect");
	
	//Wait until the File Page is displayed...
	WebDriverWait wait0 = new WebDriverWait(driver,25);
	wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(("filepagelogo")))));
		
	//Select the Edit Button
	click("selecteditbutton");
	Thread.sleep(500);
	
	//Verify the File Number...
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(("mlfilepage")))));
	String ActualStr_1 = (data.get("ML_FILE_ID"));
	String fileName = driver.findElement(By.xpath(OR.getProperty(("mlfilename")))).getAttribute("innerHTML").trim();
	fileName = fileName.substring(0,10);
	System.out.println("Sugar - File Number: " + fileName);
	
	verifyEquals(ActualStr_1, fileName, "File Name");

	//Verify the Assigned Name on File...
	String ActualStr_2 = (data.get("ASSIGNED_TO"));
	String assignTo = driver.findElement(By.xpath(OR.getProperty("assignedtoname"))).getAttribute("innerHTML").trim();
	System.out.println("Sugar - Assigned To: " + assignTo);

	verifyEquals(ActualStr_2, assignTo,"Assign To");
	
	//Verify the Status
	String ActualStr_3 = (data.get("STATUS"));
	String fileStatus = driver.findElement(By.xpath(OR.getProperty(("assignedtostatus")))).getAttribute("innerHTML").trim();
	System.out.println("Sugar - File Status: " + fileStatus);
	
	verifyEquals(ActualStr_3, fileStatus,"File Status");
	
	String ActualStr_4 = (data.get("DOO"));
	String DOO = driver.findElement(By.xpath(OR.getProperty("assignedtoDOO"))).getAttribute("innerHTML").trim();
	System.out.println("Sugar - DOO: " + DOO);
	
	verifyEquals(ActualStr_4, DOO,"DOO");

	String ActualStr_5 = (data.get("POO"));
	String POO = driver.findElement(By.xpath(OR.getProperty("assignedtoPOO"))).getAttribute("innerHTML").trim();
	System.out.println("Sugar - POO: " + POO);
	
	verifyEquals(ActualStr_5, POO,"POO");
	
	String ActualStr_6 = (data.get("COO"));
	String COO = driver.findElement(By.xpath(OR.getProperty("assignedtoCOO"))).getAttribute("innerHTML").trim();
	System.out.println("Sugar - COO: " + COO);

	verifyEquals(ActualStr_6, COO, "COO");

	String ActualStr_7 = (data.get("ML_FILE_TYPE"));
	String ML_FILE_TYPE = driver.findElement(By.xpath(OR.getProperty("highseverity"))).getAttribute("innerHTML").trim();
	System.out.println("Sugar - High Severity: " + ML_FILE_TYPE);
	
	verifyEquals(ActualStr_7, ML_FILE_TYPE,"High Severity");

	String ActualStr_8 = (data.get("CREATION_DATE"));
	String CREATION_DATE = driver.findElement(By.xpath(OR.getProperty("datecreated"))).getAttribute("innerHTML").trim();
	CREATION_DATE = CREATION_DATE.substring(0,10);
	System.out.println("Sugar - Creation Date: " + ActualStr_8);
	
	verifyEquals(ActualStr_8, CREATION_DATE,"Creation Date");
	
	
	String ActualStr_9 = (data.get("LAST_UPDATED"));
	String LAST_UPDATED = driver.findElement(By.xpath(OR.getProperty("datelastupd"))).getAttribute("innerHTML").trim();
	System.out.println("Sugar - Last Updated Date: " + LAST_UPDATED); 
	LAST_UPDATED = LAST_UPDATED.substring(0,10);
	System.out.println("Sugar - Last Updated Date: " + LAST_UPDATED); 
	
	verifyEquals(ActualStr_9, LAST_UPDATED,"Last Updated");
	
	String ActualStr_10 = (data.get("CLOSED_DATE"));
	String CLOSED_DATE = driver.findElement(By.xpath(OR.getProperty("dateclosed"))).getAttribute("value").trim();
	System.out.println("Sugar - Closed Date: " + CLOSED_DATE);
	
	verifyEquals(ActualStr_10, CLOSED_DATE, "Closed Date");
	
	String ActualStr_11 = (data.get("DESCR"));
	String DESCR = driver.findElement(By.xpath(OR.getProperty("clinicalsum"))).getAttribute("innerHTML").trim();
	System.out.println("Sugar - Clinical Summary: " + DESCR);
	
	verifyEquals(ActualStr_11, DESCR,"Clinical Summary");
		
	String ActualStr_12 = (data.get("GENERAL_COUNSEL"));
	String GENERAL_COUNSEL = driver.findElement(By.xpath(OR.getProperty("gencounsel"))).getText().trim();
	System.out.println("Sugar - General Counsel: " + GENERAL_COUNSEL);
	
	verifyEquals(ActualStr_12, GENERAL_COUNSEL,"General Counsel");
	
	String ActualStr_13 = (data.get("CMA_ID"));
	String CMA_ID = driver.findElement(By.xpath(OR.getProperty("lcaassigned"))).getText().trim();
	System.out.println("Sugar - LCA Assigned: " + CMA_ID);
	
	verifyEquals(ActualStr_13, CMA_ID,"LCA Assigned");
	
    System.out.println("------------------------------------------------------------------------------------");    
    
    TestResult testResult = new TestResult();
    testResult.populateTestResult(Constants.TEST_RAIL_PROJECT_NAME.value, "DataConversionValidation", data.get("ML_FILE_ID"), "DataConversionValidation");
}
}