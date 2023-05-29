package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import resources.Base;

public class TwoTest extends Base {
	 public WebDriver driver;

	@Test
	public void twoTest() throws IOException, InterruptedException {
	        System.out.println("s-duduka01 has updated the code")
		System.out.println("TestTwo");
		driver = intializeDriver();
		driver.get("https://tutorialsninja.com/demo/");
		Thread.sleep(2000);
		driver.close();
	}

}
