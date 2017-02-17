package com.core.framework.HybridFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

import com.core.framework.HybridFramework.util.Constants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;

public class GenericKeywords{
	public WebDriver driver;
	public Properties prop;
	private ExtentTest test;
	public GenericKeywords(ExtentTest test)
	{
		this.test=test;
		prop=new Properties();
		FileInputStream fis;
		try {
			fis = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//project.properties");
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String OpenBrowser(String browserType)
	{
		test.log(LogStatus.INFO, "Opening Browser "+browserType);
		driver= Utilities.getDesiredDriver(browserType, driver);
		driver.manage().timeouts().implicitlyWait(20000, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return Constants.PASS;
	}
	
	public String click(String locatorKey)
	{
		test.log(LogStatus.INFO, "Clicking on Webelement "+locatorKey);
		WebElement ele=Utilities.getWebElement(locatorKey, driver, prop, test);
		ele.click();
		return Constants.PASS;
	}
	
	public String navigate(String url)
	{
		test.log(LogStatus.INFO, "Navigating to URL "+prop.getProperty(url));
		driver.navigate().to(prop.getProperty(url));
		return Constants.PASS;
	}
	
	public String type(String locatorKey, String data)
	{
		test.log(LogStatus.INFO, "Typing in the Webelement "+locatorKey+ " of Data "+data);
		WebElement ele=Utilities.getWebElement(locatorKey, driver, prop, test);
		ele.sendKeys(data);
		return Constants.PASS;
	}
	
	public String closeBrowser()
	{
		test.log(LogStatus.INFO, "Closing the browser");
		driver.quit();
		return Constants.PASS;
	}
	
	public String scrollTo(String locatorKey)
	{
		int y=Utilities.getWebElement(locatorKey, driver, prop, test).getLocation().y;
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,"+y+")");
		return Constants.PASS;
	}
	public String verifyText(String locatorKey, String expectedTexts)
	{
		WebElement ele=Utilities.getWebElement(locatorKey, driver, prop, test);
		String actualValue=ele.getText();
		//Assert.assertEquals(expectedTexts, actualValue,"Values are not matching");	
		if(actualValue.equals(expectedTexts))
		return Constants.PASS;
		else
			return Constants.FAIL;
	}
	
	public String verifyElementPresent(String locatorKey)
	{
		boolean result=Utilities.isElementPresent(locatorKey, driver, prop, test);
		if(result)
			return Constants.PASS;
		else
			return Constants.FAIL +" - Could not find the element "+locatorKey;
	}
	
	public String verifyElementNotPresent(String locatorKey)
	{
		boolean result=Utilities.isElementPresent(locatorKey, driver, prop, test);
		if(!result)
			return Constants.PASS;
		else
			return Constants.FAIL +" - Could find the element "+locatorKey;
	}
	
	public void takeScreenShot(ExtentTest test)
	{
		Date d=new Date();
		String filePath=System.getProperty("user.dir")+"//ScreenShots//"+ "ErrorSnapshot_"+d.toString().replace(" ", "_").replace(":", "_")+".png";	
		File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.log(LogStatus.INFO, test.addScreenCapture(filePath));
	}
	
	public void reportFailure(String text)
	{
		test.log(LogStatus.FAIL, text);
		Assert.fail(text);
	}
}
