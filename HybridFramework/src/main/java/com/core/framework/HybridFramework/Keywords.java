package com.core.framework.HybridFramework;

import java.util.Hashtable;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.core.framework.HybridFramework.util.Constants;
import com.core.framework.HybridFramework.util.Xls_Reader;
import com.relevantcodes.extentreports.ExtentTest;

public class Keywords {
	//WebDriver driver;
	private ExtentTest test;
	ApplicationKeywords app;
	public Keywords(ExtentTest test)
	{
		this.test=test;
	}
	public void executeKeywords(String testUnderExecution, Xls_Reader xls, Hashtable<String,String> testData)
	{	
		app=new ApplicationKeywords(test);
		int rowCount=xls.getRowCount(Constants.KEYWORDS_SHEET);
		
		for(int rNum=2;rNum<rowCount+1;rNum++)
		{
			String tcID=xls.getCellData(Constants.KEYWORDS_SHEET, Constants.TCID_COL, rNum);
			if(tcID.equals(testUnderExecution))
			{
			String keyword=xls.getCellData(Constants.KEYWORDS_SHEET, Constants.KEYWORD_COL, rNum);
			String object=xls.getCellData(Constants.KEYWORDS_SHEET, Constants.OBJECT_COL, rNum);
			String key=xls.getCellData(Constants.KEYWORDS_SHEET, Constants.DATA_COL, rNum);
			String data=testData.get(key);
			
			String results=Utilities.getKeywords(keyword, app, object, data, testData);
			
			if(!results.equals(Constants.PASS))
			{
				//Utilities.reportFailure(driver, test, results +" - Failed as the Keyword"+keyword+" does not exist");;
				Assert.fail(results);
			}
			}
		}
	}
	public ApplicationKeywords getApplicationKeywords()
	{
		return app;
	}
}
