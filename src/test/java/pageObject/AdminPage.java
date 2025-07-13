package pageObject;

import java.time.Duration;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminPage {
	
	public WebDriver driver;
	public String loginError;
	
	public AdminPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	By adminLink = By.xpath("//li[@class = 'nav-item']/a[@href='/admin']");

	By username = By.xpath("//input[@id='username']");
	By password = By.cssSelector("#password");
	By loginButton = By.xpath("//button[contains(text(),'Login')]");
	By errorMsg = By.xpath("//div/div[contains(text(),'Invalid credentials')]");
	
	public String adminLoginScreen()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(adminLink));
		
		driver.findElement(adminLink).click();
		return driver.getTitle();
	}
	
	public void inputAdminCredentials(String uname, String passkey)
	{
		driver.findElement(username).sendKeys(uname);
		driver.findElement(password).sendKeys(passkey);
	}
	
	public void clickLoginButton()
	{
		driver.findElement(loginButton).click();
		
	}
	
	public String verifyLoginErrorMsg()
	{		
		String loginError = driver.findElement(errorMsg).getText();
		return loginError;
	}
	
	public String getErrorMsgFromFeature(String errormessage)
	{
		System.out.println(errormessage);
		return errormessage;
	}
}
