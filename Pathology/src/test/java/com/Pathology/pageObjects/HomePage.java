package com.Pathology.pageObjects;

import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Pathology.generics.GeneralFunctions;

public class HomePage  extends GeneralFunctions {

	public WebDriver driver;
	public static Logger log = Logger.getLogger(HomePage.class);
	
	Random rand  = new Random();

	@FindBy(xpath = "//label[text()='Add tests for patient']/following-sibling::div/input")
	private WebElement addTestsForPatient;
	
	@FindBy(xpath = "//div[@id='patient-test-popup']//li")
	private List<WebElement> addTestsForPatientList;
	
	@FindBy(xpath = "//label[text()='Discount']/following-sibling::div/div/em")
	private WebElement discount;
	
	@FindBy(xpath = "//div[@class='MuiPaper-root MuiMenu-paper MuiPopover-paper"
			+ " MuiPaper-elevation8 MuiPaper-rounded']//li")
	private List<WebElement> discountList;
	
	@FindBy(xpath = "//div[text()='Subtotal']/following-sibling::div")
	private WebElement subtotal;
	
	@FindBy(xpath = "//div[text()='Total']/following-sibling::div")
	private WebElement total;
	
	@FindBy(xpath = "//span[@class='MuiButton-label' and text()='Add']")
	private WebElement toDoAddBtn;
	
	@FindBy(xpath = "//input[@id='outlined-add-todo-input']")
	private WebElement toDoAddField;
	
	@FindBy(xpath = "//span[@class='MuiButton-label']")
	private WebElement toDoAddSaveBtn;
	
	@FindBy(xpath = "(//p)[1]")
	private WebElement toDoAddDashboard;
	
	@FindBy(xpath = "//li[@class='MuiListItem-container']")
	private List<WebElement> remainingWorkList;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyHomePage() {
		log.info("verifying home page...");
		boolean vfyHomePage = driver.getCurrentUrl().contains("https://gor-pathology.web.app/dashboard");
		return vfyHomePage;
	}
	
	public boolean verifyTestCostCalculator() {
		log.info("verifying test cost calculator...");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", total);
		click(addTestsForPatient);
		for(WebElement e:addTestsForPatientList) { 
			if(collectText(e).contains("FBS , FLUORIDE")) {
				click(e);
				break;
			}
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	//	js.executeScript("arguments[0].scrollIntoView(true);", discount);
		click(discount);
		for(WebElement e:discountList) { 
			if(collectText(e).contains("5%")) {
				click(e);
				break;
			}
		}
		log.info("total: " + collectText(total));
		log.info("total after applying discount: " + collectText(subtotal));
		boolean flag = visibilityCheck(total);
		return flag;
	}
	
	public boolean verifyAddToDo() {
		boolean flag = false;
		click(toDoAddBtn);
		sendKeys(toDoAddField, "addToDoNew");
		click(toDoAddSaveBtn);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		click(toDoAddDashboard);
		for(WebElement e:remainingWorkList) {
			if(collectText(e).contains("addToDoNew")) {
				flag = true;
			}
		}
		return flag;
	}
	
	

}
