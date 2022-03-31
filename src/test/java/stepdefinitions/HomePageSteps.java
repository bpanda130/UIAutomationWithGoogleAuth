package stepdefinitions;

import com.qa.factory.DriverFactory;
import com.qa.pages.HomePage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class HomePageSteps {

	private HomePage homePage = new HomePage(DriverFactory.getDriver());

	@Given("{string} launch the application")
	public void user_launch_the_application(String role)
	{
		homePage.lauchURL(role);
	}

	@When("User navigate to HomePage")
	public void verify_Home_Page_Details() {
		Assert.assertTrue(homePage.verifyHomePageDetails(), "All the required elements are not appearing on Home page");
	}

	@When("Click on Login link on Home Page")
	public void click_on_login_link_homePage() {
		homePage.clickOnLogin();
	}

	@When("Click on {string} Register button")
	public void click_on_register_button(String role) {
		homePage.clickOnBuyerRegistration(role);
	}

	@Then("verify Register {string} Page details")
	public void verify_register_yourself_page_details(String userRole) {
		String registerMsg = homePage.verifyRegisterPageDetails();
		if(userRole.equalsIgnoreCase("Seller"))
			Assert.assertEquals(registerMsg, "I'm looking to sell");
		else if(userRole.equalsIgnoreCase("Buyer"))
			Assert.assertEquals(registerMsg, "I'm looking to buy");
	}

	@When("Click on the Start button")
	public void click_on_the_start_button() {
		homePage.clickOnStartButton();
	}
	@When("Enter user credentials for registration")
	public void enter_buyer_credentials_for_registration(DataTable dataTable) {
		List<Map<String, String>> credList = dataTable.asMaps();
		String userName = credList.get(0).get("username");
		String password = credList.get(0).get("password");

		homePage.enterRegistrationDetails(userName, password);
	}
	@When("Select Privacy Policy Term")
	public void select_privacy_policy_term() {
		homePage.selectPrivacyPolicy();
	}
	@When("click on Next button")
	public void click_on_next_button() {
		homePage.clickOnNextBtn();
	}
	@Then("Verify User sees {string} message on the page")
	public void verify_user_sees_message_on_the_page(String actMessage) {
		Assert.assertEquals(actMessage, homePage.getRegistrationSuccessMessage());
	}
	@When("User enter the email verification code for {string} as {string}")
	public void buyer_enter_the_email_verification_code(String emailID, String role) {
		homePage.enterEmailAuthCode(emailID, role);
	}
	@Then("Verify User get the QR Auth CODE for authentication for {string} and enter")
	public void verify_buyer_get_the_qr_code_for_authentication(String emailID) {
		homePage.verifyQRCodeOnScreenAndEnter(emailID);
	}
	@When("Click on Create Account button")
	public void click_on_create_account_button() {
		homePage.clickOnAcreatAccount();
	}
}
