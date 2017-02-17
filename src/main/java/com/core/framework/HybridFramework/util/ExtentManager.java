package com.core.framework.HybridFramework.util;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	
private static ExtentReports extent;
    
    public synchronized static ExtentReports getReporter(String filePath) {
        if (extent == null) {
            extent = new ExtentReports(filePath, true);
            
            extent
                .addSystemInfo("Host Name", "Shyam")
                .addSystemInfo("Environment", "Test");
        }
        
        return extent;
    }
}
