package com.core.framework.HybridFramework.testsuite;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.core.framework.HybridFramework.util.Constants;
import com.core.framework.HybridFramework.util.Xls_Reader;

public class TestA {
	@Test(dataProvider="getData")
	public void TestA(Hashtable<String,String> ht)
	{
	System.out.println(ht.get("RunMode")+"--"+ht.get("col1")+"--"+ht.get("col2")+"--"+ht.get("col3")+"--"+ht.get("col4"));
	}
	
	@DataProvider
	public Object[][] getData()
	{
		String sheetName="Data";
		String testCaseName="TestB";
		Xls_Reader xls=new Xls_Reader(Constants.SUITEA_Excel);
		System.out.println(xls.getRowCount(sheetName));
		int testStartRowNo=1;
		while(!xls.getCellData(sheetName, 0, testStartRowNo).equals(testCaseName))
		{
			testStartRowNo++;
		}
		System.out.println("Test case found at "+testStartRowNo);
		int colStartRowNum=testStartRowNo+1;
		int dataStartRowNum=testStartRowNo+2;
		
		int rows=0;
		while(!xls.getCellData(sheetName, 0, dataStartRowNum+rows).equals(""))
		{
			rows++;
		}
		System.out.println("Rows are "+rows);
		
		int cols=0;
		while(!xls.getCellData(sheetName, cols, colStartRowNum).equals(""))
		{
			cols++;
		}
		System.out.println("Cols are "+cols);
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
	
}
