Feature: Admin Login functionality

@AdminLoginError
  Scenario Outline: Admin login with invalid credentials
    Given User is on the Admin Login screen
    When they input invalid username <uname> and password <passkey>
    And click on Login button
    Then System should display an error message <errormessage>

    Examples: 
      | uname  | passkey |  errormessage  |
      | Robert77  | Password01| Invalid credentials |
     