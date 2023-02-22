package TestNGListenersDemo;

import org.testng.annotations.Test;

import Pages.GoogleSearchPageObjects;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

//@Listeners(TestNGListenersDemo.TestNGListeners.class)

public class TestNGListenerDemo {

	@Test(groups = {"sanity"}, retryAnalyzer = TestNGListeners.class)
	public void demo1test1(){
		WebDriverManager.chromedriver().setup();
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		WebDriver driver = new ChromeDriver(options);
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		GoogleSearchPageObjects search_web_objects = new GoogleSearchPageObjects(driver);
		driver.get("https://www.google.com.ph");
		search_web_objects.Set_Text_in_searchbox("testng listeners reason for failed test");
		search_web_objects.Find_Text_from_searchbox();
		search_web_objects.Go_to_first_result();
		driver.close();
	}
	@Test(groups = {"sanity"}, retryAnalyzer = TestNGListeners.class)
	public void demo1test2() {
		System.out.println("two");
	}
	@Test(groups = {"sanity"}, retryAnalyzer = TestNGListeners.class)
	public void demo1test3() {
		System.out.println("three");
		Assert.assertTrue(0>1);
	}
	@Test(groups = {"sanity"}, retryAnalyzer = TestNGListeners.class)
	public void demo1test4() {
		System.out.println("three");
	}
}
