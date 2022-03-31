@UserRegistration
Feature: Registration Flow

  Scenario: Verify the Buyer Registration Flow
    Given "User" launch the application
    Then User navigate to HomePage
    When Click on "Buyer" Register button
    Then verify Register "Buyer" Page details
    When Click on the Start button
    And Enter user credentials for registration
      |username	 |password|
      |pegasusbuyer@gmail.com|Test@1234|
    And Select Privacy Policy Term
    And click on Next button
    Then Verify User sees "Check your corporate email for a message with the code to continue." message on the page
    When User enter the email verification code for "pegasusbuyer@gmail.com" as "buyer"
    And click on Next button
    And Verify User get the QR Auth CODE for authentication for "pegasusbuyer@gmail.com" and enter
    And Click on Create Account button
    Then Verify User navigate to Login Screen
    When Enter User credentials for Login
      |username	 |password|
      |pegasusbuyer@gmail.com|Test@1234|
    And Click on Login Button
    Then Verify User Navigate to authentication page and enter Code for "pegasusbuyer@gmail.com" user
    And Click on Enter button
    Then verify user navigate to Profile Page
    When User logout from profile page
    Then User navigate to HomePage

  Scenario: Verify the seller Registration Flow
    Given "User" launch the application
    Then User navigate to HomePage
    When Click on "Seller" Register button
    Then verify Register "Seller" Page details
    When Click on the Start button
    And Enter user credentials for registration
      |username	 |password|
      |pegasuseller@gmail.com|Test@1234|
    And Select Privacy Policy Term
    And click on Next button
    Then Verify User sees "Check your corporate email for a message with the code to continue." message on the page
    When User enter the email verification code for "pegasuseller@gmail.com" as "seller"
    And click on Next button
    And Verify User get the QR Auth CODE for authentication for "pegasuseller@gmail.com" and enter
    And Click on Create Account button
    Then Verify User navigate to Login Screen
    When Enter User credentials for Login
      |username	 |password|
      |pegasuseller@gmail.com|Test@1234|
    And Click on Login Button
    Then Verify User Navigate to authentication page and enter Code for "pegasuseller@gmail.com" user
    And Click on Enter button
    Then verify user navigate to Profile Page
    When User logout from profile page
    Then User navigate to HomePage
