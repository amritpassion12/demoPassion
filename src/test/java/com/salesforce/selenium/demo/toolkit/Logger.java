package com.salesforce.selenium.demo.toolkit;

import org.openqa.selenium.WebDriver;

/**
 * @description This is dedicated class to log relevant exception, different
 *              type of error messages, managing snapshots ...
 * @author amrit kumar
 */

public class Logger {

	static WebDriver m_webdriver;

	public Logger(WebDriver webdriver) {
		m_webdriver = webdriver;

	}

	public void logException(Throwable t) {
		t.printStackTrace();
	}

}
