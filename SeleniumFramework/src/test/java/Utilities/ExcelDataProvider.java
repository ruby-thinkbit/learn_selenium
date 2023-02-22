package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import Pages.GoogleSearchPageObjects;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ExcelDataProvider {
	public static Object[][] Test_Data(String excelPath, String sheetName) {
		ExcelUtilities excel = new ExcelUtilities(excelPath, sheetName);
		int row_count = excel.Get_Row_Count(); 
		int col_count = excel.Get_Col_Count();
		
		Object data[][] = new Object[row_count-1][col_count];
		
		for(int row = 1; row < row_count; row++) {
			for(int col = 0; col < col_count; col++) {
				String datas = excel.Get_Cell_Data(row, col);
				//System.out.print(datas+" | ");
				data[row-1][col] = datas;
			}
			System.out.println();
		}
		return data;
	}
	
	static WebDriver driver = null;
	
	public static String browserName = null;
	
	@BeforeSuite
	public void Setup_Tests() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	
	@DataProvider(name = "1stTestData")
	public Object[][] Get_Data() {
		Object data[][] = Test_Data(System.getProperty("user.dir")+"/test-input/Untitled spreadsheet.xlsx","Sheet1");
		return data;
	}
	
	@org.testng.annotations.Test(dataProvider = "1stTestData")
	public void Test(String duration, String task) throws InterruptedException {
		//System.out.println(duration + " | " + task);
		driver.get("https://opensource-demo.orangehrmlive.com/");
		Thread.sleep(10000);
		driver.findElement(By.xpath("/html/body/div/div[1]/div/div[1]/div/div[2]/div[2]/form/div[1]/div/div[2]/input")).sendKeys(duration);
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div/div[1]/div/div[1]/div/div[2]/div[2]/form/div[2]/div/div[2]/input")).sendKeys(task);
		
	}
	@AfterSuite
	public void Close_Setup() {
		driver.close();
		driver.quit();
	}
}
