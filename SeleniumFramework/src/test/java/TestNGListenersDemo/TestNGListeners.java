package TestNGListenersDemo;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.IRetryAnalyzer;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

public class TestNGListeners implements ITestListener, IReporter, IRetryAnalyzer{
	public void onTestStart(ITestResult result) {
	    System.out.println("*****Test Started: "+result.getName());
	}
	public void onTestSuccess(ITestResult result) {
	    System.out.println("*****Test Successful: "+result.getName());
	}
	public void onTestFailure(ITestResult result) {
	    System.out.println("*****Test Failed: "+result.getName());
	    System.out.println("*****Test Failed: "+result.getThrowable());
	}
	public void onTestSkipped(ITestResult result) {
	    System.out.println("*****Test Skipped: "+result.getName());
	}
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	    // not implemented
	}
	public void onTestFailedWithTimeout(ITestResult result) {
	    onTestFailure(result);
	}
	public void onStart(ITestContext context) {
	    System.out.println("*****Execution Started: "+context.getName());
	}
	public void onFinish(ITestContext context) {
	    System.out.println("*****Execution Completed: "+context.getName());
	}
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		ISuite suite = suites.get(0);
		Map<String, Collection<ITestNGMethod>> methodsByGroup = suite.getMethodsByGroups();
		Map<String, ISuiteResult> tests = suite.getResults();
		for (String key : tests.keySet()) {
			System.out.println("Key: " + key + ", Value: " + tests.get(key));
		}
		Collection<ISuiteResult> suiteResults = tests.values();
		ISuiteResult suiteResult = suiteResults.iterator().next();
		ITestContext testContext = suiteResult.getTestContext();
		Collection<ITestNGMethod> perfMethods = methodsByGroup.get("smoke");
		IResultMap failedTests = testContext.getFailedTests();
		for (ITestNGMethod perfMethod : perfMethods) {
			Set<ITestResult> testResultSet = failedTests.getResults(perfMethod);
			for (ITestResult testResult : testResultSet) {
				System.out.println("Test " + testResult.getName() + " failed, error " + testResult.getThrowable());
			}
		}
		IResultMap passedTests = testContext.getPassedTests();
		for (ITestNGMethod perfMethod : perfMethods) {
			Set<ITestResult> testResultSet = passedTests.getResults(perfMethod);
			for (ITestResult testResult : testResultSet) {
				System.out.println("Test " + testResult.getName() + " passed, time took " + (testResult.getEndMillis() - testResult.getStartMillis()));
			}
		}
	}
	
	private int retryCount = 0;
	private static final int maxRetryCount = 3;
	public boolean retry(ITestResult result) {
		if(retryCount < maxRetryCount) {
			retryCount++;
			return true;
		}
		return false;
	}
}
