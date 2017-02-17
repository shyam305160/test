package com.core.framework.HybridFramework.testsuite;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.core.framework.HybridFramework.Keywords;
import com.core.framework.HybridFramework.util.Constants;
import com.core.framework.HybridFramework.util.DataUtil;
import com.core.framework.HybridFramework.util.Xls_Reader;
import com.relevantcodes.extentreports.LogStatus;

public class GmailTest extends Base {
	
	@BeforeTest
	public void beforeTest()
	{
		testCaseName="GmailTest";
		xls=new Xls_Reader(Constants.SUITEA_Excel);
	}
	

	@Test(dataProvider="getData")
	public void GmailTest1(Hashtable<String,String> ht)
	{
		test=extent.startTest(testCaseName);
		test.log(LogStatus.INFO, ht.toString());
		if(DataUtil.isSkip(xls, testCaseName,test) || ht.get("RunMode").equals("N"))
		{
			test.log(LogStatus.SKIP, "Skipping as the RunMode is N");
			throw new SkipException("Skipping as the RunMode is N");
		}
		Keywords app=new Keywords(test);
		app.executeKeywords("GmailTest", xls,ht);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//app.getFailureScreenshots().takeScreenShot();
		Assert.assertEquals(test.getRunStatus(), LogStatus.PASS);
	}
	
	
}
