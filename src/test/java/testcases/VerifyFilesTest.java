package testcases;

import Context.ThreadContextForScenarios;
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

public class VerifyFilesTest extends TestBase {


    @Test(dataProviderClass = TestUtil.class, dataProvider = "dp",priority = 1)
    public void verifyFilesTest(Hashtable<String, String> data) throws Exception{
        List<String> listOfErrors = new ArrayList<String>();
        //Run only if the TestCase is set to RUNMODE = Y...
        if (!TestUtil.isTestRunnable("VerifyFilesTest", excel)) {
            throw new SkipException("Skipping test: " + "VerifyFilesTest");
        }

        //Run only if the Test Data is set to RUNMODE = Y...
        if (!data.get("RUNMODE").equals("Y")) {
            throw new SkipException("Skipping test.");
        }

        //Navigate to the Files URL and select the File for processing...
        driver.get(config.getProperty("fi_filenavgurl"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("memberfilenumber"))));
        type("memberfilenumber", data.get("ML_FIELD_ID"));
        Thread.sleep(1000);
        click("memberfilenumberselect");

        //Wait until the File Page is displayed...
        WebDriverWait wait0 = new WebDriverWait(driver, 25);
        wait0.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(("filepagelogo")))));

        //Select the Edit Button
        click("selecteditbutton");
        Thread.sleep(500);

        //Verify the File Number...
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(("mlfilepage")))));
        String ActualStr_1 = (data.get("ML_FILE_ID"));
        listOfErrors.add("verifyFilesTest:"+data.get("ML_FILE_ID")+"#");
        String fileName = driver.findElement(By.xpath(OR.getProperty(("mlfilename")))).getAttribute("innerHTML").trim();
        fileName = fileName.substring(0, 10);
        System.out.println("Sugar - File Number: " + fileName);
        String fName=verifyEquals(ActualStr_1, fileName, "File Name");
        listOfErrors.add("File Name-----"+fName);

        //Verify the Assigned Name on File...
        String ActualStr_2 = (data.get("ASSIGNED_TO"));
        String assignTo = driver.findElement(By.xpath(OR.getProperty("assignedtoname"))).getAttribute("innerHTML").trim();
        System.out.println("Sugar - Assigned To: " + assignTo);
        String assignto=verifyEquals(ActualStr_2, assignTo, "Assign To");
        listOfErrors.add("Assign To-----"+assignto);

        //Verify the Status
        String ActualStr_3 = (data.get("STATUS"));
        String fileStatus = driver.findElement(By.xpath(OR.getProperty(("assignedtostatus")))).getAttribute("innerHTML").trim();
        System.out.println("Sugar - File Status: " + fileStatus);
        String fStatus=verifyEquals(ActualStr_3, fileStatus, "File Status");
        listOfErrors.add("File Status-----"+fStatus);

        String ActualStr_4 = (data.get("DOO"));
        String DOO = driver.findElement(By.xpath(OR.getProperty("assignedtoDOO"))).getAttribute("innerHTML").trim();
        System.out.println("Sugar - DOO: " + DOO);
        String doo=verifyEquals(ActualStr_4, DOO, "DOO");
        listOfErrors.add("DOO-----"+doo);

        String ActualStr_5 = (data.get("POO"));
        String POO = driver.findElement(By.xpath(OR.getProperty("assignedtoPOO"))).getAttribute("innerHTML").trim();
        System.out.println("Sugar - POO: " + POO);
        String poo=verifyEquals(ActualStr_5, POO, "POO");
        listOfErrors.add("POO-----"+poo);

        String ActualStr_6 = (data.get("COO"));
        String COO = driver.findElement(By.xpath(OR.getProperty("assignedtoCOO"))).getAttribute("innerHTML").trim();
        System.out.println("Sugar - COO: " + COO);
        String coo=verifyEquals(ActualStr_6, COO, "COO");
        listOfErrors.add("COO-----"+coo);

        String ActualStr_7 = (data.get("ML_FILE_TYPE"));
        String ML_FILE_TYPE = driver.findElement(By.xpath(OR.getProperty("highseverity"))).getAttribute("innerHTML").trim();
        System.out.println("Sugar - High Severity: " + ML_FILE_TYPE);
        String highSeve= verifyEquals(ActualStr_7, ML_FILE_TYPE, "High Severity");
        listOfErrors.add("High Severity-----"+highSeve);

        String ActualStr_8 = (data.get("CREATION_DATE"));
        String CREATION_DATE = driver.findElement(By.xpath(OR.getProperty("datecreated"))).getAttribute("innerHTML").trim();
        CREATION_DATE = CREATION_DATE.substring(0, 10);
        System.out.println("Sugar - Creation Date: " + ActualStr_8);
        String creationDate=verifyEquals(ActualStr_8, CREATION_DATE, "Creation Date");
        listOfErrors.add("Creation Date-----"+creationDate);

        String ActualStr_9 = (data.get("LAST_UPDATED"));
        String LAST_UPDATED = driver.findElement(By.xpath(OR.getProperty("datelastupd"))).getAttribute("innerHTML").trim();
        LAST_UPDATED = LAST_UPDATED.substring(0, 10);
        System.out.println("Sugar - Last Updated Date: " + LAST_UPDATED);
        String lUpdate=verifyEquals(ActualStr_9, LAST_UPDATED, "Last Updated");
        listOfErrors.add("Last Updated-----"+lUpdate);
        
       	String ActualStr_10 = (data.get("CLOSED_DATE"));
       	String checkStatus = "Closed";
        if (fileStatus.equals(checkStatus)) {
  	    String CLOSED_DATE = driver.findElement(By.xpath(OR.getProperty("dateclosed"))).getAttribute("value").trim();
		System.out.println("Sugar - Closed Date: " + CLOSED_DATE);
       	String cDate=verifyEquals(ActualStr_10, CLOSED_DATE, "Closed Date");
       	listOfErrors.add("Closed Date-----"+cDate);
        }
        else {
        String CLOSED_DATE = "";
		System.out.println("Sugar - Closed Date: " + CLOSED_DATE);
       	String cDate=verifyEquals(ActualStr_10, CLOSED_DATE, "Closed Date");
       	listOfErrors.add("Closed Date-----"+cDate);
        }
          
        String ActualStr_11 = (data.get("DESCR"));
        String DESCR = driver.findElement(By.xpath(OR.getProperty("clinicalsum"))).getAttribute("innerHTML").trim();
        System.out.println("Sugar - Clinical Summary: " + DESCR);
        String cSummary=verifyEquals(ActualStr_11, DESCR, "Clinical Summary");
        listOfErrors.add("Clinical Summary-----"+cSummary);

        String ActualStr_12 = (data.get("GENERAL_COUNSEL"));
        String GENERAL_COUNSEL = driver.findElement(By.xpath(OR.getProperty("gencounsel"))).getText().trim();
        GENERAL_COUNSEL = "????";
        System.out.println("Sugar - General Counsel: " + GENERAL_COUNSEL);
        String genCourse=verifyEquals(ActualStr_12, GENERAL_COUNSEL, "General Counsel");
        listOfErrors.add("General Counsel-----"+genCourse);

        String ActualStr_13 = (data.get("CMA_ID"));
        String CMA_ID = driver.findElement(By.xpath(OR.getProperty("lcaassigned"))).getText().trim();
        System.out.println("Sugar - LCA Assigned: " + CMA_ID);
        String LcaAssigned=verifyEquals(ActualStr_13, CMA_ID, "LCA Assigned");
        listOfErrors.add("LCA Assigned-----"+LcaAssigned);
        
        String ActualStr_14 = (data.get("BUSINESS_UNIT"));
        String BUSINESS_UNIT = driver.findElement(By.xpath(OR.getProperty("busunit"))).getText().trim();
        BUSINESS_UNIT="????";
        System.out.println("Sugar - Business Unit: " + BUSINESS_UNIT);
        String  busUnit = verifyEquals(ActualStr_14, BUSINESS_UNIT, "Business Unit");
        listOfErrors.add("Business Unit-----"+busUnit);

        StringBuilder stringBuilder=new StringBuilder();
        for(String error:listOfErrors){
               stringBuilder.append(error+" \n");
        }

      //Files.write(Paths.get("src/test/resources/fileForTestRail.txt"),(stringBuilder.toString()+ System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
        Files.write(Paths.get("src/test/resources/fileForTestRail.txt"),(stringBuilder.toString()+ System.lineSeparator()).getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        ThreadContextForScenarios.setScenarioContext("verifyFilesTest-"+data.get("ML_FILE_ID"),listOfErrors);
        System.out.println("------------------------------------------------------------------------------------");
        for(String error:listOfErrors){
        if(error.contains("but")){
        Assert.fail();
            }
        }
     }
}