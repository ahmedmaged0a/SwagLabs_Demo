package com.swaglabs.drivers;

import com.swaglabs.utils.PropertiesUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Map;
import java.util.Objects;


public class BrowserFactory {

        //returns a WebDriver instance based on the browser name provided
    @Step("Get WebDriver instance for browser: {browserName}")
    public static WebDriver getBrowserDriver(String browserName) {
        if (Objects.isNull(browserName) || browserName.isEmpty()) {
            throw new IllegalArgumentException("Browser name cannot be null or empty");
        }
        return switch (browserName.toUpperCase()) {
            case "CHROME" -> {
                ChromeOptions chromeOptions = getChromeOptions();;
                yield new ChromeDriver(chromeOptions);
            }
            case "FIREFOX" -> {
                FirefoxOptions firefoxOptions = getFirefoxOptions();
                yield new FirefoxDriver(firefoxOptions);
            }
            default -> {
                EdgeOptions edgeOptions = getEdgeOptions();
                yield new EdgeDriver(edgeOptions);
            }
        };
    }

    private static EdgeOptions getEdgeOptions() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("start-maximized"); // Start the browser maximized
        edgeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        edgeOptions.addArguments("--disable-notifications"); // Disable notifications
        edgeOptions.addArguments("--disable-popup-blocking"); // Disable pop-up blocking
        edgeOptions.addArguments("--disable-infobars"); // Disable infobars
        edgeOptions.addArguments("--disable-dev-shm-usage");
        edgeOptions.addArguments("--disable-extensions");
        Map<String, Object> prefs = Map.of("profile.default_content_setting_values.notifications", 2,
                "credential_enable_service", false,
                "profile.password_manager_enabled", false,
                "autofill.profile.enable", false);
        edgeOptions.setExperimentalOption("prefs", prefs);
        if(!PropertiesUtils.getPropertyValue("executionType").equalsIgnoreCase("local"))
        {
            edgeOptions.addArguments("--headless");
        }
        return edgeOptions;
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized"); // Start the browser maximized
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        chromeOptions.addArguments("--disable-notifications"); // Disable notifications
        chromeOptions.addArguments("--disable-popup-blocking"); // Disable pop-up blocking
        chromeOptions.addArguments("--disable-infobars"); // Disable infobars
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--disable-extensions");
        Map<String, Object> prefs = Map.of("profile.default_content_setting_values.notifications", 2,
                "credential_enable_service", false,
                "profile.password_manager_enabled", false,
                "autofill.profile.enable", false);
        chromeOptions.setExperimentalOption("prefs", prefs);
        if(!PropertiesUtils.getPropertyValue("executionType").equalsIgnoreCase("local"))
        {
            chromeOptions.addArguments("--headless");
        }
        return chromeOptions;
    }

    public static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("start-maximized"); // Start the browser maximized
        firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        firefoxOptions.addArguments("--disable-notifications"); // Disable notifications
        firefoxOptions.addArguments("--disable-popup-blocking"); // Disable pop-up blocking
        firefoxOptions.addArguments("--disable-infobars"); // Disable infobars
        firefoxOptions.addArguments("--disable-dev-shm-usage");
        firefoxOptions.addArguments("--disable-extensions");
        firefoxOptions.setAcceptInsecureCerts(true);
        if(!PropertiesUtils.getPropertyValue("executionType").equalsIgnoreCase("local"))
        {
            firefoxOptions.addArguments("--headless");
        }
        return firefoxOptions;
    }
}
