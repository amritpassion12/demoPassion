package com.salesforce.selenium.demo.toolkit;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @description If the code is reusable then it should probably become a
 *              helper.This class contain helper methods which can help all the
 *              tests to make robust and manageable automation. Product related
 *              helper methods need to go to their respective helper class which
 *              inherit from this class.
 * @author amrit kumar
 */

public abstract class MasterHelper {

	// Default Constants
	private static final Long IMPLICIT_TIMEOUT = (long) 60;
	private static final Long SCRIPT_TIMEOUT = (long) 120;
	private static final Long PAGELOAD_TIMEOUT = (long) 180;
	private static final TimeUnit TIMEUNIT = TimeUnit.SECONDS;
	private static final String GLOBAL_ENVIRONMENT = "global.properties";

	// Member variables
	public static Logger m_log;
	protected static Long m_explicit_timeout = (long) 60;
	public static WebElement m_element;
	public static WebDriver m_webdriver = null;
	private static String m_resource_package = "resources";
	public static String m_browser;
	public static String m_browserPath;
	public static String m_appUrl;
	public static String m_dataFolder;
	public static String m_username;
	public static String m_password;

	public enum TimeoutType {
		Implicit, Script, PageLoad
	}

	public enum SupportedBrowser {
		Chrome
	}

	/**
	 * Initialize resuable member variables. This method should be called by low
	 * level concrete junit test class
	 * 
	 */

	public static void init() throws Exception {

		String workingDir = System.getProperty("user.dir");
		m_dataFolder = workingDir + "\\" + "data\\";

		Properties props = loadProperties(m_dataFolder + GLOBAL_ENVIRONMENT);

		m_browser = props.getProperty("Browser").trim();
		m_browserPath = props.getProperty("BrowserPath").trim();
		m_appUrl = props.getProperty("ApplicationUrl").trim();


		// initializing webdriver instance
		if (m_browser.equals(SupportedBrowser.Chrome.toString())) {
			DesiredCapabilities dc = DesiredCapabilities.chrome();
			String[] switches = { "--ignore-certificate-errors",
					"--disable-popup-blocking", "--disable-translate" };
			dc.setCapability("chrome.switches", Arrays.asList(switches));
			System.setProperty("webdriver.chrome.driver", m_browserPath);
			m_webdriver = new ChromeDriver(dc);
			m_webdriver.manage().window().maximize();
			Thread.sleep(5000);
		} else {
			throw new TestSalesforceException(
					"Global environment input is not supported or incorrect");
		}

		m_log = new Logger(m_webdriver);

		// Initializing timeouts
		m_webdriver.manage().timeouts()
				.implicitlyWait(IMPLICIT_TIMEOUT, TIMEUNIT);
		m_webdriver.manage().timeouts()
				.setScriptTimeout(SCRIPT_TIMEOUT, TIMEUNIT);
		m_webdriver.manage().timeouts()
				.pageLoadTimeout(PAGELOAD_TIMEOUT, TIMEUNIT);

	}

	/**
	 * Open given url in the browser
	 */
	public void openBrowser(String baseUrl) {

		m_webdriver.get(baseUrl);

	}
	
	/**
	 * Open given url in the browser
	 */
	public void closeBrowser() {

		m_webdriver.close();
		m_webdriver.quit();

	}

	/**
	 * Getter of webdriver instance
	 */
	public static WebDriver getWebdriver() {

		return m_webdriver;
	}

	/**
	 * Getter of folder location which suppose to contain all data/input content
	 */
	public static String getDataFolder() {

		return m_dataFolder;
	}

	/**
	 * This method is to set member variable used for explicit timeout. Explicit
	 * timeout specify the amount of time the driver should wait for a certain
	 * condition to occur before proceeding.
	 * 
	 * @param timeoutInSeconds
	 *            Amount of time in seconds to wait.
	 */

	public void setExplicitTimeout(long timeoutInSeconds) {
		m_explicit_timeout = timeoutInSeconds;
	}

	/**
	 * This method is to set the Implicit timeout or Script timeout or PageLoad
	 * timeout. Implicit timeout specify the amount of time the driver should
	 * wait when searching for an element. Script timeout specify the amount of
	 * time the driver should wait for an asynchronous script to finish.
	 * PageLoad timeout specify the amount of time the driver should wait for a
	 * page load to complete.
	 * 
	 * @param Type
	 *            Parameter to tell the type of timeout to set.
	 * @param time
	 *            The amount of time to set.
	 * @param unit
	 *            The unit of measure for time.
	 */

	public void setTimeout(TimeoutType type, long time, TimeUnit unit) {

		if (type == null || unit == null) {
			throw new TestSalesforceException(
					"Atleast one of the method argument is null");
		}

		if (getWebdriver() != null) {

			if (type.equals(TimeoutType.Implicit)) {
				getWebdriver().manage().timeouts().implicitlyWait(time, unit);
			}

			if (type.equals(TimeoutType.Script)) {
				getWebdriver().manage().timeouts().setScriptTimeout(time, unit);
			}

			if (type.equals(TimeoutType.PageLoad)) {
				getWebdriver().manage().timeouts().pageLoadTimeout(time, unit);
			}

		} else
			throw new TestSalesforceException("Webdriver is not initialized");

	}
	
	/**

     * This method will switch to a frame.
     *
     * @param element
     *            WebElement locating the frame.
     *
     */
    public void switchToFrame(WebElement element)
    {
    	try {
    		getWebdriver().switchTo().frame(element);
    	}
    	catch(NoSuchFrameException e) {
    		throw new TestSalesforceException("Couldn't switch to frame of element: ");
    	}

    }
    
	/**

     * This method will switch to browser tab.
     *
     * @param index
     *            nth browser tab.
	 * @throws InterruptedException 
     *
     */
	public void switchToTab(int index) throws InterruptedException {
		try {
			ArrayList<String> tabs = new ArrayList<String>(MasterHelper
					.getWebdriver().getWindowHandles());
			Thread.sleep(2000);
			getWebdriver().switchTo().window(tabs.get(index));
		} catch (Exception e) {
			throw new TestSalesforceException(
					"Couldn't switch to tab");
		}

	}

	/**
	 * This method to be used after loading a page. It perform generic check
	 * like is there an unexpected alert.
	 * 
	 */

	public void validateOnPageLoad() {

		isAlertExpected(false);

	}

	/**
	 * This method check if there is an unexpected alert.
	 * 
	 * @param alertexpect
	 *            true if we expect an alert otherwise false.
	 * 
	 */

	public void isAlertExpected(boolean alertexpect) {
		try {
			Alert alert = getWebdriver().switchTo().alert();
			if (!alertexpect) {
				throw new TestSalesforceException("Unexpected Alert Found : '"
						+ alert.getText() + "'");
			}
		} catch (NoAlertPresentException e) {

			if (alertexpect) {
				throw new TestSalesforceException("Expected Alert Not Found");
			}
		}
	}

	/**
	 * This method will click on a given WebElement after waiting that to be
	 * clickable.Default timeout is of 60 seconds. Default timeout can be reset
	 * via explicit timeout setter.
	 * 
	 * @param element
	 *            WebElement represents an HTML element.
	 * 
	 */

	public void waitAndClick(WebElement element) {

		try {
			new WebDriverWait(getWebdriver(), m_explicit_timeout)
					.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
		} catch (Exception e) {

			throw new TestSalesforceException("Explicit timeout after: "
					+ m_explicit_timeout);
		}

	}

	/**
	 * This method will double click on a given WebElement after waiting that to
	 * be clickable.Default timeout is of 60 seconds. Default timeout can be
	 * reset via respective setter method.
	 * 
	 * @param element
	 *            WebElement represents an HTML element.
	 * 
	 */

	public void waitAndDoubleClick(WebElement element) {

		try {
			new WebDriverWait(getWebdriver(), m_explicit_timeout)
					.until(ExpectedConditions.elementToBeClickable(element));
			Actions action = new Actions(getWebdriver());
			action.doubleClick(element).build().perform();

		} catch (Exception e) {

			throw new TestSalesforceException("Explicit timeout after: "
					+ m_explicit_timeout);
		}
	}

	/**
	 * This method sets the text for a given WebElement. There are problems with
	 * WebElement.sendKeys() where events do not fire quickly enough if the
	 * entire string is sent in one call. This method sends the string one
	 * character at a time to slow down the speed of the typing.
	 *
	 * @param element
	 *            The WebElement whose text is being cleared
	 */
	public void setText(WebElement Element, String NewText) {
		for (int i = 0; i < NewText.length(); i++)
			Element.sendKeys(String.valueOf(NewText.charAt(i)));
	}

	/**
	 * This method will wait for WebElement to be visible before checking if it
	 * is displayed.Default timeout is of 60 seconds. Default timeout can be
	 * reset via respective setter method.
	 * 
	 * @param element
	 *            WebElement locating the frame
	 * @return true of WebElement is displayed otherwise false
	 * 
	 */
	public boolean waitAndCheckDisplay(WebElement element) {

		try {
			new WebDriverWait(getWebdriver(), m_explicit_timeout)
					.until(ExpectedConditions.visibilityOf(element));
			return element.isDisplayed();
		} catch (Exception e) {

			throw new TestSalesforceException("Explicit timeout after: "
					+ m_explicit_timeout);
		}

	}

	/**
	 * This method will wait for HTML element locator to be clickable.Default
	 * timeout is of 60 seconds. Default timeout can be reset via respective
	 * setter method.
	 * 
	 * @param locator
	 *            HTML element locator
	 * @return true of WebElement is displayed otherwise false
	 * 
	 */
	public boolean waitAndCheckVisible(String locator) {

		try {
			WebElement e1 = new WebDriverWait(getWebdriver(),
					m_explicit_timeout).until(ExpectedConditions
					.visibilityOfElementLocated(By.cssSelector(locator)));
			return e1.isDisplayed();
		} catch (Exception e) {

			throw new TestSalesforceException("Explicit timeout after: "
					+ m_explicit_timeout);
		}

	}

	/**
	 * Load and returns the properties object from a file
	 *
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static Properties loadProperties(String fileName) throws IOException {
		FileInputStream in = new FileInputStream(fileName);
		Properties props = new Properties();

		try {
			props.load(in);
		} catch (IOException e) {

			m_log.logException(e);
			throw e;
		} finally {
			if (in != null) {
				try {

					in.close();
				} catch (IOException e) {
					m_log.logException(e);
					throw e;
				}
			}
		}

		return props;
	}

	/**
	 * This method will search the supplied resource file for the supplied ID
	 * and return the appropriate string based on the locale being tested.
	 * 
	 * @param id
	 *            The ID of the string we want to retrieve, example format
	 * @param locale
	 *            Test environment locale.
	 * @return The localized string that corresponds to the id.
	 */
	public static String getResourceString(String id, String locale) {

		String resourceString = "";
		// TODO add logic to retrieve the localized string from resource package
		return resourceString;
	}
}
