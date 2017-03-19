package com.salesforce.selenium.demo.salescloud;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.salesforce.selenium.demo.common.CommonPageMap;
import com.salesforce.selenium.demo.toolkit.MasterHelper;
import com.salesforce.selenium.demo.toolkit.TestSalesforceException;

/**
 * @author amrit kumar
 */

public class TestDemoRegistration extends SalesCloudHelper {

	private String userFirstName;
	private String userLastName;
	private String userJobTitle;
	private String userEmail;
	private String userPhone;
	private String userCompany;
	private String userCountry;
	private String userProvince;
	private String employees;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		MasterHelper.init();
		new DemoPageMap(MasterHelper.getWebdriver());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

		// Test input data
		Properties prop = MasterHelper.loadProperties(this.m_dataFolder
				+ "salesCloudDemoRegistration.properties");
		userFirstName = prop.getProperty("userFirstName").trim();
		userLastName = prop.getProperty("userLastName").trim();
		userJobTitle = prop.getProperty("jobtitle").trim();
		userEmail = prop.getProperty("userEmail").trim();
		userPhone = prop.getProperty("userPhone").trim();
		userCompany = prop.getProperty("userCompany").trim();
		userCountry = prop.getProperty("country").trim();
		userProvince = prop.getProperty("province").trim();
		employees = prop.getProperty("employees").trim();

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void product_demoRegistration_with_inCompleteInfo() throws Exception {

		try {
			// open browser with given application
			this.openBrowser(MasterHelper.m_appUrl);
			// Home Page Validation
			assertTrue(this.waitAndCheckDisplay(CommonPageMap.homePageMainMenu));
			// Move the mouse to given (menu) tab
			this.moveToToolbarTab("products");
			// Navigate to given product page
			this.naviagteToProductPage("salescloud");
			// Sales Cloud home page validation
			assertTrue(this.waitAndCheckDisplay(DemoPageMap.salesCloudHomePage));
			// click demo registration button
			this.waitAndClick(DemoPageMap.salesCloudDemoRegistrationLink);
			// switch to newly opened browser tab
			this.switchToTab(1);
			// Fill Required Info except employees, country and province
			this.fillRequiredInfo(userFirstName, userLastName, userJobTitle,
					userCompany, userPhone, userEmail, null, null, null);
			// Submit
			this.waitAndClick(DemoPageMap.watchNowSubmitButton);
			// Validation (check the presence of error css class)
			assertTrue(this.waitAndCheckDisplay(DemoPageMap.employeesError));
			assertTrue(this.waitAndCheckDisplay(DemoPageMap.countryError));
			assertTrue(this.waitAndCheckDisplay(DemoPageMap.provinceError));
			this.closeBrowser();

		} catch (Exception e) {
			throw new TestSalesforceException(e.getMessage());
		}
	}
}
