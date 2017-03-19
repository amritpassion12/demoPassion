package com.salesforce.selenium.demo.salescloud;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.salesforce.selenium.demo.common.CommonPageMap;

/**
 * This class contain WebElement locator used in the tests and helper methods of
 * this package. It suppose to contain web element which are specific to
 * "Demo Registration" page
 * 
 * @author amrit kumar
 */

public class DemoPageMap extends CommonPageMap {

	public DemoPageMap(WebDriver webDriver) {
		super(webDriver);
	}

	// WebElement locator which will get initialized by PageFactory

	// Sales Cloud Home Page Content
	@FindBy(css = "#sales-cloud-einstein")
	public static WebElement salesCloudHomePage;
	
	// Sales Cloud Demo Registration link
	@FindBy(css = "div.leftnav-footer-non-affix > div > div > div > a")
	public static WebElement salesCloudDemoRegistrationLink;

	// UserFirstName text field
	@FindBy(css = "input[id='UserFirstName']")
	public static WebElement userFirstName;

	// UserLastName text field
	@FindBy(css = "input[id='UserLastName']")
	public static WebElement userLastName;

	// UserJobTitle text field
	@FindBy(css = "input[id='UserTitle']")
	public static WebElement userJobTitle;

	// UserEmail text field
	@FindBy(css = "input[id='UserEmail']")
	public static WebElement userEmail;
	
	// UserPhone text field
	@FindBy(css = "input[id='UserPhone']")
	public static WebElement userPhone;

	// UserCompany field
	@FindBy(css = "input[id='CompanyName']")
	public static WebElement userCompany;

	// UserEmployees field
	@FindBy(css = "select[id='CompanyEmployees']")
	public static WebElement userEmployees;
	
	// UserCountry field
	@FindBy(css = "select[id='CompanyCountry']")
	public static WebElement userCountry;
	
	// Province field
	@FindBy(css = "select[id='CompanyState']")
	public static WebElement userProvince;

	// Error message in employees field
	@FindBy(css = "#form-container > ul > li:nth-child(11) > div > div.field > span[class='form-field-error show-form-field-error']")
	public static WebElement employeesError;
	
	// Error message in country field
	@FindBy(css = "#form-container > ul > li:nth-child(12) > div > div.field > span[class='form-field-error show-form-field-error']")
	public static WebElement countryError;
	
	// Error message in province field
	@FindBy(css = "#form-container > ul > li:nth-child(13) > div > div.field > span[class='form-field-error show-form-field-error']")
	public static WebElement provinceError;
	
	// Watch Now submit button
	@FindBy(css = "#form-container > div.submit-container.clearfix.aligned > a > span")
	public static WebElement watchNowSubmitButton;


	// ----------------------------------------------------

	// CSS selector constants. We need CSS selector sometime as required by
	// WebDriver api

}
