package com.Pathology.test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Pathology.generics.TestBase;
import com.Pathology.pageObjects.HomePage;
import com.Pathology.pageObjects.LoginPage;

public class HomePageTest  extends TestBase {

	SoftAssert sa=new SoftAssert();
	

	@Test
	public void homePage() {
		LoginPage lp = new LoginPage(TestBase.driver);
		HomePage hp = new HomePage(TestBase.driver);
		lp.positiveLoginTest();
		sa.assertTrue(hp.verifyHomePage(),"1");
	//	sa.assertTrue(hp.verifyAddToDo(),"2");
		sa.assertTrue(hp.verifyTestCostCalculator(),"3");
		sa.assertAll();
	}

}
