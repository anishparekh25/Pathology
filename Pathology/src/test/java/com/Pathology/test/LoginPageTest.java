package com.Pathology.test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Pathology.generics.TestBase;
import com.Pathology.pageObjects.LoginPage;

public class LoginPageTest extends TestBase {

	SoftAssert sa=new SoftAssert();
	

	@Test
	public void LoginModule() {
		
		LoginPage lp=new LoginPage(TestBase.driver);
		sa.assertTrue(lp.negativeLoginTest());
		sa.assertTrue(lp.positiveLoginTest());
		sa.assertTrue(lp.signOut());
		sa.assertAll();
	}
	
	

}
