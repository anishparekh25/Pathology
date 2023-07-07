package com.Pathology.pageObjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Pathology.generics.GeneralFunctions;

public class PatientsPage  extends GeneralFunctions {

	public WebDriver driver;
	public static Logger log = Logger.getLogger(PatientsPage.class);

	@FindBy(xpath = "//div[@class='MuiListItemText-root']/span[text()='Patients']")
	private WebElement patients;
	
	@FindBy(xpath = "(//p)[1]")
	private WebElement dashboard;
	
	@FindBy(xpath = "(//button[@type='submit'])[1]")
	private WebElement addPatient;
	
	@FindBy(xpath = "//input[@placeholder='Search']")
	private WebElement search;
	
	@FindBy(xpath = "(//span[@class='material-icons MuiIcon-root MuiIcon-fontSizeSmall'])[2]")
	private WebElement clearSearch;
	
	@FindBy(xpath = "//tr[@class='MuiTableRow-root MuiTableRow-hover']")
	private List<WebElement> listOfPatients;
	
	@FindBy(xpath = "//input[@name='name']")
	private WebElement patientName;
	
	@FindBy(xpath = "//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-12']")
	private WebElement nameOnPatientProfile;
	
	@FindBy(xpath = "//p[text()='Patient name is required']")
	private WebElement patientNameError;
	
	@FindBy(xpath = "//input[@name='email']")
	private WebElement patientEmail;
	
	@FindBy(xpath = "//input[@name='phone']")
	private WebElement patientPhone;
	
	@FindBy(xpath = "//p[text()='Required']")
	private WebElement patientPhoneError;
	
	@FindBy(xpath = "//input[@name='height']")
	private WebElement height;
	
	@FindBy(xpath = "//p[contains(text(),'height')]")
	private WebElement heightError;
	
	@FindBy(xpath = "//input[@name='weight']")
	private WebElement weight;
	
	@FindBy(xpath = "//p[contains(text(),'weight')]")
	private WebElement weightError;
	
	@FindBy(xpath = "//input[@name='age']")
	private WebElement age;
	
	@FindBy(xpath = "//p[contains(text(),'age')]")
	private WebElement ageError;
	
	@FindBy(xpath = "//div[@id='mui-component-select-gender']")
	private WebElement gender;
	
	@FindBy(xpath = "(//ul)[2]/li")
	private List<WebElement> genderList;
	
	@FindBy(xpath = "//span[text()='back']")
	private WebElement backBtn;
	
	@FindBy(xpath = "//span[text()='cancel']")
	private WebElement cancelBtn;
	
	@FindBy(xpath = "(//span[text()='General Details'])[2]")
    private WebElement generateDetailsBtn;
	
	@FindBy(xpath = "(//span[text()='Add Patient'])[1]")
    private WebElement addPatientBtn;
	
	@FindBy(xpath = "(//span[text()='Add Tests'])[2]")
    private WebElement addTestBtn;
	
	@FindBy(xpath = "//span[text()='Update Patient']")
    private WebElement updatePatientBtn;
	
	@FindBy(xpath = "//span[@class='material-icons MuiIcon-root']")
    private WebElement addEquipmentBtn;
	
	@FindBy(xpath = "(//input[@class='MuiSelect-nativeInput'])[3]")
    private WebElement equipmentName;
	
	@FindBy(xpath = "(//ul)[2]/li")
    private List<WebElement> equipmentList;
	
	@FindBy(xpath = "//input[@placeholder='Required']")
    private WebElement required;
	
	@FindBy(xpath = "(//span[@class='material-icons MuiIcon-root'])[3]")
    private WebElement actionsCrossorDelete;
	
	@FindBy(xpath = "(//span[@class='material-icons MuiIcon-root'])[2]")
    private WebElement actionsSaveOrEdit;
	
	//calculator elements
	@FindBy(xpath = "//label[text()='Add tests for patient']/following-sibling::div/div")
	private WebElement addTestsForPatient;
	
	@FindBy(xpath = "//div[@id='patient-test-popup']//li")
	private List<WebElement> addTestsForPatientList;
	
	@FindBy(xpath = "//label[text()='Discount']/following-sibling::div/div")
	private WebElement discount;
	
	@FindBy(xpath = "//div[@class='MuiPaper-root MuiMenu-paper MuiPopover-paper"
			+ " MuiPaper-elevation8 MuiPaper-rounded']//li")
	private List<WebElement> discountList;
	
	@FindBy(xpath = "//div[text()='Subtotal']/following-sibling::div")
	private WebElement subtotal;
	
	@FindBy(xpath = "//div[text()='Total']/following-sibling::div")
	private WebElement total;
	
	@FindBy(xpath = "(//input[@aria-autocomplete='list'])[2]")
	private WebElement selectLabs;
	
	@FindBy(xpath = "//ul[@id='patient-tests-labs-popup']//li")
	private List<WebElement> selectLabsList;
	
	@FindBy(xpath = "//input[@name='doctor_name']")
	private WebElement selectDoc;
	
	@FindBy(xpath = "//ul[@id='mui-409-popup']//li")
	private List<WebElement> selectDocList;
	
	@FindBy(xpath = "//div[@id='mui-component-select-doctor_commission']")
	private WebElement selectDocComission;
	
	@FindBy(xpath = "//ul[@class='MuiList-root MuiMenu-list MuiList-padding']/li")
	private List<WebElement> selectDocComissionList;
	
	@FindBy(xpath = "//span[text()='Yes Delete']")
	private WebElement deletePatient;
	
	@FindBy(xpath = "//span[text()='Edit Patient Info']")
	private WebElement editPatientInfo;
			
	public PatientsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyPatientsPage() {
		log.info("verifying patients page...");
		click(patients);
		boolean vfyPatientsPage = driver.getCurrentUrl().contains("https://gor-pathology.web.app/patients");
		return vfyPatientsPage;
	}
	
	public boolean addPatients() {
		boolean flag = false;
		boolean f0 = patientContactDetails();
		boolean f1 = secondaryDetails();
		log.info("verifying adding a patient, its tests and then deleting it...");
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
		click(discount);
		for(WebElement e:discountList) { 
			if(collectText(e).contains("5%")) {
				click(e);
				break;
			}
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		click(selectLabs);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(WebElement e:selectLabsList) { 
			if(collectText(e).contains("FBS , FLUORIDE")) {
				click(e);
				break;
			}
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", addPatientBtn);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	     clickWithJS(addEquipmentBtn);
	     try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	 /*    click(equipmentName);
	     try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {        
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	     for(WebElement e:equipmentList) { 
				if(collectText(e).contains("RED TUBE")) {
			  		click(e);                         //not working    
					break;
				}
			}*/
		clickWithJS(actionsSaveOrEdit);
		clickWithJS(addPatientBtn); 
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		sendKeys(search, "name1");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		 for(WebElement e:listOfPatients) { 
				if(collectText(e).contains("name1")) {
					click(e);
					break;
				}
			}
		 try {
				Thread.sleep(2000);
			} catch (InterruptedException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		 boolean f2 = collectText(nameOnPatientProfile).contains("name1");
		  click(editPatientInfo);
		 sendKeys(patientName, "name1modify");
		 clickWithJS(updatePatientBtn);
		 boolean f3 = collectText(nameOnPatientProfile).contains("name1modify");
		 js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		 clickWithJS(deletePatient);        
		 try {
			Thread.sleep(4000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	if(f0==true & f1==true & f2==true & f3==true) {
		flag = true;
	}
		 return flag;
		// return true;
		}
	
	public boolean patientContactDetails() {
		boolean flag = false;
		log.info("verifying patient contact details scenario...");
		click(patients);
		click(addPatient);
		click(cancelBtn);
		click(addPatient);
	//	sendKeys(patientName, " ");
		click(patientName);
		sendKeys(patientEmail, " ");
		sendKeys(patientPhone, " ");
		click(generateDetailsBtn);
		boolean f0 = visibilityCheck(patientNameError);
		boolean f1 = visibilityCheck(patientPhoneError);
		sendKeys(patientEmail, "name");
		sendKeys(patientPhone, "0123456789123");
		sendKeys(patientName, "name1");
		sendKeys(patientEmail, "name1@gmail.com");
		sendKeys(patientPhone, "0123456789");
		click(generateDetailsBtn);
	try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	if(f0==true & f1==true) {
		flag = true;
	}
		 return flag;
	}
	
	public boolean secondaryDetails() {
		boolean flag = false;
		log.info("verifying secondary details scenario");
		click(backBtn);
		click(generateDetailsBtn);
		sendKeys(height, "99");
		click(age);
		boolean f0 = visibilityCheck(heightError);
		sendKeys(height, "160");
		sendKeys(weight, "29");
		click(age);
		boolean f1 = visibilityCheck(weightError);
		sendKeys(weight, "55");
		sendKeys(age, "22");
		click(gender);
		for(WebElement e:genderList) {
			if(collectText(e).contains("Female")) {
				click(e);
				break;
			}
		}
		click(addTestBtn);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(f0==true & f1==true) {
			flag = true;
		}
			 return flag;
	}
	
	
}