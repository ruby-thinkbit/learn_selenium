package test;

//import org.openqa.selenium.By;

//import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

//import Pages.GoogleSearchPage;
import Pages.GoogleSearchPageObjects;

public class GoogleSearch_Test {
	
	private static WebDriver driver = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test_google_search();
		

	}
	
	public static void test_google_search() {
		
		//String projectPath = System.getProperty("user.dir");
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		driver.get("https://www.google.com.ph");
		
		GoogleSearchPageObjects search_web_objects = new GoogleSearchPageObjects(driver);
		
		try {
			search_web_objects.Set_Text_in_searchbox("ruby");
			Thread.sleep(5000);
			search_web_objects.Find_Text_from_searchbox();
			
			//WebElement txtSearch = driver.findElement(By.xpath("//input[@class='gLFyf']"));
			//txtSearch.sendKeys("test");
			//GoogleSearchPage.Search_Textbox(driver).sendKeys("test");
			
			//Thread.sleep(5000);

//			driver.findElement(By.xpath("//input[@class='gNO89b']")).click();
			//driver.findElement(By.xpath("//input[@class='gLFyf']")).sendKeys(Keys.RETURN);
			//GoogleSearchPage.Search_Button(driver).sendKeys(Keys.RETURN);
		}catch(Exception ex) {
			System.out.println(ex);
		}
		
		driver.close();
	}

}
