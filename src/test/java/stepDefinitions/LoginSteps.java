package stepDefinitions;

import com.swaglabs.pages.LoginPage;
import com.swaglabs.drivers.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
    @Given("I open the Swag lab page")
    public void i_open_the_swag_lab_page() {
       new LoginPage(DriverManager.getDriver()).navigateToLoginPage();
    }
    @When("I enter {string} and {string} into the login form")
    public void iEnterAndIntoTheLoginForm(String username, String password) {
        new LoginPage(DriverManager.getDriver())
                .enterUsername(username)
                .enterPassword(password)
                .clickLoginButton();
    }
    @Then("I should be logged in successfully")
    public void i_should_be_logged_in_successfully() {
       new LoginPage(DriverManager.getDriver()).validateSuccessMessage();
    }

    @And("I should see the products page")
    public void iShouldSeeTheProductsPage() {
        new LoginPage(DriverManager.getDriver()).validationProductsPage();

    }

    @Then("I should see an error message {string}")
    public void iShouldSeeAnErrorMessage(String errorType) {
        new LoginPage(DriverManager.getDriver()).validateErrorMessage(errorType);
    }
}
