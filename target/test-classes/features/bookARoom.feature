Feature: Book a room functionality

@BookSingleRoomValidDetails
  Scenario Outline: Book a single room with valid user details
    Given User is on the Home screen
    And input valid Check in date <checkintargetmonthyear> <checkintargetday> 
    And input valid Check out date <checkouttargetmonthyear> <checkouttargetday> to select the available single room
    When they input valid user details to reserve the room
    And click on Reserve Now button
    Then System should display a booking confirmation message to the user
  
  Examples:
  | checkintargetmonthyear | checkintargetday | checkouttargetmonthyear |checkouttargetday |
  |   Month July, 2025     |   11   |      Month July, 2025   |   12 |
  
     
