package rajeshsalimath.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsScript 
{
	public static ExtentReports ReportObject() 
	{
		//ExtentReports, ExtentSparkReporter, ExtentTest
		
		String filepath = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(filepath);
		
		reporter.config().setDocumentTitle("Test Results");
		reporter.config().setReportName("Automation results");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Madmax");
		return extent;
		
	}
}
