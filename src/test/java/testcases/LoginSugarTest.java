package testcases;

import Context.ThreadContextForScenarios;
import base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class LoginSugarTest extends TestBase {
	private final String FILE_NAME = "src/test/resources/fileForTestRail.txt";

	@BeforeTest
	public void threadContextInit() throws IOException {
		ThreadContextForScenarios.getInstance();
		Path newFilePath = Paths.get(FILE_NAME);
		Files.deleteIfExists(newFilePath);
		Files.createFile(newFilePath);
	}

@Test
public void loginSugarTest() throws Exception {
	//Login to SugarCRM...  
	type("usernameprop",config.getProperty("uid"));
	type("passwordprop",config.getProperty("pwd"));
	click("loginbuttonprop");
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("mainmenucube"))));
	Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("mainmenucube"))),"Login Failed.");
	log.debug("Login Successful.");
	Reporter.log("Login Successful.");
	String value="loginSugarTest"+"LoginSugarTest"+"#"+"  ";
	ThreadContextForScenarios.setScenarioContext("loginSugarTest-"+"LoginSugarTest","Login Successful.");
	Files.write(Paths.get(FILE_NAME),(value+ System.lineSeparator()).getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
	}
}



