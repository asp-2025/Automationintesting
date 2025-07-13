Feature: Submit an enquiry form

@SubmitEnquiryValidInput
  Scenario: Submit an enquiry form with valid user inputs and verify the state of the form after submission
    Given User is on the Home screen
    When they input valid user inputs to the enquiry form
    And click on Submit button
    Then System should display a confirmation message to the user
    And refresh the page to check the form fields are reset to blank
    
@SubmitEnquiryMissingInput   
    Scenario: Submit an enquiry form with missing user inputs
    Given User is on the Home screen
    When they input valid user inputs to the enquiry form, except one of the fields
    And click on Submit button
    Then System should display an error message, stating user to input the missing detail
    
   
    