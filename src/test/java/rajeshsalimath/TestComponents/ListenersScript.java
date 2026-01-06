package rajeshsalimath.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rajeshsalimath.resources.ExtentReportsScript;

public class ListenersScript extends BaseTest implements ITestListener 
{
	ExtentReports extent = ExtentReportsScript.ReportObject();
	ExtentTest test;
	
	ThreadLocal<ExtentTest> threadExtentSafe = new ThreadLocal<ExtentTest>(); 
	//used for thread safety, so that each test/methods have their own thread for execution, to overcome concurrency problem
	
//	 * Invoked each time before a test will be invoked. 
//	The <code>ITestResult</code> is only partially filled with the references to class, method, start millis
//	 * and status.
//   'ITestResult result' this holds all the information about the test case which will be executed
	@Override
	public void onTestStart(ITestResult result) 
	{
		test = extent.createTest(result.getMethod().getMethodName());
		threadExtentSafe.set(test); //unique thread id will be assigned to each test method(@Test) while execution
	}

//	 * Invoked each time a test succeeds.
//	 *
//	 * @param result <code>ITestResult</code> containing information about the run test @see ITestResult#SUCCESS
	@Override
	public void onTestSuccess(ITestResult result) 
	{
		//test.log(Status.PASS, "test passed");
		threadExtentSafe.get().log(Status.PASS, "test passed");
	}


//	 * Invoked each time a test fails.
	@Override
	public void onTestFailure(ITestResult result) 
	{
		//test.log(Status.FAIL, "test failed");
		//or
		//test.fail(result.getThrowable()); //gives the error message or stack trace as why the test failed
		
		threadExtentSafe.get().fail(result.getThrowable()); //gives the error message or stack trace as why the test failed
		
		try 
		{
			driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String filePath = null;
		try 
		{
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		threadExtentSafe.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}


//	 * Invoked each time a test is skipped.
	@Override
	public void onTestSkipped(ITestResult result) 
	{
		// not implemented
	}


//	 * Invoked each time a method fails but has been annotated with
//	 * successPercentage and this failure still keeps it within the success
//	 * percentage requested.
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
	{
		// not implemented
	}


//	 * Invoked each time a test fails due to a timeout.
	@Override
	public void onTestFailedWithTimeout(ITestResult result) 
	{
		onTestFailure(result);
	}


//	 * Invoked before running all the test methods belonging to the classes inside
//	 * the &lt;test&gt; tag and calling all their Configuration methods.
	@Override
	public void onStart(ITestContext context) 
	{
		// not implemented
	}


//	 * Invoked after all the test methods belonging to the classes inside the
//	 * &lt;test&gt; tag have run and all their Configuration methods have been called.
	@Override
	public void onFinish(ITestContext context) 
	{
		// not implemented
		extent.flush();
	}
}
