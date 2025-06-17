package com.swaglabs.pages;

import com.swaglabs.utils.ElementActions;
import com.swaglabs.utils.PropertiesUtils;
import com.swaglabs.utils.Validations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;

    //Locators for the login page elements
    private final By usernameLocator = By.cssSelector("#user-name");
    private final By passwordLocator = By.cssSelector("#password");
    private final By loginButtonLocator = By.cssSelector("#login-button");

    private final By errorMessageLocator = By.cssSelector("h3[data-test='error']");
    private final By errorMessageEmptyLocator = By.cssSelector("h3[data-test='error']");

    private final By successMessageLocator = By.cssSelector(".app_logo");
    private final By productsPageLocator = By.cssSelector(".title");

    //Constructor to initialize the WebDriver instance
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to navigate to the login page
    public void navigateToLoginPage() {
        driver.get(PropertiesUtils.getPropertyValue("baseURL"));
    }
    // Enter username
    public LoginPage enterUsername(String username) {
        ElementActions.sendDataToElement(driver, usernameLocator, username);
        return this;
    }
    // Enter password
    public LoginPage enterPassword(String password) {
        ElementActions.sendDataToElement(driver, passwordLocator, password);
        return this;
    }
    // Click the login button
    public LoginPage clickLoginButton() {
        ElementActions.clickElement(driver, loginButtonLocator);
        return this;
    }
    public String getErrorMessage(String errorType) {
        return switch (errorType.toLowerCase()) {
            case "empty" -> ElementActions.getElementText(driver, errorMessageEmptyLocator);
            case "invalid" -> ElementActions.getElementText(driver, errorMessageLocator);
            default -> "Unknown error type";
        };
    }
    // Method to get the success message after login
    public String getSuccessMessage() {
        return ElementActions.getElementText(driver, successMessageLocator);
    }
    // Method to get the products page title
    public String getProductsPageTitle() {
        return ElementActions.getElementText(driver, productsPageLocator);
    }
    //Validate error message and success message
    public void validateSuccessMessage() {
        //Variables to store expected messages
        String successExpectedMessage = "Swag Labs";
        Validations.validateContains(getSuccessMessage(), successExpectedMessage, "Success message is not correct");
    }
    public void validationProductsPage(){
        String expectedTitle = "Products";
        Validations.validateEquals(getProductsPageTitle(), expectedTitle, "Products page title is not correct");
    }
    public void validateErrorMessage(String errorType) {
        String emptyUserName = "Username is required";
        String emptyPassword = "Password is required";
        switch (errorType.toLowerCase()) {
            case "emptyusername":
                Validations.validateContains(getErrorMessage("empty"), emptyUserName, "Empty username error message is not correct");
                break;
            case "emptypassword":
                Validations.validateContains(getErrorMessage("empty"), emptyPassword, "Empty password error message is not correct");
                break;
            case "invalidcredentials":
                Validations.validateContains(getErrorMessage("invalid"), "Epic sadface: Username and password do not match any user in this service", "Invalid credentials error message is not correct");
                break;
            default:
                throw new IllegalArgumentException("Unknown error type: " + errorType);
        }
    }


}
