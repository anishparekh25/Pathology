package com.w2a.Testcases;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.w2a.Base.Utils;

public class LoginTest extends Utils {

	public static Logger log = Logger.getLogger(LoginTest.class);
//	Utils utility = new Utils();
	
	SoftAssert sa = new SoftAssert();
	
	@Test
	public void bankManagerLoginTest() {
		click("//button[@ng-click='manager()']");
//		sendKeys(OR.getProperty("managerLoginBtn"),"test");
		
		
		log.info("clicked on Bank manager Login button");
		wait.until(ExpectedConditions.urlContains("manager"));
		log.info(driver.getCurrentUrl());
		
		WebElement addCustomer = driver.findElement(By.xpath("//button[@ng-class='btnClass1']"));
		log.info(addCustomer.isDisplayed());
		sa.assertTrue(driver.getCurrentUrl().contains("manager")," url did not change");
		sa.assertTrue(addCustomer.isDisplayed(),"customer button not displayed");
		sa.assertAll();
		log.info("logged in to manger account successfully");
		
}
	
	
	@Test
	public void customerLoginTest() {
		driver.findElement(By.xpath(OR.getProperty("managerLoginBtn"))).click();
		log.info("clicked on Bank manager Login button");
		wait.until(ExpectedConditions.urlContains("manager"));
		log.info(driver.getCurrentUrl());
		Assert.assertTrue(driver.getCurrentUrl().contains("manager"));
		log.info("logged in to manger account successfully");
	}
	
}
