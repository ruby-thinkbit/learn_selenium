//import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
//import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class BrowserTesting {

	public static void main(String[] args) throws InterruptedException  {
		
		//String projectPath = System.getProperty("user.dir");
		//System.out.println("projectPath: "+projectPath);

		// set the driver location
		//System.setProperty("webdriver.gecko.driver", projectPath+"\\Drivers\\geckodriver.exe");
		//create web driver manually to use for navigation (using firefox)
		//WebDriver browserDriver = new FirefoxDriver();
		
		// set the driver location
		//System.setProperty("webdriver.chrome.driver", projectPath+"\\Drivers\\chromedriver.exe");
		//create web driver manually to use for navigation (using chrome)
		//WebDriver browserDriver = new ChromeDriver();
		
		// set the driver location
		//System.setProperty("webdriver.ie.driver", projectPath+"\\Drivers\\IEDriverServer.exe");
		//create web driver manually to use for navigation (using ie)
		//WebDriver browserDriver = new InternetExplorerDriver();
		
		// for automatic webdriver manager setup (chrome) = no need to install browser drivers
		WebDriverManager.chromedriver().setup();
		WebDriver browserDriver = new ChromeDriver();
		
		// go to url
		browserDriver.get("https://www.google.com.ph");
		
		try {
			//find web elements
			WebElement txtSearch = browserDriver.findElement(By.xpath("//input[@class='gLFyf']"));
			txtSearch.sendKeys("Automated Steps");
			/*List<WebElement> lstInputElements = browserDriver.findElements(By.xpath("//input"));
			int count = lstInputElements.size();
			System.out.println(count);*/
		}catch(Exception ex) {
			System.out.println(ex);
		}
		
		// close the browser
		browserDriver.close();
		browserDriver.quit();
	}
	
}
