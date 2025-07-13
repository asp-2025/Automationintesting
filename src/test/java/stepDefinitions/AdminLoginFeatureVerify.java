package stepDefinitions;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.AdminPage;
import utils.TestContextSetup;

public class AdminLoginFeatureVerify {
	
	TestContextSetup testContextSetup;
	AdminPage adminPage;
	String loginError;
	
	public AdminLoginFeatureVerify(TestContextSetup testContextSetup)
	{
		this.testContextSetup = testContextSetup;
	    this.adminPage = testContextSetup.pageObjectManager.getAdminPage();
	}

	@Given("User is on the Admin Login screen")
	public void user_is_on_the_admin_login_screen() {
			
		adminPage.adminLoginScreen();
	    
	}
	@When("^they input invalid username (.+) and password (.+)$")
	public void they_input_invalid_username_and_password (String name,String passwrd) {
	    adminPage.inputAdminCredentials(name, passwrd);
	}
	@When("click on Login button")
	public void click_on_login_button() {
	    adminPage.clickLoginButton();
	}
	@Then("^System should display an error message (.+)$")
	public void system_should_display_an_error_message(String errormsg) {
		adminPage.getErrorMsgFromFeature(errormsg);
	    String actualErrorMsg = adminPage.verifyLoginErrorMsg();
	    Assert.assertEquals(actualErrorMsg, errormsg);
	    
	}


}
