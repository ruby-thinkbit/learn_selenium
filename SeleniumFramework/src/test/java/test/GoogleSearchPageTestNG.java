package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Pages.GoogleSearchPageObjects;
import io.github.bonigarcia.wdm.WebDriverManager;

public class GoogleSearchPageTestNG {
	
	private static WebDriver driver = null;
	
	@BeforeTest
	public void SetupTesting() {
		//String projectPath = System.getProperty("user.dir");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	
	@Test
	public static void Test_Google_Search() {
		driver.get("https://www.google.com.ph");
		
		GoogleSearchPageObjects search_web_objects = new GoogleSearchPageObjects(driver);
		try {
			search_web_objects.Set_Text_in_searchbox("test1");
			Thread.sleep(5000);
			search_web_objects.Find_Text_from_searchbox();
		}catch(Exception ex) {
			System.out.println(ex);
		}
	}
	
	@AfterTest
	public void StopTest() {
		driver.close();
		driver.quit();
	}
}
