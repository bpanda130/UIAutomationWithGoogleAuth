package stepdefinitions;

import com.qa.factory.DriverFactory;
import com.qa.pages.AdminDashboardPage;
import com.qa.pages.ProfileDashboardPage;
import com.qa.util.GlobalData;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class AdminDashboardPageSteps {

    private AdminDashboardPage adminDashboardPage = new AdminDashboardPage(DriverFactory.getDriver());

    @Then("verify Admin navigate to Dashboard page")
    public void verify_admin_navigate_to_dashboard_page() {
        Assert.assertTrue("Required Page component are not appearing correctly on Admin dashboard page.",
                adminDashboardPage.validateDashBoardPage());
    }

    @When("Admin click on the required company message")
    public void admin_click_on_the_required_company_message() {
        String reqCompany = GlobalData.scenarioData.get("CompanyName");
        adminDashboardPage.clickOnRequiredCompanyFromList(reqCompany);
    }

    @Then("verify the required message panel appeared on the page")
    public void verify_the_required_message_panel_appeared_on_the_page() {
        String reqCompany = GlobalData.scenarioData.get("CompanyName");
        Assert.assertTrue("Required components are not appearing on Company Message panel",
                adminDashboardPage.validateMessagePanel(reqCompany, "Review KYC submission"));
    }

    @When("User open Company kyc page")
    public void user_open_company_kyc_page() {
        adminDashboardPage.clickOnOpenKYCPageLink();
    }

    @Then("Company details page opens with {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string} and {string}")
    public void verify_the_required_message_panel_appeared_on_the_page(String emailID, String userRole, String fName, String lName, String position, String companyName, String UENNumber, String TaxNumber, String LinkedInAct) {
        String reqCompany = GlobalData.scenarioData.get("CompanyName");
        Assert.assertTrue("Required components are not appearing on Company Message panel",
                adminDashboardPage.validateCompanyDetails(emailID, userRole, fName, lName, position, reqCompany, UENNumber, TaxNumber, LinkedInAct));
    }

    @Then("Status appeared as {string}")
    public void status_appeared_as(String reqStatus) {
        Assert.assertEquals(reqStatus, adminDashboardPage.getComapnyStatus());
    }

    @When("User click on the {string} button")
    public void user_click_on_the_button(String status) {
        adminDashboardPage.clickOnApproveOrReject(status);
    }
    @When("add Reason for Reject as {string}")
    public void add_reason_for_reject_as(String reason) {
        adminDashboardPage.enterReason(reason);
    }
    @When("Click on Send button")
    public void click_on_send_button() {
        adminDashboardPage.clickOnSendButton();
    }
    @When("User logged Out from Admin portal")
    public void user_logged_out_from_admin_portal() {
        adminDashboardPage.clickOnLogOutFromAdmin();
    }
}
