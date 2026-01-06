package rajeshsalimath.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryScript implements IRetryAnalyzer 
{
	int count = 0;
	int maxTry = 1;
	//this method runs 1 time as maxTry is 1, it can increased depending on the need
	@Override
	public boolean retry(ITestResult result) 
	{
		if(count < maxTry) 
		{
			count++;
			return true;
		}
		
		//WebDriver driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		//result.getMethod().getMethodName();
		return false;
	}

}

//The IRetryAnalyzer is a TestNG interface used to automatically re-run failed test methods to handle intermittent, non-reproducible failures (flaky tests).
//By implementing this interface, you can add custom logic to decide whether a specific test should be retried before being marked as a definitive failure. 