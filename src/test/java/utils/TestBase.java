package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TestBase {

	public WebDriver driver;

	
	public WebDriver WebDriverManager() throws IOException {
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//test//resources//global.properties");

		Properties prop = new Properties();
		prop.load(fis);
		String Url = prop.getProperty("TestUrl");
		String browser = prop.getProperty("browser");
		


		if (driver == null) 
		{
			if (browser.equalsIgnoreCase("chrome")) {
		
				driver = new ChromeDriver();
			}
			if(browser.equalsIgnoreCase("firefox"))
			{
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//src//test//resources//geckodriver.exe");
				String firefoxBinaryPath = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
				FirefoxOptions options = new FirefoxOptions();
				options.setBinary(firefoxBinaryPath);

			    driver = new FirefoxDriver(options);
				
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get(Url);
			driver.manage().window().setSize(new Dimension(1280, 2000));
		}
		return driver;
	}



}
