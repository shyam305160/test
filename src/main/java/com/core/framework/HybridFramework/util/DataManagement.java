package com.core.framework.HybridFramework.util;

import org.apache.poi.util.SystemOutLogger;

public class DataManagement {

	public static void main(String[] args) {
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
		
		for(int rNum=dataStartRowNum;rNum<dataStartRowNum+rows;rNum++)
		{
			for(int cNum=0;cNum<cols;cNum++){
				System.out.println(xls.getCellData(sheetName, cNum, rNum));
			}
		}
	}

}
