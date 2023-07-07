package com.w2a.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	public static Properties config;
	public static Properties OR;
	public static Logger log;
	public static WebDriverWait wait;
	public static ExtentTest test;
	public static ExtentReports report;


	@BeforeSuite
	public static void setUp() {
		log = Logger.getLogger(TestBase.class);
		PropertyConfigurator
				.configure(System.getProperty("user.dir") + "\\src\\test\\resources\\Assets\\Log4j.properties");
		
		report = new ExtentReports(System.getProperty("user.dir")+"//reports//ExtentReportResults.html");

		
		config = new Properties();
		OR = new Properties();

		try {
			config.load(new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\Properties\\config.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			OR.load(new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\Properties\\OR.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (config.getProperty("browser").equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			log.info("launching chrome browser");
		}
		else if (config.getProperty("browser").equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			log.info("launching firefox browser");
		}


		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(config.getProperty("url"));
		log.info("navigating to site ");
		wait = new WebDriverWait(driver, 10);
		
	}
	
	@AfterSuite
	public static void tearDown() {
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		if(driver!=null)
		{	driver.close();
			log.info("Browser closed");
		}
		}
	}


