package stepdefinitions;

import com.qa.factory.DriverFactory;
import com.qa.pages.ProfileDashboardPage;
import com.qa.pages.ProfilePage;
import com.qa.util.GlobalData;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class ProfileDashboardPageSteps {

    private ProfileDashboardPage profileDashboardPage = new ProfileDashboardPage(DriverFactory.getDriver());


    @Then("verify {string} message appeared on the profile Notification section")
    public void verify_profile_notification_message(String profileNotificationMessage)
    {
        Assert.assertTrue(profileDashboardPage.verifyNotificationMessageOnProfile(profileNotificationMessage),
                "Required Notification message not appearing correctly on Profile page");
    }

    @Then("verify {string} user sees {string}, {string}, {string}, {string}, {string}, {string}, {string} and {string} on Dashboard")
    public void verify_profile_dashboard_Details(String emailID, String userRole, String fName, String lName, String position, String companyName, String UENNumber, String TaxNumber, String LinkedInAct)
    {
        String reqCompanyName = GlobalData.scenarioData.get("CompanyName");
        Assert.assertTrue(profileDashboardPage.verifyProfileDashboardDetails(emailID, userRole, fName, lName, position, reqCompanyName, UENNumber, TaxNumber, LinkedInAct),
                "Required Notification message not appearing correctly on Profile page");
    }

    @Then("verify the Reject notification appeared on Profile Page")
    public void verify_Rejection_Notification_On_Dashboard()
    {
        Assert.assertTrue(profileDashboardPage.validateRejectionMessageOnProfile(),
                "Required Rejection Notification Message is not appearing on Dashboard");
    }

    @When("User click on the Edit button")
    public void user_click_on_edit_button()
    {
        profileDashboardPage.clickOnEditButton();
    }
}
