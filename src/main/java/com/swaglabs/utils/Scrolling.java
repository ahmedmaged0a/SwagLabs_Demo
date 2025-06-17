package com.swaglabs.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;


public class Scrolling {
    private Scrolling() {
        // Private constructor to prevent instantiation
    }

    // Method to scroll to a specific element
    @Step("Scrolling to element: {locator}")
    public static void scrollToElement(WebDriver driver, By locator) {
        LogsManager.info("Scrolling to " + locator.toString());
        // Use JavaScriptExecutor to scroll to the element
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ElementActions.findElement(driver, locator));
        scrollUp(driver, 200); // Adjust the scroll position to ensure the element is fully visible
    }

    // Method to scroll down the page by a specific amount
    public static void scrollDown(WebDriver driver, int pixels) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + pixels + ");");
    }

    // Method to scroll up the page by a specific amount
    public static void scrollUp(WebDriver driver, int pixels) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-" + pixels + ");");
    }
}
