package stepDefinitions;

import java.time.Duration;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.BookRoomPage;
import utils.TestContextSetup;

public class BookARoomFeatureVerify {
	
	TestContextSetup testContextSetup;
	BookRoomPage bookRoomPage;
	public String targetMonthYear = "July 2025";
	public String targetDay = "15";
	
	
	public BookARoomFeatureVerify(TestContextSetup testContextSetup)
	{
		this.testContextSetup = testContextSetup;
		this.bookRoomPage = testContextSetup.pageObjectManager.getBookRoomPage();
	}

	
	@Given("^input valid Check in date (.+) (.+)")
	public void input_valid_check_in_date(String checkinmonthYear, String checkinday) {
	   bookRoomPage.clickBookingLink();
	   bookRoomPage.scrollTocheckAvailabilityBtn();
	   bookRoomPage.inputCheckinDate(checkinmonthYear, checkinday);
	}
	
	@Given("^input valid Check out date (.+) (.+) to select the available single room$")
	public void input_valid_check_out_date_to_select_the_available_single_room(String checkoutmonthYear, String checkoutday) throws InterruptedException {
	   
	  bookRoomPage.inputCheckoutDate(checkoutmonthYear, checkoutday);
	  testContextSetup.testBase.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	  bookRoomPage.clickAvailabilityBtn();
	  bookRoomPage.clickSingleRoomSelect();
	  bookRoomPage.bookSingleRoom();
	}
	
	@When("they input valid user details to reserve the room")
	public void they_input_valid_user_details_to_reserve_the_room() {
      bookRoomPage.inputReservationUserDetails();
	}
	@When("click on Reserve Now button")
	public void click_on_reserve_now_button() throws InterruptedException {
	    bookRoomPage.clickReserveNowBtn();
	}
	@Then("System should display a booking confirmation message to the user")
	public void system_should_display_a_booking_confirmation_message_to_the_user() {
	    bookRoomPage.bookingConfirmMsg();
	}
	
}
