package com.core.framework.HybridFramework.util;

public class Constants {
	//*******************ExcelVaues***********************************//
	public static final String SUITEA_Excel=System.getProperty("user.dir")+"//Data//SuiteA.xlsx";
	public static final String KEYWORDS_SHEET="Keywords";
	public static final String TCID_COL = "TCID";
	public static final String KEYWORD_COL = "Keyword";
	public static final String OBJECT_COL = "Object";
	public static final String DATA_COL = "Data";
	public static final String TESTCASES_SHEET = "TestCases";
	public static final String RUNMODE_COL = "RunMode";
	public static final String PASS = "Pass";
	public static final String FAIL = "Fail";
	//******************BrowserTypes***********************************//
	public static final String FIREFOX = "Firefox";
	public static final String CHROME = "Chrome";
	public static final String EDGE = "Edge";
	//*******************GenericKeywords**************************************//
	public static final String OPENBROWSER = "openBrowser";
	public static final String NAVIGATE = "navigate";
	public static final String CLICK = "click";
	public static final String INPUT = "input";
	public static final String VERIFYELEMENTPRESENT = "VerifyElementPresent";
	public static final String VERIFYLOGINDETAILS = "VerifyLoginDetails";
	public static final String CLOSEBROWSER = "closeBrowser";
}
