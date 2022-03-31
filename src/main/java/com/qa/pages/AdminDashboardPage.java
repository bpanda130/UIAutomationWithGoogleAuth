package com.qa.pages;

import com.qa.util.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminDashboardPage {

    //private WebDriver driver;
    ElementUtil elementUtil;

    private final By adminDashboardTitle = By.xpath("//section[@class='messages__profile-title']/h3");
    private final By companies_link = By.xpath("//section[@id='logo']//p");
    private final By dashboardMsgContainer = By.xpath("//div[contains(@class,'messages__row__right-container')]//div[@class='generic-message']");
    private final By companyName = By.xpath("//div[contains(@class,'messages__row__right-container')]//div[@class='title-container__text']");
    private final By compDashboardTabName = By.xpath("//div[@class='p-tabview-nav-content']/ul/li/a/span");
    private final By compDashboardTab = By.xpath("//div[@class='p-tabview-nav-content']/ul/li/a");
    private final By dashboardMessageBody = By.xpath("//div[@class='item-row']/div");
    private final By goToCompanyPagelink = By.xpath("//div[@class='item-row']/following-sibling::div/a");
    private final By newMessageButton = By.xpath("//button[span[text()='NEW MESSAGE']]");
    private final By companyList = By.xpath("//section[@class='grid messages__row']//section/div[3]/div//span");
    private final By companyNamenPosition = By.xpath("//div[contains(@class,'company-name bold')]");
    private final By userInfodetails = By.xpath("//div[contains(@class,'user-info__data grid grid-nogutter')]");
    private final By companystatus = By.xpath("//div[span[contains(@class,'status-badge')]]");
    private final By rejectButton = By.xpath("//button[span[text()='REJECT']]");
    private final By approveButton = By.xpath("//button[span[text()='APPROVE']]");
    private final By AddReasonTextArea = By.xpath("//h3[text()='Add a reason']/following-sibling::form//textarea");
    private final By sendButton = By.xpath("//button[span[text()='SEND']]");
    private final By profileIcon = By.xpath("//div[@class='user-area flex justify-content-between align-items-center']/div/div/svg-icon");
    private final By logoutLink = By.xpath("//div[@class='user-area__options grid grid-nogutter ng-star-inserted']/div[2]/span[1]");


    //Constructor
    public AdminDashboardPage(WebDriver driver) {
        //this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    public boolean validateDashBoardPage() {
        return elementUtil.doGetText(adminDashboardTitle).equalsIgnoreCase("Manage activity and tasks on Dealcierge") &&
                elementUtil.getDisplayedStatus(companies_link);
    }

    public void clickOnRequiredCompanyFromList(String companyName) {
        elementUtil.clickOnRequiredItemsFromList(companyName, companyList);
    }

    public boolean validateMessagePanel(String CompanyName, String reviewMessage) {
        System.out.println(CompanyName);
        System.out.println(elementUtil.doGetText(companyName).contains(CompanyName));
        return elementUtil.doGetText(dashboardMessageBody).equalsIgnoreCase(reviewMessage) &&
                elementUtil.doGetText(companyName).contains(CompanyName) &&
                elementUtil.getDisplayedStatus(dashboardMsgContainer) &&
                elementUtil.getDisplayedStatus(goToCompanyPagelink) &&
                elementUtil.getDisplayedStatus(newMessageButton);
    }

    public void clickOnOpenKYCPageLink() {
        elementUtil.doClick(goToCompanyPagelink);
    }

    public boolean validateCompanyDetails(String emailID, String userRole, String fName, String lName, String position, String companyName, String UENNumber, String TaxNumber, String LinkedInAct) {
        return elementUtil.doGetText(companyNamenPosition).contains(companyName) &&
                elementUtil.doGetText(companyNamenPosition).contains(userRole) &&
                elementUtil.doGetText(userInfodetails).contains(fName) &&
                elementUtil.doGetText(userInfodetails).contains(lName) &&
                elementUtil.doGetText(userInfodetails).contains(emailID) &&
                elementUtil.doGetText(userInfodetails).contains(position);
    }

    public String getComapnyStatus() {
        elementUtil.waitForRequiredSec(3);
        return elementUtil.doGetText(companystatus);
    }

    public void clickOnApproveOrReject(String status) {
        if(status.equalsIgnoreCase("Reject"))
            elementUtil.doClick(rejectButton);
        else if(status.equalsIgnoreCase("Approve"))
            elementUtil.doClick(approveButton);
    }

    public void enterReason(String reason) {
        elementUtil.doSendKeys(AddReasonTextArea, reason);
    }

    public void clickOnSendButton() {
        elementUtil.doClick(sendButton);
    }

    public void clickOnLogOutFromAdmin() {
        elementUtil.doClick(profileIcon);
        elementUtil.waitForRequiredSec(3);
        elementUtil.doClick(logoutLink);
    }
}
