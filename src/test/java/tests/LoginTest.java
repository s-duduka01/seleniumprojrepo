package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageobjects.AccountPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import resources.Base;

public class LoginTest extends Base {
	Logger log;
	public WebDriver driver;

	@Test
	public void login() throws IOException, InterruptedException {

		LandingPage landingPage = new LandingPage(driver);

		landingPage.MyAccountDropDown().click();
		log.debug("clicked on myAccount dropdown");
		landingPage.LoginOption().click();
		log.debug("clicked on LoginOption");
		Thread.sleep(3000);

		LoginPage loginpage = new LoginPage(driver);
		loginpage.emailAddressField().sendKeys(prop.getProperty("email"));
		log.debug("email Address got entered");
		loginpage.passwordField().sendKeys(prop.getProperty("password"));
		log.debug("password got entered");
		loginpage.ClickOnLoginButton().click();
		log.debug("Clicked on LoginButton");
		AccountPage accountPage = new AccountPage(driver);

		/*
		 * String actualResult = null; ( if we have more than one set on data we have to
		 * use below condition(we cant apply properties file) try { if (
		 * accountpage.editAccountInformationOption().isDisplayed()) actualResult =
		 * "Successful";
		 * 
		 * }catch(Exception e) { actualResult = "Failure"; }
		 */
		Assert.assertTrue(accountPage.editYourAccountInformation().isDisplayed());
		log.info("login Test got pass");
	}

	@BeforeMethod
	public void openApplication() throws IOException {

		log = LogManager.getLogger(LoginTest.class.getName());

		driver = intializeDriver();
		log.debug(" Browser got Launched");
		driver.get(prop.getProperty("url"));
		log.debug("Naviagted to Omayoblogspot");
	}

	@AfterMethod
	public void closure() {
		driver.close();
		log.debug("Close the browser");
	}

}
/*
 * if we have multiple sets of data , we have to go with below @DataProvider (
 * not from the properties)
 * 
 * @DataProvider public object[][] getLoginData(){
 * object[][]data={{"omsairam@gmail.com","omsairam","Successful"},{
 * "dummy@test.com","1234","failure"}} return data; also have to add try , catch
 * and if condition
 */
