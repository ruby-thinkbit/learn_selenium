package UsingApplitools;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.TestResultsSummary;
import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;
import com.applitools.eyes.visualgrid.model.DeviceName;
import com.applitools.eyes.visualgrid.model.ScreenOrientation;
import com.applitools.eyes.visualgrid.services.RunnerOptions;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class AplitoolTest {
	
	//test constant
	private final static BatchInfo BATCH = new BatchInfo("Test using Applitools Ultrafast Grid");

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		VisualGridRunner runner = null;
		Eyes eyes = null;
		WebDriver driver = null;
		
		try {
			//setup applitools for testing
			runner = new VisualGridRunner(new RunnerOptions().testConcurrency(1)); //Concurrency refers to the number of visual checkpoints Applitools will perform in parallel.
			eyes = new Eyes(runner);
			Configuration eyes_config = eyes.getConfiguration();
			eyes_config.setApiKey("971ciHrs0IOL9exanvPIPEotbtFmJ42iUHkAaXmqYF4g110"); //System.getenv("APPLITOOLS_API_KEY")
			
			//Use headless mode for Continuous Integration (CI) execution. Use headed mode for local development.
			boolean headless = Boolean.parseBoolean(System.getenv().getOrDefault("HEADLESS", "false"));
			
			eyes_config.setBatch(BATCH);
			
			//add test device using different device viewports
			eyes_config.addBrowser(800, 600, BrowserType.CHROME);
			eyes_config.addBrowser(1600, 1200, BrowserType.FIREFOX);
			eyes_config.addBrowser(1024, 768, BrowserType.SAFARI);
			eyes_config.addDeviceEmulation(DeviceName.Pixel_2, ScreenOrientation.PORTRAIT);
			eyes_config.addDeviceEmulation(DeviceName.Nexus_10, ScreenOrientation.LANDSCAPE);
			
			eyes.setConfiguration(eyes_config);
			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(new ChromeOptions().setHeadless(headless));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
			//the actual testing
			eyes.open(driver, "ACME Bank Web App", "Log into the bank account", new RectangleSize(1200, 600));
			
			driver.get("https://demo.applitools.com");
			
			//verify that the page is already displayed
			eyes.check(Target.window().fully().withName("Login page"));
			
			//perform the test
			driver.findElement(By.id("username")).sendKeys("applibot");
            driver.findElement(By.id("password")).sendKeys("I<3VisualTests");
            driver.findElement(By.id("log-in")).click();
            
            //verify that the page is already displayed
            eyes.check(Target.window().fully().withName("Main page").layout());
            
            // Close Eyes to tell the server it should display the results.
            eyes.closeAsync();
		}catch (Exception ex) {
			ex.printStackTrace();
			if(eyes != null) {
				eyes.abortAsync();
			}
		}
		
		try {
			if(driver != null) {
				driver.quit();
			}
			if(runner != null) {
				TestResultsSummary allTestResults = runner.getAllTestResults();
				System.out.println(allTestResults);
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		
		System.exit(0);
	}

}
