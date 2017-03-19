package com.salesforce.selenium.demo.common;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.salesforce.selenium.demo.toolkit.TestSalesforceException;

/**
 * @author amrit kumar
 */

public class TestMenuBarNavigation extends CommonHelper {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	//	MasterHelper.init();
	//	new CommonPageMap(MasterHelper.getWebdriver());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_example() throws Exception {

		try {

		} catch (Exception e) {
			throw new TestSalesforceException(e.getMessage());
		}
	}

}
