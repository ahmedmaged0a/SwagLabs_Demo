package com.swaglabs.utils;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Validations {
    private Validations() {
        // Private constructor to prevent instantiation
    }

    public static void validateTrue(boolean condition, String message) {
        Assert.assertTrue(condition, message);
    }

    public static void validateFalse(boolean condition, String message) {
        Assert.assertFalse(condition, message);
    }
    @Step("Validating that the actual value is equal to the expected value")
    public static void validateEquals(Object actual, Object expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }

    public static void validateNotEquals(Object actual, Object expected, String message) {
        Assert.assertNotEquals(actual, expected, message);
    }

    public static void validateNull(Object object, String message) {
        Assert.assertNull(object, message);
    }

    public static void validateNotNull(Object object, String message) {
        Assert.assertNotNull(object, message);
    }

    public static void validateContains(String actual, String expected, String message) {
        Assert.assertTrue(actual.contains(expected), message);
    }

    public static void validateNotContains(String actual, String expected, String message) {
        Assert.assertFalse(actual.contains(expected), message);
    }
    @Step("Validating page URL")
    public static void validatePageUrl(WebDriver driver, String expectedUrl, String message) {
        Assert.assertEquals(BrowserActions.getCurrentUrl(driver), expectedUrl, message);
    }

    public static void validatePageTitle(WebDriver driver, String expectedTitle, String message) {
        Assert.assertEquals(BrowserActions.getTitle(driver), expectedTitle, message);
    }
}
