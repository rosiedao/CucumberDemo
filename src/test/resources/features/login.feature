@loginCase
Feature: feature to test login functionality

  @loginSuccess
  Scenario: Check login is successful with valid credentials
    Given user opens login page
    When user enters username and password
    And clicks on login button
    Then user is navigated to the homepage

  @loginFail
  Scenario: Check login is successful with empty credentials
    Given user opens login page
    And clicks on login button
    Then user is navigated to the homepage