package cucumberOptions;

import io.cucumber.java.After;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.TestNGCucumberRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import com.swaglabs.Listeners.TestNGListeners;
import com.swaglabs.drivers.DriverManager;
import com.swaglabs.utils.BrowserActions;

@Listeners(TestNGListeners.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefinitions", "cucumberOptions"},
        tags = "@validLogin or @invalidLogin or @Smoke or @Regression",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty.html",
        },
        monochrome = true
)
public class TestNGRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

}
