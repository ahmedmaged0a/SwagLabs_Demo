package cucumberOptions;

import io.cucumber.java.After;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import com.swaglabs.Listeners.TestNGListeners;


@Listeners(TestNGListeners.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefinitions", "cucumberOptions"},
        tags = "@Smoke or @Regression",
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
