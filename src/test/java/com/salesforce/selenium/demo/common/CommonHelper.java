package com.salesforce.selenium.demo.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.salesforce.selenium.demo.toolkit.MasterHelper;

/**
 * @description If the code of tests is reusable then it should probably become
 *              a helper. This class contain helper for common functionality in
 *              Salesforce Website.
 * @author amrit kumar
 */

public abstract class CommonHelper extends MasterHelper {

	public void moveToToolbarTab(String tab) throws Exception {
		String index = "";
		if (tab.equalsIgnoreCase("products")) {
			index = "1";
		} else if (tab.equalsIgnoreCase("community")) {
			index = "2";
		} else if (tab.equalsIgnoreCase("industries")) {
			index = "3";
		} else if (tab.equalsIgnoreCase("services")) {
			index = "4";
		} else if (tab.equalsIgnoreCase("customers")) {
			index = "5";
		} else if (tab.equalsIgnoreCase("events")) {
			index = "6";
		} else if (tab.equalsIgnoreCase("aboutus")) {
			index = "7";
		}
		WebElement tabLocator = this.getTabWebElement(index);
		Actions action = new Actions(MasterHelper.getWebdriver());
		action.moveToElement(tabLocator).release().perform();
	}

	private WebElement getTabWebElement(String tabIndex) {
		WebElement tabId = MasterHelper.getWebdriver().findElement(
				(By.cssSelector("div.primaryMenuOverlayEffectTypeLevel > div > ul > li:nth-child(" + tabIndex +")")));
		return tabId;
	}
	
	public void naviagteToProductPage(String product) throws Exception {
		String index = "";
		if (product.equalsIgnoreCase("salescloud")) {
			index = "sales-cloud";
		} else if (product.equalsIgnoreCase("salesforceiq")) {
			index = "salesforceiq";
		} else if (product.equalsIgnoreCase("salesforce-quote-to-cash")) {
			index = "salesforce-quote-to-cash";
		} else if (product.equalsIgnoreCase("service-cloud")) {
			index = "service-cloud ";
		} else if (product.equalsIgnoreCase("marketing-cloud")) {
			index = "marketing-cloud";
		} else if (product.equalsIgnoreCase("datacom")) {
			index = "datacom";
		}
		WebElement tabLocator = this.getProductWebElement(index);
		tabLocator.click();
	}

	private WebElement getProductWebElement(String tabIndex) {
		WebElement tabId = MasterHelper.getWebdriver().findElement(
				(By.cssSelector("#" + tabIndex +" > a")));
		return tabId;
	}

}
