package pageObject;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {
	
	public WebDriver driver;
	public AdminPage adminPage;
	public BookRoomPage bookRoomPage;
	public EnquiryForm enquiryForm;
	
	public PageObjectManager (WebDriver driver)
	{
		this.driver = driver;
	}

	public AdminPage getAdminPage() 
	{
		adminPage = new AdminPage(driver);
		return adminPage;
	}
	
	public BookRoomPage getBookRoomPage() 
	{
		bookRoomPage = new BookRoomPage(driver);
		return bookRoomPage;
	}
	
	public EnquiryForm getEnquiryForm() 
	{
		enquiryForm = new EnquiryForm(driver);
		return enquiryForm;
	}
	
}
