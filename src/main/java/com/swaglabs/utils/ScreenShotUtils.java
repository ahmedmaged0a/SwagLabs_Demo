package com.swaglabs.utils;

import com.swaglabs.drivers.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;

public class ScreenShotUtils {
    public static final String SCREENSHOT_PATH = "test-outputs/screenshots/";
    private ScreenShotUtils() {
        super();
        // Private constructor to prevent instantiation
    }
    public static void takeScreenshot(String ScreenshotName) {

        try {
            File screenshotFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
            File destinationFile = new File(SCREENSHOT_PATH+ ScreenshotName + ".png");
            FileUtils.copyFile(screenshotFile, destinationFile);
            LogsManager.info("Screenshot taken: " + SCREENSHOT_PATH+ ScreenshotName + ".png");
            AllureUtils.attachScreenshotToAllure(ScreenshotName);
        } catch (Exception e) {
            LogsManager.error("Failed to take screenshot: " + e.getMessage());
        }
    }
}
