package stepDefinitions;

import com.swaglabs.drivers.DriverManager;
import com.swaglabs.utils.BrowserActions;
import com.swaglabs.utils.LogsManager;
import com.swaglabs.utils.PropertiesUtils;
import com.swaglabs.utils.ScreenShotUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


import static com.swaglabs.utils.AllureUtils.attachLogToAllure;
import static com.swaglabs.utils.PropertiesUtils.getPropertyValue;

public class Hooks {
    @Before
    public void setUp() {
        // Load properties file
        PropertiesUtils.loadProperties();
        String browserName = getPropertyValue("browserType");
        DriverManager.createDriverInstance(browserName);
    }
    @After
    public void tearDown(Scenario scenario) {
        attachLogToAllure();
        String scenarioName = scenario.getName();

        if (scenario.isFailed()) {
            ScreenShotUtils.takeScreenshot("failed-" + scenarioName);
            LogsManager.error("Test case " + scenarioName + " failed.");
        } else if (scenario.getStatus().toString().equals("PASSED")) {
            ScreenShotUtils.takeScreenshot("Passed-" + scenarioName);
            LogsManager.info("Test case " + scenarioName + " passed successfully.");
        } else if (scenario.getStatus().toString().equals("SKIPPED")) {
            ScreenShotUtils.takeScreenshot("skipped-" + scenarioName);
            LogsManager.warn("Test case " + scenarioName + " was skipped.");
        } else {
            LogsManager.info("Test Method: " + scenarioName + " has an unknown status.");
        }

        // Close the browser after each scenario
        if (DriverManager.getDriver() != null) {
            BrowserActions.closeBrowser(DriverManager.getDriver());
        }
    }
}