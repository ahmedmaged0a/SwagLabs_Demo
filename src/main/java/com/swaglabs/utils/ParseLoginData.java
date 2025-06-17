package com.swaglabs.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ParseLoginData {
    private ParseLoginData() {
        // Private constructor to prevent instantiation
    }

    private static final By LoginDataFile = By.xpath("//p[@class='oxd-text oxd-text--p']"); // Update with actual path or locator
    private static List<WebElement> loginDataElements;
    // Method to parse login data from a file or other source
    public static String getUserName(WebDriver driver) {
        Waits.waitForElementToBeVisible(driver, LoginDataFile);
        Scrolling.scrollToElement(driver, LoginDataFile);
        loginDataElements = driver.findElements(LoginDataFile);
        //use stream to get the username from the list of elements
        return loginDataElements.stream().filter(u -> u.getText().contains("Username")).findFirst()
                .map(u -> u.getText().split(":")[1].trim()).orElseThrow(() -> new RuntimeException("Username not found"));

    }
    public static String getPassword(WebDriver driver) {
        loginDataElements = driver.findElements(LoginDataFile);
        return loginDataElements.stream().filter(p -> p.getText().contains("Password")).findFirst()
                .map(p -> p.getText().split(":")[1].trim()).orElseThrow(() -> new RuntimeException("Password not found"));
    }
}
