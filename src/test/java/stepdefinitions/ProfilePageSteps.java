package stepdefinitions;

import com.qa.factory.DriverFactory;
import com.qa.pages.ProfilePage;
import com.qa.util.GlobalData;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProfilePageSteps {

    private ProfilePage profilePage = new ProfilePage(DriverFactory.getDriver());

    @Then("verify user navigate to Profile Page")
    public void verify_user_navigate_to_profile_page() {
        Assert.assertTrue(profilePage.validateProfilePageDetails("Hello!", "Let’s get you on-boarded to the platform"),
                "Required form title is not appearing on the Profile page.");
    }

    @When("User logout from profile page")
    public void user_profile_logout() {
        profilePage.profileLogout();
    }

    @When("On Profile page user enter {string}, {string}, {string}, {string}, {string}, {string} and {string}")
    public void user_enter_profile_Details(String fName, String lName, String position, String companyName, String UENNumber, String TaxNumber, String LinkedInAct)
    {
        String reqCompanyName = companyName+"_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        GlobalData.scenarioData.put("CompanyName", reqCompanyName);
        profilePage.enterFirstName(fName);
        profilePage.enterLastName(lName);
        profilePage.enterCompanyPosition(position);
        profilePage.enterCompanyName(reqCompanyName);
        profilePage.enterCompanyRegNumber(UENNumber);
        profilePage.enterCompanyTaxRegNumber(TaxNumber);
        profilePage.enterLinkedInActDetails(LinkedInAct);
    }
    @When("On Profile page user sees {string}, {string}, {string}, {string}, {string}, {string} and {string}")
    public void user_verify_Profile_details(String fName, String lName, String position, String companyName, String UENNumber, String TaxNumber, String LinkedInAct)
    {
        String reqCompanyName = GlobalData.scenarioData.get("CompanyName");
        Assert.assertTrue(profilePage.verifyProfileEntries(fName, lName, position, reqCompanyName, UENNumber, TaxNumber, LinkedInAct),
                "Required data is not properly saved in Profile page");
    }

    @Then("verify Send To Approve button appears enable")
    public void verify_Send_To_Approve_Button()
    {
        Assert.assertTrue(profilePage.verifySentToApproveBtn(),
                "Send To Approval Button still appearing in disable mode.");
    }

    @When("User click on Save button")
    public void user_click_on_save_button()
    {
        profilePage.ClickOnSaveButton();
    }

    @When("User gets an Alert with message {string}")
    public void user_gets_alert_with_message(String alertMessage)
    {
        Assert.assertEquals(alertMessage, profilePage.verifyAlertPresence(), "User is not seeing the required Saved message.");
    }

    @When("User click on Send To Approval button")
    public void user_click_on_send_to_approval_button()
    {
        profilePage.ClickOnSentToApproveBtn();
    }
}
