package stepDefinitions;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.EnquiryForm;
import utils.TestContextSetup;

public class EnquiryFormVerify {
	
	public WebDriver driver;
	TestContextSetup testContextSetup;
	EnquiryForm enquiryForm;
	String name = "Robert";
	String extractedName;
	
	
	public EnquiryFormVerify(TestContextSetup testContextSetup)
	{
		this.testContextSetup = testContextSetup;
		this.enquiryForm = testContextSetup.pageObjectManager.getEnquiryForm();
	}
	
	@Given("User is on the Home screen")
	public void user_is_on_the_home_screen() {
	   Assert.assertTrue(enquiryForm.getTitleHomePage().contains("Restful-booker-platform"));
	  
	}
	
	@When("they input valid user inputs to the enquiry form")
	public void they_input_valid_user_inputs_to_the_enquiry_form() throws InterruptedException {
	    enquiryForm.inputFormDetails();
	}
	
	@When("click on Submit button")
	public void click_on_submit_button() {
	   enquiryForm.clickSubmit();
	}
	
	@Then("System should display a confirmation message to the user")
	public void system_should_display_a_confirmation_message_to_the_user() {
		testContextSetup.testBase.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	   enquiryForm.verifySubmitMsg();
	   
	   Assert.assertEquals(testContextSetup.extractedName, testContextSetup.name);
	   
	}
	
	@Then("refresh the page to check the form fields are reset to blank")
	public void refresh_the_page_to_check_the_form_fields_are_reset_to_blank() throws IOException, InterruptedException {
	   testContextSetup.testBase.WebDriverManager().navigate().refresh();
	   enquiryForm.scrollToSubmit();
	   enquiryForm.verifyFieldAfterRefresh();
	   
	}

	@When("they input valid user inputs to the enquiry form, except one of the fields")
	public void they_input_valid_user_inputs_to_the_enquiry_form_except_one_of_the_fields() throws InterruptedException {
	 enquiryForm.missingFormDetails();
	 
	}

	@Then("System should display an error message, stating user to input the missing detail")
	public void system_should_display_an_error_message_stating_user_to_input_the_missing_detail() {
		enquiryForm.clickSubmit();
		enquiryForm.fieldLevelErrorMsg();
	}


}
