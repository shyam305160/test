package com.core.framework.HybridFramework.testsuite;

import java.util.Date;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.core.framework.HybridFramework.util.DataUtil;
import com.core.framework.HybridFramework.util.ExtentManager;
import com.core.framework.HybridFramework.util.Xls_Reader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public abstract class Base {
	public Xls_Reader xls;
	public String testCaseName;
    public ExtentReports extent;
    public ExtentTest test;
	Date d=new Date();

    //final String filePath = "Extent.html";
	String filePath=System.getProperty("user.dir")+"//Reports//"+ "Reports_"+d.toString().replace(" ", "_").replace(":", "_")+".html";
    @AfterMethod
    protected void afterMethod(ITestResult result) {
    	
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(LogStatus.FAIL, result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
        } else {
            test.log(LogStatus.PASS, "Test passed");
        }
        
        extent.endTest(test);        
        extent.flush();
    }
    
    @BeforeSuite
    public void beforeSuite() {
        extent = ExtentManager.getReporter(filePath);
    }
    
    @AfterSuite
    protected void afterSuite() {
        extent.close();
    }
    
    @DataProvider
	public Object[][] getData()
	{
		return DataUtil.getData(xls, testCaseName);
	}
}
