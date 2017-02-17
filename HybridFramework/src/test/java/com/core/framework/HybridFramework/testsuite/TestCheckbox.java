package com.core.framework.HybridFramework.testsuite;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestCheckbox {

	public static void main(String[] args) {
		
		WebDriver driv=new FirefoxDriver();
		driv.get("http://www.smartwebby.com/PHP/Phptips2.asp");

		List<WebElement> eles=driv.findElements(By.xpath("//input[@type='checkbox']"));
		for(WebElement el:eles)
		{
			if(el.isSelected())
			{
				System.out.println("Checked checkbox: "+el.getAttribute("value"));
			}
		}

	}

}
