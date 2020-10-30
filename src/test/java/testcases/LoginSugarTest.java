package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import base.TestBase;

public class LoginSugarTest extends TestBase {
	
@Test
	
public void loginSugarTest() throws InterruptedException {
	//Login to SugarCRM...  
	type("usernameprop",config.getProperty("uid"));
	type("passwordprop",config.getProperty("pwd"));
	click("loginbuttonprop");
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("mainmenucube"))));
	Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("mainmenucube"))),"Login Failed.");
	log.debug("Login Successful.");
	Reporter.log("Login Successful.");
	}
}



