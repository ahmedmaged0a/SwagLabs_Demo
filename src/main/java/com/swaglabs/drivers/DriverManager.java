package com.swaglabs.drivers;

import com.swaglabs.utils.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static org.testng.AssertJUnit.fail;

public class DriverManager {
    // ThreadLocal to hold the WebDriver instance for each thread
    // This allows for parallel execution of tests without interference between threads
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private DriverManager() {
        // Private constructor to prevent instantiation
    }

    //Create instance of WebDriver for the current thread
    @Step("Create WebDriver instance for the current thread")
    public static void createDriverInstance(String browserName) {
        WebDriver driver = BrowserFactory.getBrowserDriver(browserName);
        LogsManager.info(browserName + " created");
        setDriver(driver);
    }

    // Method to set the WebDriver instance for the current thread
    @Step("Set WebDriver instance for the current thread")
    public static void setDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
    }

    // Method to get the WebDriver instance for the current thread
    @Step("Get WebDriver instance for the current thread")
    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            LogsManager.error("WebDriver instance is not set for the current thread.");
            fail("WebDriver instance is not set for the current thread. Please set it using DriverManager.setDriver(driver) before calling getDriver().");
        }
        return driverThreadLocal.get();
    }
    public static void unload() {
        driverThreadLocal.remove();
    }
}
