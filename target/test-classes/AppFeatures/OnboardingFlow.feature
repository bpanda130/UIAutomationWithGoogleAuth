@UserOnBoarding
Feature: User On-Boarding Flow

  Scenario Outline: Verify the Buyer On-boarding Flow
    Given "User" launch the application
    Then User navigate to HomePage
    When Click on Login link on Home Page
    Then Verify User navigate to Login Screen
    When Enter User credentials "<emailID>" and "<password>" for Login
    And Click on Login Button
    Then Verify User Navigate to authentication page and enter Code for "<emailID>" user
    And Click on Enter button
    Then verify user navigate to Profile Page
    When On Profile page user enter "<FirstName>", "<LastName>", "<Position>", "<CompanyName>", "<UENNumber>", "<TaxNumber>" and "<LinkedInAct>"
    Then verify Send To Approve button appears enable
    When User click on Save button
    Then User gets an Alert with message "Your data has been saved for later"
    When User logout from profile page
    Then User navigate to HomePage
    When Click on Login link on Home Page
    Then Verify User navigate to Login Screen
    When Enter User credentials "<emailID>" and "<password>" for Login
    And Click on Login Button
    Then Verify User Navigate to authentication page and enter Code for "<emailID>" user
    And Click on Enter button
    Then verify user navigate to Profile Page
    And On Profile page user sees "<FirstName>", "<LastName>", "<Position>", "<CompanyName>", "<UENNumber>", "<TaxNumber>" and "<LinkedInAct>"
    When User click on Send To Approval button
    Then verify "We are reviewing your CDD/KYC data" message appeared on the profile Notification section
    And verify "<emailID>" user sees "<User_role>", "<FirstName>", "<LastName>", "<Position>", "<CompanyName>", "<UENNumber>", "<TaxNumber>" and "<LinkedInAct>" on Dashboard
    When User logout from profile page
    Then User navigate to HomePage
    When "Admin" launch the application
    When Enter User credentials "<AdminEmailID>" and "<password>" for Login
    And Click on Login Button
    Then Verify User Navigate to authentication page and enter Code for "<AdminEmailID>" user
    And Click on Login Button
    And verify Admin navigate to Dashboard page
    When Admin click on the required company message
    Then verify the required message panel appeared on the page
    When User open Company kyc page
    Then Company details page opens with "<emailID>", "<User_role>", "<FirstName>", "<LastName>", "<Position>", "<CompanyName>", "<UENNumber>", "<TaxNumber>" and "<LinkedInAct>"
    And Status appeared as "Review"
    When User click on the "Reject" button
    And add Reason for Reject as "Test Reject flow"
    And Click on Send button
    Then Status appeared as "Rejected"
    When User logged Out from Admin portal
    Given "User" launch the application
    Then User navigate to HomePage
    When Click on Login link on Home Page
    Then Verify User navigate to Login Screen
    When Enter User credentials "<emailID>" and "<password>" for Login
    And Click on Login Button
    Then Verify User Navigate to authentication page and enter Code for "<emailID>" user
    And Click on Enter button
    And verify the Reject notification appeared on Profile Page
    When User click on the Edit button
    Then verify Send To Approve button appears enable
    When User click on Send To Approval button
    Then verify "We are reviewing your CDD/KYC data" message appeared on the profile Notification section
    When User logout from profile page
    Then User navigate to HomePage
    When "Admin" launch the application
    When Enter User credentials "<AdminEmailID>" and "<password>" for Login
    And Click on Login Button
    Then Verify User Navigate to authentication page and enter Code for "<AdminEmailID>" user
    And Click on Login Button
    And verify Admin navigate to Dashboard page
    When Admin click on the required company message
    Then verify the required message panel appeared on the page
    When User open Company kyc page
    And Status appeared as "Review"
    When User click on the "Approve" button
    Then Status appeared as "Approved"
    When User logged Out from Admin portal
    Given "User" launch the application
    Then User navigate to HomePage
    When Click on Login link on Home Page
    Then Verify User navigate to Login Screen
    When Enter User credentials "<emailID>" and "<password>" for Login
    And Click on Login Button
    Then Verify User Navigate to authentication page and enter Code for "<emailID>" user
    And Click on Enter button

    Examples:
      | emailID                 | AdminEmailID                      | password      |User_role |FirstName   | LastName    | Position      | CompanyName       | UENNumber | TaxNumber | LinkedInAct |
      #| pegasuseller@gmail.com  | pegasusapprover@gmail.com         | Test@1234     |SELLER    | TestSeller | Automation  | QA Automation | PegasusAutomation | TEST1234  | 80091988  | TestAccount |
      | pegasusbuyer@gmail.com  | pegasusapprover@gmail.com         | Test@1234     |BUYER     | TestBuyer  | Automation  | QA Automation | PegasusAutomation | TEST1234  | 80091988  | TestAccount |
