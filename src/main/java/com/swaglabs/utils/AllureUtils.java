package com.swaglabs.utils;

import io.qameta.allure.Allure;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class AllureUtils {
    public static final String ALLURE_RESULTS_PATH = "test-outputs/allure-results";
    private AllureUtils() {
        super();
    }

    public static void attachLogToAllure() {
       try{
           File logFile = FilesUtils.getLatestFileFromDirectory(LogsManager.LOGS_PATH);
           if(!logFile.exists()) {
               LogsManager.warn("Log file does not exist: " + logFile.getAbsolutePath());
               return;
           }
           Allure.addAttachment("logs.log", Files.readString(Path.of(logFile.getAbsolutePath())));
       }catch (Exception e) {
              LogsManager.error("Failed to attach log file to Allure: " + e.getMessage());
       }
    }
    public static void attachScreenshotToAllure(String screenshotName) {
        try {
            Allure.addAttachment(screenshotName, Files.newInputStream(Path.of(ScreenShotUtils.SCREENSHOT_PATH + screenshotName + ".png")));
        } catch (Exception e) {
            LogsManager.error("Failed to attach screenshot to Allure: " + e.getMessage());
        }
    }
}
