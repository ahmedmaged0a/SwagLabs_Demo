package com.swaglabs.utils;

import com.swaglabs.drivers.DriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class BrowserActions {
    private BrowserActions() {
        // Private constructor to prevent instantiation
    }

    // Method to navigate to a URL
    @Step("Navigate to URL: {url}")
    public static void navigateToUrl(WebDriver driver, String url) {
        driver.get(url);
        LogsManager.info("Navigated to URL: " + url);
    }
// Method to get the current URL
    @Step("Get the current URL")
    public static String getCurrentUrl(WebDriver driver) {
        LogsManager.info("Current URL: " + driver.getCurrentUrl());
        return driver.getCurrentUrl();
    }
    // Method to refresh the current page
    public static void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    // Method to go back to the previous page
    public static void goBack(WebDriver driver) {
        driver.navigate().back();
    }
    // Method to go forward to the next page
    public static void goForward(WebDriver driver) {
        driver.navigate().forward();
    }
    // Method to maximize the browser window
    public static void maximizeWindow(WebDriver driver) {
        driver.manage().window().maximize();
    }
    // Method to get the title of the current page
    @Step("Get the title of the current page")
    public static String getTitle(WebDriver driver) {
        LogsManager.info("Current Page Title: " + driver.getTitle());
        return driver.getTitle();
    }
    // Method to close the browser
    public static void closeBrowser(WebDriver driver) {
        if (driver != null) {
            LogsManager.info("Closing the browser.");
            driver.quit();
            DriverManager.unload();
        }
    }

}
