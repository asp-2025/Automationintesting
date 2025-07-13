package utils;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import pageObject.PageObjectManager;

public class TestContextSetup {
	
	public WebDriver driver;
	
	public PageObjectManager pageObjectManager;
	public TestBase testBase;
	public String name;
	public String extractedName;
	public String loginError;
	public String targetMonthYear;
	public String targetDay;

	
	public TestContextSetup() throws IOException
	{
		testBase = new TestBase();
		pageObjectManager = new PageObjectManager(testBase.WebDriverManager());
	}
	

}
