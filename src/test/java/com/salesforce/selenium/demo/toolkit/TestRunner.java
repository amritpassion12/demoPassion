package com.salesforce.selenium.demo.toolkit;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.salesforce.selenium.demo.salescloud.SalesCloudSuite;

/**
 * @author amrit kumar
 */
public class TestRunner {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Result result = JUnitCore.runClasses(SalesCloudSuite.class);
        for (Failure failure : result.getFailures()) {
           System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());

    }

}
