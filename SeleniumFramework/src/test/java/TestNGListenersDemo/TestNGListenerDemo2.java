package TestNGListenersDemo;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Pages.GoogleSearchPageObjects;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

//@Listeners(TestNGListenersDemo.TestNGListeners.class)

public class TestNGListenerDemo2 {

	static WebDriver driver = null;
	GoogleSearchPageObjects search_web_objects = new GoogleSearchPageObjects(driver);

	@Parameters("browserName")
	@BeforeTest(groups = {"setup"})
	public void Setup_Test(String browserName) {
		if(browserName.equalsIgnoreCase("chrome")) {
			//WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			driver = new ChromeDriver(options);
		}else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
	}
	
	@Test(groups = {"sanity"}, retryAnalyzer = TestNGListeners.class)
	public void go_to_url(){
		driver.get("https://www.google.com.ph");
	}
	
	@Test(groups = {"smoke"}, dependsOnMethods = {"go_to_url"}, retryAnalyzer = TestNGListeners.class)
	public void input_text() throws InterruptedException {
		Thread.sleep(5000);
		search_web_objects.Set_Text_in_searchbox("testng listeners reason for failed test");
	}
	
	@Test(groups = {"smoke"}, dependsOnMethods = {"input_text"}, retryAnalyzer = TestNGListeners.class)
	public void search_input() throws InterruptedException {
		Thread.sleep(5000);
		search_web_objects.Find_Text_from_searchbox();
	}
	@AfterTest(groups = {"setup"})
	public void Close_Setup() {
		driver.close();
		driver.quit();
	}
}
