package pageObject;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class EnquiryForm {
	
	public WebDriver driver;
	public String name = "Robert";
	public String extractedName;
	
	public EnquiryForm(WebDriver driver)
	{
		this.driver = driver;
	}

	By contactName = By.xpath("//input[@id ='name']");
	By contactEmail = By.xpath("//input[@id ='email']");
	By contactPhone = By.cssSelector("#phone");
	By contactSubject = By.cssSelector("#subject");
	By contractDescription = By.cssSelector("#description");
	By submitButton = By.xpath("//button[contains(text(),'Submit')]");
	By submitMsg = By.xpath("//div[@class='card-body p-4']/h3[@class = 'h4 mb-4']");
	By missingFieldErrorMsg = By.xpath("//div[@class = 'alert alert-danger']/p");
	
	public String getTitleHomePage()
		{
			return driver.getTitle();
		}
	
	public void inputFormDetails() throws InterruptedException 
	{
		
		scrollToSubmit();
		
		driver.findElement(contactName).sendKeys(name);
		driver.findElement(contactEmail).sendKeys(name+"123@gmail");
		driver.findElement(contactPhone).sendKeys("075482349872");
		driver.findElement(contactSubject).sendKeys("Booking Info Required");		
		driver.findElement(contractDescription).sendKeys("There is an issue with the booking reference received");
	}
	
	public void missingFormDetails() throws InterruptedException 
	{
		
		scrollToSubmit();
		
		driver.findElement(contactName).sendKeys(name);
		driver.findElement(contactEmail).sendKeys(name+"123@gmail");
		driver.findElement(contactSubject).sendKeys("Booking Info Required");		
		driver.findElement(contractDescription).sendKeys("There is an issue with the booking reference received");
	}
	
	public void scrollToSubmit() throws InterruptedException 
	{
		WebElement submit = driver.findElement(submitButton);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", submit);
		
		/*Both implicit and explicit waits are not working. 
		Getting out of bounds of viewport dimensions, while performing cross-browser testing (firefox browser)*/
		
		Thread.sleep(500);
		Actions actions = new Actions(driver);
		actions.moveToElement(submit).click().perform();
	}
	
	public void verifyFieldAfterRefresh()
	{
		WebElement inputFormField = driver.findElement(contactName);
		
		String value = inputFormField.getAttribute("value");
		
		if(value==null || value.trim().isEmpty())
		{
			System.out.println("The field is empty");
		}
		else
		{
			System.out.println("The field is not empty: " + value);
		}
		
	}
	public void clickSubmit()
	{
		driver.findElement(submitButton).click();
	}
	
	public String verifySubmitMsg()
	{
		String msg = driver.findElement(submitMsg).getText();
		System.out.println(msg);
		
		msg = msg.trim().replaceAll("[^a-zA-Z\\s]", "");
		
		String[] words = msg.split("\\s+");
		
		if(words.length>0)
		{
			String extractedName = words[words.length-1];
			System.out.println("Extracted Name: " + extractedName);
			return extractedName;
		}
		
		return null;
		
	}
	
	public void fieldLevelErrorMsg()
	{
		String phoneFieldErrorMsg =  driver.findElement(missingFieldErrorMsg).getText();
		System.out.println(phoneFieldErrorMsg);
	}
}
