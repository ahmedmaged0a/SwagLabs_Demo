package com.swaglabs.Listeners;

import com.swaglabs.utils.LogsManager;
import com.swaglabs.utils.ScreenShotUtils;
import com.swaglabs.utils.BrowserActions;
import com.swaglabs.drivers.DriverManager;
import io.cucumber.java.After;
import io.cucumber.testng.PickleWrapper;
import io.qameta.allure.Allure;
import org.testng.*;

import java.io.File;

import static com.swaglabs.utils.AllureUtils.attachLogToAllure;
import static com.swaglabs.utils.FilesUtils.CleanDirectory;
import static com.swaglabs.utils.FilesUtils.DeleteFiles;
import static com.swaglabs.utils.PropertiesUtils.loadProperties;


public class TestNGListeners implements ITestListener, IExecutionListener, IInvokedMethodListener {
    File allureResults = new File("test-outputs/allure-results");
    File Screenshots = new File("test-outputs/screenshots");

    @Override
    public void onExecutionStart() {
        LogsManager.info("Test Execution Started .......");
        loadProperties();
        DeleteFiles(allureResults);
        CleanDirectory(Screenshots);
    }

    @Override
    public void onExecutionFinish() {
        LogsManager.info("Test Execution Finished .......");
        // Removed browser closing from here for parallel execution
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        String[] groups = testResult.getMethod().getGroups();
        for (String group : groups) {
            Allure.label("tag", group);
        }
    }
//    @Override
//    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
//        if (method.isTestMethod()) {
//            attachLogToAllure();
//            String scenarioName = "Unknown Scenario";
//            if (testResult.getParameters() != null && testResult.getParameters().length > 0) {
//                Object param = testResult.getParameters()[0];
//                if (param instanceof PickleWrapper) {
//                    scenarioName = ((PickleWrapper) param).getPickle().getName();
//                }
//            }
//
//            switch (testResult.getStatus()) {
//                case ITestResult.SUCCESS:
//                    ScreenShotUtils.takeScreenshot("Passed-" + scenarioName);
//                    LogsManager.info("Test case " + scenarioName + " passed successfully.");
//                    break;
//                case ITestResult.FAILURE:
//                    ScreenShotUtils.takeScreenshot("failed-" + scenarioName);
//                    LogsManager.error("Test case " + scenarioName + " failed with exception: " + testResult.getThrowable().getMessage());
//                    break;
//                case ITestResult.SKIP:
//                    ScreenShotUtils.takeScreenshot("skipped-" + scenarioName);
//                    LogsManager.warn("Test case " + scenarioName + " was skipped.");
//                    break;
//                default:
//                    LogsManager.info("Test Method: " + method.getTestMethod().getMethodName() + " has an unknown status.");
//            }
//        }
//    }
}
