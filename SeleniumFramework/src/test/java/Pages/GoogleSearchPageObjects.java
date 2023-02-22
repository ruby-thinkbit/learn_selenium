package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSearchPageObjects {
	WebDriver driver = null;
	
	By txtSearch = By.xpath("//input[@class='gLFyf']");
	By btnSearch = By.xpath("//input[@class='gLFyf']");
	//By result = By.xpath("//h3[contains(.,'TestNG')]//ancestor::a[1]");
	
	public GoogleSearchPageObjects(WebDriver driver) {
		this.driver = driver;
	}
	
	public void Set_Text_in_searchbox(String text) {
		driver.findElement(txtSearch).sendKeys(text);
	}
	
	public void Find_Text_from_searchbox() {
		driver.findElement(btnSearch).sendKeys(Keys.RETURN);
	}
	
	public void Go_to_first_result() {
		//driver.findElement(result).sendKeys(Keys.RETURN);
		
		WebElement resultz = new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(.,'TestNG')]//ancestor::a[1]")));
		System.out.println(resultz.getText());
	}
}