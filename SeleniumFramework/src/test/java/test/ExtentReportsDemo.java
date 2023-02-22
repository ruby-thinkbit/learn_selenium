package test;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Configurations.PropertiesFile;
import Pages.GoogleSearchPageObjects;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtentReportsDemo {
	
	private static WebDriver driver = null;
	ExtentSparkReporter htmlReporter;
	ExtentTest test;
	ExtentReports extent;
	
	public static String browserName = null;
	
	@BeforeSuite
	public void Setup_Tests() {
		// initialize the HtmlReporter
		htmlReporter = new ExtentSparkReporter("extent.html");
		
		// create extent report
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		//String projectPath = System.getProperty("user.dir");
		
		PropertiesFile.Get_Properties();
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
	}
	
	@Test
	public void Search_Test_with_Extent_Report() {
		// create test logs
		test = extent.createTest("Search in Google Homepage", "test for validation of google search functionality");
		
		GoogleSearchPageObjects search_web_objects = new GoogleSearchPageObjects(driver);
		try {
			driver.get("https://www.gogle.com.ph");
			test.pass("went to the URL");
			Thread.sleep(5000);
			search_web_objects.Set_Text_in_searchbox("test1");
			test.pass("searched the data");
			Thread.sleep(5000);
			search_web_objects.Find_Text_from_searchbox();
			test.pass("submit the data");
		}catch(Exception ex) {
			test.fail("Error: "+ex.getMessage());
			test.fail("Cause: "+ex.getCause());
			ex.printStackTrace();
			Capabilities browserCapabilities = ((RemoteWebDriver)driver).getCapabilities();
			test.fail("Browser Name: "+browserCapabilities.getBrowserName());
			test.fail("Browser Version: "+browserCapabilities.getBrowserVersion());
			test.fail("Browser Version: "+browserCapabilities.getPlatformName());
		}
	}
	
	@Test
	public void Search_Test_with_Extent_Report_2() {
		// create test logs
		test = extent.createTest("Search in Google Homepage", "test for validation of google search functionality");

		GoogleSearchPageObjects search_web_objects = new GoogleSearchPageObjects(driver);
		try {
			driver.get("https://www.gogle.com.ph");
			test.pass("went to the URL");
			Thread.sleep(5000);
			search_web_objects.Set_Text_in_searchbox("test1");
			test.pass("searched the data");
			Thread.sleep(5000);
			search_web_objects.Find_Text_from_searchbox();
			test.pass("submit the data");
		}catch(Exception ex) {
			test.fail("Error: "+ex.getMessage());
			test.fail("Cause: "+ex.getCause());
			ex.printStackTrace();
			Capabilities browserCapabilities = ((RemoteWebDriver)driver).getCapabilities();
			test.fail("Browser Name: "+browserCapabilities.getBrowserName());
			test.fail("Browser Version: "+browserCapabilities.getBrowserVersion());
			test.fail("Browser Version: "+browserCapabilities.getPlatformName());
		}
	}
	
	@AfterSuite
	public void Close_Setup() {
		driver.close();
		driver.quit();
		test.log(Status.INFO,"stopped session");
		
		// log all extent reported logs
		extent.flush();
	}

}
