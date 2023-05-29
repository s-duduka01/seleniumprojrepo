package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(xpath="//a[@title='My Account']")
	private WebElement MyAccountDropDown;
	
	@FindBy(linkText="Login")
	private WebElement LoginOption;
	public WebElement MyAccountDropDown() {
		
		return MyAccountDropDown;
	}	
	public WebElement LoginOption() {
		return LoginOption;
		
	}
}
