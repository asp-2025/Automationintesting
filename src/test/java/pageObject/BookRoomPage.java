package pageObject;

import java.time.Duration;

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookRoomPage {

	public WebDriver driver;
	WebDriverWait wait;
	public String targetMonthYear = "July 2025";
	public String targetDay = "15";

	public BookRoomPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	By bookingLink = By.linkText("Booking");
	By checkInField = By.xpath("(//div[@class='react-datepicker-wrapper dateWrapper'])[1]");
	By checkInMonth = By.cssSelector(".react-datepicker__month");
	By checkInNextMonth = By.cssSelector("button[aria-label='Next Month']");
	By checkInDays = By.cssSelector(".react-datepicker__day");

	By checkOutField = By.xpath("(//div[@class='react-datepicker-wrapper dateWrapper'])[2]");
	By checkOutMonth = By.cssSelector(".react-datepicker__month");
	By checkOutNextMonth = By.cssSelector("button[aria-label='Next Month']");
	By checkOutDays = By.cssSelector(".react-datepicker__day");

	By checkAvailabilityButton = By.xpath("//button[text() = 'Check Availability']");
	By roomBookNow = By.xpath("//div[@class='container']/div[2]/div[1]//a[contains(text(), 'Book')]");
	//By roomBookNow = By.cssSelector(".room-card .btn.btn-primary");
	By pageHeader = By.xpath("//div/h1");
	By reserveNow = By.xpath("//button[contains(text(),'Reserve Now')]");
	
	By firstName = By.cssSelector("input[placeholder='Firstname']");
	By lastName = By.cssSelector("input[placeholder='Lastname']");
	By email = By.xpath("//input[@placeholder='Email']");
	By phone = By.xpath("//input[@name='phone']");
	By reserveNowAfterInput = By.xpath("//button[contains(text(),'Reserve Now')]");
	
	By bookingConfirm = By.xpath("//h2[contains(text(),'Confirmed')]");
	By bookingConfirmMsg = By.xpath("//div[@class ='col-lg-4']");
	By phoneNumberFieldError = By.xpath("//div[@class = 'alert alert-danger']/ul");

	public void clickBookingLink() {
		driver.findElement(bookingLink);

	}

	public void scrollTocheckAvailabilityBtn() {
		WebElement checkAvailBtn = driver.findElement(checkAvailabilityButton);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", checkAvailBtn);

		Actions actions = new Actions(driver);
		actions.moveToElement(checkAvailBtn).click().perform();
	}

	public void inputCheckinDate(String checkintargetmonthyear, String checkintargetday) {
		driver.findElement(checkInField).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".react-datepicker__month-container")));

		int maxAttempts = 12;
		int attempts = 0;

		while (attempts < maxAttempts) {
			WebElement monthLabel = driver.findElement(checkInMonth);
			String ariaLabel = monthLabel.getAttribute("aria-label");

			if (ariaLabel != null && ariaLabel.contains(checkintargetmonthyear)) {
				break;
			}

			driver.findElement(checkInNextMonth).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(checkInMonth));
			attempts++;
		}
		if (attempts == maxAttempts) {
			throw new RuntimeException("Check-in month not found: " + checkintargetmonthyear);
		}

		
		WebElement visibleMonth = driver.findElement(checkInMonth);

		List<WebElement> dayElements = visibleMonth.findElements(checkInDays);

		for (WebElement day : dayElements) {
			String dayText = day.getText().trim();
			String classes = day.getAttribute("class");
			System.out.println("Day: " + dayText + ", Classes: " + classes);

			boolean isCurrentMonth = !classes.contains("react-datepicker__day--outside-month");
			
			wait.until(ExpectedConditions.elementToBeClickable(checkInDays));
  
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", day);
            try {
			if (isCurrentMonth && dayText.equals(checkintargetday)) {
				System.out.println("Clicking day: " + dayText);
				day.click();
				break;
			}
			} catch (ElementClickInterceptedException e) {
			    // If the click is intercepted, use JavaScript to click
			    js.executeScript("arguments[0].click();", day);
			}
		}
	}

	public void inputCheckoutDate(String checkouttargetmonthyear, String checkouttargetday) {
		driver.findElement(checkOutField).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".react-datepicker__month-container")));

		int maxAttempts = 12;
		int attempts = 0;

		while (attempts < maxAttempts) {
			WebElement monthLabel = driver.findElement(checkOutMonth);
			String ariaLabel = monthLabel.getAttribute("aria-label");

			if (ariaLabel != null && ariaLabel.contains(checkouttargetmonthyear)) {
				break;
			}

			driver.findElement(checkOutNextMonth).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(checkOutMonth));
			attempts++;
		}
		if (attempts == maxAttempts) {
			throw new RuntimeException("Check-out month not found: " + checkouttargetmonthyear);
		}

		WebElement visibleMonth = driver.findElement(checkOutMonth);

		List<WebElement> dayElements = visibleMonth.findElements(checkOutDays);

		for (WebElement day : dayElements) {
			String dayText = day.getText().trim();
			String classes = day.getAttribute("class");
			System.out.println("Day: " + dayText + ", Classes: " + classes);

			boolean isCurrentMonth = !classes.contains("react-datepicker__day--outside-month");
			
			wait.until(ExpectedConditions.elementToBeClickable(checkInDays));
			  
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", day);
            try {
			if (isCurrentMonth && dayText.equals(checkouttargetday)) {
				System.out.println("Clicking day: " + dayText);
				day.click();
				break;
			}
			} catch (ElementClickInterceptedException e) {
			    // If the click is intercepted, use JavaScript to click
			    js.executeScript("arguments[0].click();", day);
			}

		}

	}
	
	public void clickAvailabilityBtn() 
	{
		driver.findElement(checkAvailabilityButton).click();
		
	}
	
	public void clickSingleRoomSelect() throws InterruptedException
	{
		scrollToBookNow();
		
		wait.until(ExpectedConditions.elementToBeClickable(pageHeader));
	}
	
	public void scrollToBookNow() throws InterruptedException
	{
		
		int attempts = 0;
		while(attempts<3)
		{
		try {
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='container']/div[2]/div[1]//a[contains(text(), 'Book')]")));
			
			
			 String script = "let button = document.querySelector('//div[@class='container']/div[2]/div[1]//a[contains(text(), 'Book')]');" +
		                "if (button) {" +
		                    "button.scrollIntoView({block: 'center'});" +
		                   "setTimeout(() => button.click();"+
		                    "return true;"+
		                "}" +
		                   " return false;" 
		                ;

		            Boolean success = (Boolean) ((JavascriptExecutor) driver).executeScript(script);
		            if (Boolean.TRUE.equals(success)) {
		                System.out.println("Successfully clicked 'Book now' via pure JS.");
		                return;
		            } else {
		                System.out.println("JS could not find the button.");
		                attempts++;
		            }

		
		
		} catch (Exception e) {
            System.out.println("Attempt \" + (attempts + 1) + \" failed: \" + e.getMessage()");
            
        }
		attempts++;
		try {
			Thread.sleep(500);
		}catch (InterruptedException ignored) {}
		}
	
		throw new RuntimeException("Failed to click 'Book Now' after multiple attempts.");
	}
	
	public void bookSingleRoom() throws InterruptedException
	{
		WebElement reserveRoom = driver.findElement(reserveNow);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", reserveRoom);
		
		
		Thread.sleep(500);
		Actions actions = new Actions(driver);
		actions.moveToElement(reserveRoom).click().perform();
		
		scrollToFirstName();
	}
	
	public void scrollToFirstName()
	{
		WebElement fname = driver.findElement(firstName);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", fname);		
		
		Actions actions = new Actions(driver);
		actions.moveToElement(fname).click().perform();
	}
	
	public void inputReservationUserDetails()
	{
		driver.findElement(firstName).sendKeys("Robert");
		driver.findElement(lastName).sendKeys("John");
		driver.findElement(email).sendKeys("robertjohn123@gmail.com");
		driver.findElement(phone).sendKeys("07656745678");
		
	}
	
	public void clickReserveNowBtn() throws InterruptedException
	{
		scrollToReserveBtnAfterInput();
		//driver.findElement(reserveNowAfterInput).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(bookingConfirm));
	}
	
	public void scrollToReserveBtnAfterInput() throws InterruptedException
	{
		WebElement reserveAfterInput = driver.findElement(reserveNowAfterInput);
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", reserveAfterInput);
		
		/*Both implicit and explicit waits are not working. 
		Getting out of bounds of viewport dimensions, while performing cross-browser testing (firefox browser)*/
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(reserveNowAfterInput));
		//Thread.sleep(500);
		Actions actions = new Actions(driver);
		actions.moveToElement(reserveAfterInput).click().perform();
	}
	
	
	public void bookingConfirmMsg()
	{
		String confirmationMsg = driver.findElement(bookingConfirmMsg).getText().replace("Return home", " ");
		System.out.println(confirmationMsg);
	}
	

}
