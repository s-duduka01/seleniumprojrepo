package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	 WebDriver driver;
	public Properties prop;

	public WebDriver intializeDriver() throws IOException {
		 prop = new Properties();
		String proPath = System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties";

		FileInputStream fis = new FileInputStream(proPath);
		prop.load(fis);
		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("Chrome")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("IE")) {

			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(15));

		return driver;
	}

// reusable method ( add two parameters}
public String takeSreenShot(String testName , WebDriver driver) throws IOException {
	
	File sourceFile =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	String destinationFilePath = System.getProperty("user.dir")+"\\screenshots\\"+testName+".png";//("+testName+".png"(which replace the failed test case name.
	FileUtils.copyFile(sourceFile,new File( destinationFilePath));
	return  destinationFilePath;
	
}
}