@loginCase
Feature: feature to test login functionality
  #Background:
  #Given user opens login page

  @loginSuccess
  Scenario: Check login is successful with valid credentials has no parameters
    #Given user opens login page
    When user enters username and password
    And clicks on login button
    Then user is navigated to the homepage

  @loginWithParam
  Scenario: Check login is successful with valid credentials has parameters
    #Given user opens login page
    When user enters username with value "dung@test.com" and password with value "admin"
    And clicks on login button
    Then user is navigated to the homepage

  @loginWithDataTable
  Scenario Outline: Check login is successful with <Username> and <Password>
    #Given user opens login page
    When user enters username with value "<Username>" and password with value "<Password>"
    And clicks on login button
    Then user is navigated to the homepage

    Examples:
      | Username       | Password |
      | dung@test.com  | admin    |
      | dung1@test.com | admin    |

  @loginWithDataTableInStep
  Scenario: Check login is successful with data table in step
    #Given user opens login page
    When user enters username and password as below data
      | Username      | Password |
      | dung@test.com | admin    |
    And clicks on login button
    Then user is navigated to the homepage

  @loginFail
  Scenario: Check login is successful with empty credentials
    #Given user opens login page
    And clicks on login button
    Then user is navigated to the homepage