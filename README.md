# 🧪 SwagLabs Login Page Automation with Cucumber

This project demonstrates end-to-end test automation for the **Login functionality** of the [Swag Labs](https://www.saucedemo.com/) web application using **Cucumber BDD**, **Java**, **Selenium WebDriver**, and **TestNG**.

## 📌 Project Objective

Automate and validate different login scenarios using a behavior-driven approach to ensure a robust and maintainable test framework.

---

## 📋 Feature File: `Login.feature`

### Feature: Login functionality  
As a user, I want to log in to the Swag Lab application so that I can access my account.

### ✅ Scenarios Covered

#### 🔹 Login with valid credentials `@validLogin @Smoke`
Scenario Outline: Login with valid credentials
  Given I open the Swag lab page
  When I enter "<username>" and "<password>" into the login form
  Then I should be logged in successfully
  And I should see the products page

Examples:
  | username      | password     |
  | standard_user | secret_sauce |
  
#### 🔹 Login with invalid credentials @invalidLogin @Regression
Scenario Outline: Login with invalid credentials
  Given I open the Swag lab page
  When I enter "<username>" and "<password>" into the login form
  Then I should see an error message "<errorMessage>"

Examples:
  | username      | password     | errorMessage       |
  | standard_user | WrongPass    | invalidcredentials |
  | WrongUser     | secret_sauce | invalidcredentials |

⚠️ Login with empty credentials @invalidLogin @Regression
Scenario Outline: Login with Empty credentials
  Given I open the Swag lab page
  When I enter "<username>" and "<password>" into the login form
  Then I should see an error message "<errorMessage>"

Examples:
  | username  | password  | errorMessage  |
  |           | WrongPass | emptyusername |
  | WrongUser |           | emptypassword |


🛠 Tech Stack
  Java 11+
  Maven
  Selenium WebDriver
  Cucumber (Gherkin)
  TestNG
  Page Object Model (POM)
  Jenkins (optional CI integration)

  

  
