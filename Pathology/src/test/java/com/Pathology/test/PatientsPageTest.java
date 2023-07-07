package com.Pathology.test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Pathology.generics.TestBase;
import com.Pathology.pageObjects.HomePage;
import com.Pathology.pageObjects.LoginPage;
import com.Pathology.pageObjects.PatientsPage;

public class PatientsPageTest  extends TestBase {

	SoftAssert sa=new SoftAssert();
	

	@Test
	public void patientsPage() {
		LoginPage lp = new LoginPage(TestBase.driver);
		PatientsPage pp = new PatientsPage(TestBase.driver);
		lp.positiveLoginTest();
		sa.assertTrue(pp.verifyPatientsPage());
	//	sa.assertTrue(pp.patientContactDetails());
	//	sa.assertTrue(pp.secondaryDetails());
		sa.assertTrue(pp.addPatients());
		sa.assertAll();
	}

}
