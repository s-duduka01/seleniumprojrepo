package stepdefinitions;

import java.io.IOException;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.AccountPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import resources.Base;

public class Login extends Base {
	WebDriver driver;
	LoginPage loginpage;
	AccountPage accountPage;
	LandingPage landingPage;

	@Given("^Open any browser$")
	public void open_any_browser() throws IOException {
		driver = intializeDriver();
	}

	@And("^Navigated to the page$")
	public void navigated_to_the_page() throws InterruptedException {

		driver.get(prop.getProperty("url"));
		landingPage = new LandingPage(driver);
		landingPage.MyAccountDropDown().click();
		landingPage.LoginOption().click();
		Thread.sleep(3000);

	}

	@When("^I enter emailaddress as \"([^\"]*)\" and password as \"([^\"]*)\" into the feilds$")
	public void i_enter_emailaddress_as_something_and_password_as_something_into_the_feilds(String emailaddress,
			String password) {

		driver.findElement(By.id( "input-email")).sendKeys(emailaddress);
		driver.findElement(By.id("input-password")).sendKeys(password);

	}

	@And("^user clicks on login button$")
	public void user_clicks_on_login_button() {

		driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();
	}

	@Then("^verify is able to successfully login$")
	public void verify_is_able_to_successfully_login() {
		accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.editYourAccountInformation().isDisplayed());

	}

	@After
	public void Closebrowser() {
		driver.close();
	}

}
