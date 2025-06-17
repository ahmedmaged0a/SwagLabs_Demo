package com.swaglabs.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {
    private Waits() {
        // Private constructor to prevent instantiation
    }

    // Method to wait for an element to be present in the DOM
    @Step("Waiting for element to be present: {locator}")
    public static WebElement waitForElementTobePresent(WebDriver driver, By locator) {

        // Log the action
        LogsManager.info("Waiting for element to be present: " + locator.toString());
        // Implement logic to wait for the element to be present in the DOM
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(_ -> driver.findElement(locator));
    }

    // Method to wait for an element to be visible
    @Step("Waiting for element to be visible: {locator}")
    public static WebElement waitForElementToBeVisible(WebDriver driver, By locator) {
        // Log the action
        LogsManager.info("Waiting for element to be visible: " + locator.toString());
        // Implement logic to wait for the element to be visible
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(_ -> {
                    WebElement element = waitForElementTobePresent(driver, locator);
                    return element.isDisplayed() ? element : null;
                });
    }
    @Step("Waiting for element to be Located: {locator}")
    public static WebElement waitForElementToBeLocated(WebDriver driver, By locator) {
        // Log the action
        LogsManager.info("Waiting for element to be located: " + locator.toString());
        // Implement logic to wait for the element to be located
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(_ -> driver.findElement(locator));
    }

    // Method to wait for an element to be clickable
    @Step("Waiting for element to be clickable: {locator}")
    public static WebElement waitForElementToBeClickable(WebDriver driver, By locator) {
        // Log the action
        LogsManager.info("Waiting for element to be clickable: " + locator.toString());
        // Implement logic to wait for the element to be clickable
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(_ -> {
                    WebElement element = waitForElementToBeVisible(driver, locator);
                    return element.isEnabled() ? element : null;
                });
    }


}
