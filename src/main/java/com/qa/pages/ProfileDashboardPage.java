package com.qa.pages;

import com.qa.util.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfileDashboardPage {

    ElementUtil elementUtil;

    private final By notificationSection = By.xpath("//div[@class='grid grid-nogutter']//div[@class='notification-bar__container col']");
    private final By companyDetailsOnDashboard = By.xpath("//div[@class='grid grid-nogutter']//div[@class='col-12 flex align-items-center company-name bold']");
    private final By userDetailsOnDashboard = By.xpath("//div[@class='grid grid-nogutter']//div[@class='col-10 user-info__data grid grid-nogutter']");
    private final By companyRegistOnDetails = By.xpath("//div[@class='grid col-12 grid-nogutter'][2]/div[2]/div");
    private final By comapnyLinkedInTaxDetails = By.xpath("//div[@class='grid grid-nogutter col-12']");
    private final By RejectionMessage = By.xpath("//div[div[text()=' CDD/KYC for your company has been rejected. Please see reasons and guidance on next steps below. ']]");
    private final By companyProfileEditBtn = By.xpath("//button[span[text()='EDIT']]");

    //Constructor
    public ProfileDashboardPage(WebDriver driver) {
        elementUtil = new ElementUtil(driver);
    }

    public boolean verifyNotificationMessageOnProfile(String notificationMsg) {
        return elementUtil.doGetText(notificationSection).contains(notificationMsg);
    }

    public boolean verifyProfileDashboardDetails(String emailID, String userRole, String fName, String lName, String position, String companyName, String UENNumber, String TaxNumber, String LinkedInAct) {

        return elementUtil.doGetText(userDetailsOnDashboard).contains(fName) &&
                elementUtil.doGetText(userDetailsOnDashboard).contains(lName) &&
                elementUtil.doGetText(userDetailsOnDashboard).contains(position) &&
                elementUtil.doGetText(userDetailsOnDashboard).contains(emailID) &&
                elementUtil.doGetText(companyDetailsOnDashboard).contains(companyName) &&
                elementUtil.doGetText(companyDetailsOnDashboard).contains(userRole) &&
                elementUtil.doGetText(companyRegistOnDetails).contains(UENNumber) &&
                elementUtil.doGetText(comapnyLinkedInTaxDetails).contains(TaxNumber) &&
                elementUtil.doGetText(comapnyLinkedInTaxDetails).contains(LinkedInAct);
    }

    public boolean validateRejectionMessageOnProfile() {
        elementUtil.waitForElementVisible(RejectionMessage, 20);
        return elementUtil.getDisplayedStatus(RejectionMessage);
    }

    public void clickOnEditButton() {
        elementUtil.doClick(companyProfileEditBtn);
        elementUtil.waitForRequiredSec(3);
    }
}
