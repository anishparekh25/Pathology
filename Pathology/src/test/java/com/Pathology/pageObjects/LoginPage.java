package com.Pathology.pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Pathology.generics.GeneralFunctions;

public class LoginPage extends GeneralFunctions {

	public WebDriver driver;
	public static Logger log = Logger.getLogger(LoginPage.class);
	
	
	private String username = "test@kennect.io";
	private String pass = "Qwerty@1234";

	@FindBy(xpath = "//input[@name='email']")
	private WebElement id;

	@FindBy(xpath = "//input[@name='password']")
	private WebElement password;

	@FindBy(xpath = "(//span[@class='MuiButton-label'])[1]")
	private WebElement signIn;

	@FindBy(xpath = "//div[@class='MuiAlert-message']")
	private WebElement errorMsg;
	
	@FindBy(xpath = "(//span[@class='MuiIconButton-label'])[2]/div")
	private WebElement beforeLogout;

	@FindBy(xpath = "//span[@class='MuiButton-label' and text()='Sign out of Lab']")
	private WebElement logout;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean positiveLoginTest() {
		log.info("passing correct id and password...");
		sendKeys(id, username);
		sendKeys(password, pass);
		click(signIn);
		try {
			Thread.sleep(3000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		boolean plTest = driver.getCurrentUrl().equals("https://gor-pathology.web.app/dashboard");
		log.info("Sign-In is Done.");
		return plTest;
	}

	public boolean negativeLoginTest() {
		log.info("passing empty username and empty password...");
		sendKeys(id, "");
		sendKeys(password, "");
		click(signIn);
		log.info("passing correct username and incorrect password...");
		sendKeys(id, username);
		sendKeys(password, "Testing");
		click(signIn);
		log.info("passing incorrect username and correct password...");
		sendKeys(id, "test@gmail.com");
		sendKeys(password, pass);
		click(signIn);
		log.info("passing incorrect username and incorrect password...");
		sendKeys(id, "Testing@gmail.com");
		sendKeys(password, "Testing");
		click(signIn);
		boolean nlTest = visibilityCheck(errorMsg);
		if(nlTest) {
		log.info("error message is: " + collectText(errorMsg));
		log.info("Negative SignIn is Verified.");
		}
		try {
			Thread.sleep(3000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return nlTest;
	}

	public boolean signOut() {
		try {
			Thread.sleep(3000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		click(beforeLogout);
		click(logout);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("LogOut is Done.");
		boolean logoutVfy = visibilityCheck(signIn);
		return logoutVfy;
	}

}
