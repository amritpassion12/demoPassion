package com.salesforce.selenium.demo.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class contain WebElement locator used in the tests and helper methods of
 * this package. It suppose to contain web element which are transversal such as
 * common navigation bar
 * 
 * @author amrit kumar
 */

public class CommonPageMap {

	public CommonPageMap(WebDriver webDriver) {
		PageFactory.initElements(webDriver, this);
	}

	// WebElement locator which will get initialized by PageFactory

	// Home Page Content
	@FindBy(css = "div.primaryMenuOverlayEffectTypeLevel > div > ul")
	public static WebElement homePageMainMenu;
	
	// ----------------------------------------------------

	// CSS selector constants. We need CSS selector sometime as required by
	// WebDriver api

}
