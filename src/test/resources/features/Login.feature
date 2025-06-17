Feature: Login functionality
  As a user
  I want to log in to the Swag lab application
  So that I can access my account

  @validLogin @Smoke
  Scenario Outline: Login with valid credentials
    Given I open the Swag lab page
    When I enter "<username>" and "<password>" into the login form
    Then I should be logged in successfully
    And I should see the products page
    Examples:
      | username      | password     |
      | standard_user | secret_sauce |


    @invalidLogin @Regression
    Scenario Outline: Login with invalid credentials
    Given I open the Swag lab page
    When I enter "<username>" and "<password>" into the login form
    Then I should see an error message "<errorMessage>"
    Examples:
      | username      | password     | errorMessage       |
      | standard_user | WrongPass    | invalidcredentials |
      | WrongUser     | secret_sauce | invalidcredentials |


  @invalidLogin @Regression
  Scenario Outline: Login with Empty credentials
    Given I open the Swag lab page
    When I enter "<username>" and "<password>" into the login form
    Then I should see an error message "<errorMessage>"
    Examples:
      | username  | password  | errorMessage  |
      |           | WrongPass | emptyusername |
      | WrongUser |           | emptypassword |