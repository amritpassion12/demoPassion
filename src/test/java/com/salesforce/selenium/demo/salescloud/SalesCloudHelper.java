package com.salesforce.selenium.demo.salescloud;

import org.openqa.selenium.support.ui.Select;

import com.salesforce.selenium.demo.common.CommonHelper;

/**
 * @description If the code of tests is reusable then it should probably become
 *              a helper. This class contain helper for Sales Cloud page
 *              functionality
 * @author amrit kumar
 */
public abstract class SalesCloudHelper extends CommonHelper {

	/**
	 * This method is to fill required info in demo registration form. Parameter
	 * input need to be provided from global.properties file
	 * 
	 */

	public void fillRequiredInfo(String userFirstName, String userLastName,
			String userJobTitle, String userCompany, String userPhone,
			String userEmail, String employees, String userCountry,
			String userProvince) {

		this.waitAndClick(DemoPageMap.userFirstName);
		this.setText(DemoPageMap.userFirstName, userFirstName);

		this.waitAndClick(DemoPageMap.userLastName);
		this.setText(DemoPageMap.userLastName, userLastName);

		this.waitAndClick(DemoPageMap.userJobTitle);
		this.setText(DemoPageMap.userJobTitle, userJobTitle);

		this.waitAndClick(DemoPageMap.userCompany);
		this.setText(DemoPageMap.userCompany, userCompany);

		this.waitAndClick(DemoPageMap.userPhone);
		this.setText(DemoPageMap.userPhone, userPhone);

		this.waitAndClick(DemoPageMap.userEmail);
		this.setText(DemoPageMap.userEmail, userEmail);

		if (employees != null) {
			new Select(DemoPageMap.userEmployees)
					.selectByVisibleText(employees);
		}
		if (userCountry != null) {
			new Select(DemoPageMap.userCountry)
					.selectByVisibleText(userCountry);
		}

		if (userProvince != null) {
			new Select(DemoPageMap.userProvince)
					.selectByVisibleText(userProvince);
		}

	}
}
