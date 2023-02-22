package Configurations;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import test.ExtentReportsDemo;

public class PropertiesFile {

	static Properties prop;
	static String projectPath = System.getProperty("user.dir");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Get_Properties();
		Set_Properties();
	}
	
	public static void Get_Properties() {
		try {
			prop = new Properties();
			InputStream input = new FileInputStream(projectPath+"/src/test/java/Configurations/config.properties");
			prop.load(input);
			System.out.println(prop.getProperty("browser"));
			ExtentReportsDemo.browserName = prop.getProperty("browser").toString();
		}catch(Exception ex) {
			System.out.println("Error: "+ex.getMessage());
			System.out.println("Cause: "+ex.getCause());
			ex.printStackTrace();
		}
	}
	
	public static void Set_Properties() {
		try {
			OutputStream output = new FileOutputStream(projectPath+"/src/test/java/Configurations/config.properties");
			prop.setProperty("browser", "chrome");
			prop.store(output, null);
			System.out.println(prop.getProperty("browser"));
		}catch(Exception ex) {
			System.out.println("Error: "+ex.getMessage());
			System.out.println("Cause: "+ex.getCause());
			ex.printStackTrace();
		}
	}

}
