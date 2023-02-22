package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleSearchPage {
	
	private static WebElement ui_element = null;
	
	public static WebElement Search_Textbox(WebDriver driver) {
		ui_element = driver.findElement(By.xpath("//input[@class='gLFyf']"));
		return ui_element;
	}
	
	public static WebElement Search_Button(WebDriver driver) {
		ui_element = driver.findElement(By.xpath("//input[@class='gLFyf']"));
		return ui_element;
	}
}
