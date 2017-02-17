package com.core.framework.HybridFramework;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.testng.Assert;

import com.core.framework.HybridFramework.util.Constants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Utilities {
	
public static WebDriver getDesiredDriver(String browserName, WebDriver driver)
	{
		if(browserName.equalsIgnoreCase(Constants.FIREFOX))
		{
				ProfilesIni profile = new ProfilesIni();
				FirefoxProfile myprofile = profile.getProfile("ShyamSelenium");
				driver=new FirefoxDriver(myprofile);
		}else if(browserName.equalsIgnoreCase(Constants.CHROME))
		{
				System.setProperty("webdriver.chrome.driver", "E:\\Rakesh\\SeleniumComponents\\chrome\\chromedriver.exe");
				driver=new ChromeDriver();
		}
		else
		{
				System.setProperty("webdriver.edge.driver","E:\\Rakesh\\SeleniumComponents\\edge\\MicrosoftWebDriver.exe");
				driver=new EdgeDriver();
		}
		
		return driver;
	}

	public static String getKeywords(String keyword,ApplicationKeywords app,String object, String data,Hashtable<String,String> testData)
	{
		String results="";
		try
		{
			if(keyword.equalsIgnoreCase(Constants.OPENBROWSER))
			{
			results= app.OpenBrowser(data);
			}
			else if(keyword.equalsIgnoreCase(Constants.NAVIGATE))
			{
			results=app.navigate(object);
			}
			else if(keyword.equalsIgnoreCase(Constants.CLICK))
			{
			results=app.click(object);
			}
			else if(keyword.equalsIgnoreCase(Constants.INPUT))
			{
			results=app.type(object, data);
			}
			else if(keyword.equalsIgnoreCase(Constants.VERIFYELEMENTPRESENT))
			{
			results=app.verifyElementPresent(object);
			}
			else if(keyword.equalsIgnoreCase(Constants.VERIFYLOGINDETAILS))
			{
			results=app.VerifyLoginDetails(testData);
			}
			else if(keyword.equalsIgnoreCase(Constants.CLOSEBROWSER))
			{
			results=app.closeBrowser();
			}
			else
			{
			app.closeBrowser();
			app.reportFailure("There is no Keyword by "+ keyword);
			}
		}
		catch (Exception ex)
		{
			app.closeBrowser();
		}
		return results;
	}
	public static WebElement getWebElement(String locatorKey,WebDriver driver, Properties prop, ExtentTest test)
	{
		WebElement ele = null;
		try
		{
		if(locatorKey.endsWith("_id"))
		{
			ele=driver.findElement(By.id(prop.getProperty(locatorKey)));
		}
		else if(locatorKey.endsWith("_xpath"))
		{
			ele=driver.findElement(By.xpath(prop.getProperty(locatorKey)));
		}
		else if(locatorKey.endsWith("_classname"))
		{
			ele=driver.findElement(By.className(prop.getProperty(locatorKey)));
		}
		else if(locatorKey.endsWith("_css"))
		{
			ele=driver.findElement(By.cssSelector(prop.getProperty(locatorKey)));
		}
		else if(locatorKey.endsWith("_linktext"))
		{
			ele=driver.findElement(By.linkText(prop.getProperty(locatorKey)));
		}
		else if(locatorKey.endsWith("_name"))
		{
			ele=driver.findElement(By.name(prop.getProperty(locatorKey)));
		}
		else if(locatorKey.endsWith("_partiallinktext"))
		{
			ele=driver.findElement(By.partialLinkText(prop.getProperty(locatorKey)));
		}
		else
		{
			ele=driver.findElement(By.tagName(prop.getProperty(locatorKey)));
		}
		}
		catch(Exception ex)
		{
			driver.quit();
			test.log(LogStatus.FAIL, "Failure in Element Extraction "+locatorKey);
			Assert.fail("Failure in Element Extraction "+locatorKey);
		}
		return ele;
	}
	public static boolean isElementPresent(String locatorKey, WebDriver driver, Properties prop, ExtentTest test)
	{
		List<WebElement> ele=null;
		try
		{
		if(locatorKey.endsWith("_id"))
		{
			ele=driver.findElements(By.id(prop.getProperty(locatorKey)));
		}
		else if(locatorKey.endsWith("_xpath"))
		{
			ele=driver.findElements(By.xpath(prop.getProperty(locatorKey)));
		}
		else if(locatorKey.endsWith("_classname"))
		{
			ele=driver.findElements(By.className(prop.getProperty(locatorKey)));
		}
		else if(locatorKey.endsWith("_css"))
		{
			ele=driver.findElements(By.cssSelector(prop.getProperty(locatorKey)));
		}
		else if(locatorKey.endsWith("_linktext"))
		{
			ele=driver.findElements(By.linkText(prop.getProperty(locatorKey)));
		}
		else if(locatorKey.endsWith("_name"))
		{
			ele=driver.findElements(By.name(prop.getProperty(locatorKey)));
		}
		else if(locatorKey.endsWith("_partiallinktext"))
		{
			ele=driver.findElements(By.partialLinkText(prop.getProperty(locatorKey)));
		}
		else
		{
			ele=driver.findElements(By.tagName(prop.getProperty(locatorKey)));
		}
		}
		catch(Exception ex)
		{
			test.log(LogStatus.FAIL, "Failure in Element Extraction"+locatorKey);

			Assert.fail("Failure in Element Extraction"+locatorKey);

		}
		if(ele.size()==0)
			return false;
		else
			return true;
	}
}
