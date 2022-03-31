package stepdefinitions;

import com.qa.factory.DriverFactory;
import com.qa.pages.LoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

public class LoginPageSteps {

    private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());


    @Then("Verify User navigate to Login Screen")
    public void verify_user_navigate_to_login_screen() {
        loginPage.verifyLoginPageDetails();
    }

    @When("Enter User credentials for Login")
    public void enter_buyer_credentials_for_registration(DataTable dataTable) {
        List<Map<String, String>> credList = dataTable.asMaps();
        String userName = credList.get(0).get("username");
        String password = credList.get(0).get("password");

        loginPage.enterLoginDetails(userName, password);
    }

    @When("Enter User credentials {string} and {string} for Login")
    public void enter_credentials_for_registration(String userName, String password) {
        loginPage.enterLoginDetails(userName, password);
    }
    @When("Click on Login Button")
    public void click_on_login_button() {
        loginPage.clickOnLoginBtn();
    }
    @Then("Verify User Navigate to authentication page and enter Code for {string} user")
    public void verify_user_navigate_to_authentication_page_and_enter_code_for_user(String emailID) {
        loginPage.enterIntroductionCode(emailID);
    }
    @When("Click on Enter button")
    public void click_on_enter_button() {
        loginPage.clickOnEnterBtn();
    }
}
