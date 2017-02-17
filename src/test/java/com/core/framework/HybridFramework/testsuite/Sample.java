package com.core.framework.HybridFramework.testsuite;

import org.testng.annotations.Test;

import junit.framework.Assert;

public class Sample {
	@Test
	public void testA()
	{
		System.out.println("Test A method passed");
	}
	
	@Test
	public void testB()
	{
		System.out.println("Test B method passed");
	}
	
	@Test
	public void testC()
	{
		Assert.assertTrue(false);
		System.out.println("Test B method passed");
	}

}
