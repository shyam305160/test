package com.core.framework.HybridFramework;

import java.util.Hashtable;

import com.core.framework.HybridFramework.util.Constants;
import com.relevantcodes.extentreports.ExtentTest;

public class ApplicationKeywords extends GenericKeywords{

	public ApplicationKeywords(ExtentTest test) {
		super(test);

	}
	
	public String VerifyLoginDetails(Hashtable<String, String> testData)
	{
		//name
		String expectedName=testData.get("Name");
		//email
		String email=testData.get("UserName");
		
		return Constants.PASS;
	}

}
