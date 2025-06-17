package com.swaglabs.utils;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementActions {
    private ElementActions() {
        // Private constructor to prevent instantiation
    }

    // Add methods for common element actions like click, sendKeys, etc.

    // Method to click on an element
    @Step("Clicking on element: {locator}")
    public static void clickElement(WebDriver driver, By locator) {
        Waits.waitForElementToBeClickable(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        findElement(driver, locator).click();
    }

    // Method to send keys to an element
    @Step("Sending data to element: {locator} with data: {Data}")
    public static void sendDataToElement(WebDriver driver, By locator, String Data) {
        Waits.waitForElementToBeVisible(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        // Clear the existing text before sending new data
        findElement(driver, locator).sendKeys(Keys.CONTROL + "a");
        findElement(driver, locator).sendKeys(Keys.DELETE);
        findElement(driver, locator).sendKeys(Data);
        // Log the action
        LogsManager.info("Sending data to element: " + locator+ " with data: " + Data);
    }

    // Method to get text from an element
    @Step("Getting text from element: {locator}")
    public static String getElementText(WebDriver driver, By locator) {
        Waits.waitForElementToBeVisible(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        // Log the action
        LogsManager.info("Getting text from element: " + locator, " with data: " + findElement(driver, locator).getText());
        return findElement(driver, locator).getText();
    }

    @Step("Select option from dropdown: {locator} with value: {value}")
    public static void selectOptionFromDropdown(WebDriver driver, By locator, String value) {
        Waits.waitForElementToBeVisible(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        WebElement dropdown = findElement(driver, locator);
        dropdown.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@role='option' and .='" + value + "']") // Adjust if needed
        ));
        option.click();

        LogsManager.info("Selected option: " + value + " from dropdown: " + locator);
    }

    @Step("Handle AutoComplete: {locator} with value: {value}")
    public static void handleAutoComplete(WebDriver driver, By locator, String value, By Option) {
        Waits.waitForElementToBeVisible(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        WebElement autoCompleteField = findElement(driver, locator);
        autoCompleteField.click();
        autoCompleteField.clear();
        autoCompleteField.sendKeys(value);
        Waits.waitForElementToBeLocated(driver, Option);
        findElement(driver, Option).click();
        LogsManager.info("Handled AutoComplete for: " + locator + " with value: " + value);
    }

    // Method to find an element
    @Step("Finding element: {locator}")
    public static WebElement findElement(WebDriver driver, By locator) {
        // Log the action
        LogsManager.info("Finding element: " + locator.toString());
        return driver.findElement(locator);
    }
}
