package com.core.framework.HybridFramework.util;

import java.util.Hashtable;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class DataUtil {
	
	public static Object[][] getData(Xls_Reader xls, String testCaseName)
	{
		String sheetName="Data";
		//System.out.println(xls.getRowCount(sheetName));
		int testStartRowNo=1;
		while(!xls.getCellData(sheetName, 0, testStartRowNo).equals(testCaseName))
		{
			testStartRowNo++;
		}
		//System.out.println("Test case found at "+testStartRowNo);
		int colStartRowNum=testStartRowNo+1;
		int dataStartRowNum=testStartRowNo+2;
		
		int rows=0;
		while(!xls.getCellData(sheetName, 0, dataStartRowNum+rows).equals(""))
		{
			rows++;
		}
		//System.out.println("Rows are "+rows);
		
		int cols=0;
		while(!xls.getCellData(sheetName, cols, colStartRowNum).equals(""))
		{
			cols++;
		}
		//System.out.println("Cols are "+cols);
		Object[][] data=new Object[rows][1];
		int dataRow=0;
		Hashtable<String,String> ht=null;
		for(int rNum=dataStartRowNum;rNum<dataStartRowNum+rows;rNum++)
		{
			ht=new Hashtable<String,String>();
			for(int cNum=0;cNum<cols;cNum++)
			{
				String key=xls.getCellData(sheetName, cNum, colStartRowNum);
				String value=xls.getCellData(sheetName, cNum, rNum);
				ht.put(key, value);
			}
			data[dataRow][0]=ht;
			dataRow++;
		}

		return data;
	}
	
	//true  --Y
	//false --N
	public static boolean isSkip(Xls_Reader xls, String testCaseName, ExtentTest test)
	{
		int rows= xls.getRowCount(Constants.TESTCASES_SHEET);
		for(int rNum=2;rNum<=rows;rNum++)
		{
			String tcid=xls.getCellData(Constants.TESTCASES_SHEET, Constants.TCID_COL, rNum);
			if(tcid.equals(testCaseName))
			{
				String runmode=xls.getCellData(Constants.TESTCASES_SHEET, Constants.RUNMODE_COL, rNum);
			if(runmode.equals("Y"))
			{
				return false;
			}
			else
			{		
				test.log(LogStatus.INFO, "TestCase is getting skipped as Run mode is N for Test Case "+testCaseName);
				return true;
			}
			
			}
			
		}
		return true;
		
	}
}
