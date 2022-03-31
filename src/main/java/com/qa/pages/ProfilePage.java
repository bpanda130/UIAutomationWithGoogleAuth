package com.qa.pages;

import com.qa.util.AuthenticationUtility;
import com.qa.util.CSVFileUtility;
import com.qa.util.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class ProfilePage {

    private WebDriver driver;
    Properties prop;
    ElementUtil elementUtil;
    AuthenticationUtility authUtility;
    CSVFileUtility csvFileUtility;


    private final By formTitle_h1 = By.xpath("//h1[@class='register-form__title']");
    private final By formTitle_h2 = By.xpath("//h2[@class='register-form__title2']");
    private final By logout_link = By.xpath("//span[text()='Logout']");
    private final By sendToApproval_btn = By.xpath("//button[span[text()='SEND TO APPROVE']]");
    private final By save_btn = By.xpath("//button[span[text()=' SAVE']]");
    private final By firstName_input = By.xpath("//label[text()='First name']/preceding-sibling::input");
    private final By lastName_input = By.xpath("//label[text()='Last name']/preceding-sibling::input");
    private final By positionInCompany_input = By.xpath("//label[text()='Position in your company']/preceding-sibling::input");
    private final By companyReg_input = By.xpath("//label[text()='Company Registration number (UEN)']/preceding-sibling::input");
    private final By companyName_input = By.xpath("//label[text()='Full Company Name']/preceding-sibling::input");
    private final By linkedInAct_input = By.xpath("//label[text()='LinkedIn Personal account']/preceding-sibling::input");
    private final By taxRegNumber_input = By.xpath("//label[text()='Tax Registration Number']/preceding-sibling::input");
    private final By saveAlertMessage = By.xpath("//div[@role='alert']//section[2]/div/div");


    //Constructor
    public ProfilePage(WebDriver driver) {
        elementUtil = new ElementUtil(driver);
    }

    public boolean validateProfilePageDetails(String formtitle1, String formtitle2) {
        return elementUtil.doGetText(formTitle_h1).equalsIgnoreCase(formtitle1) &&
                elementUtil.doGetText(formTitle_h2).equalsIgnoreCase(formtitle2) &&
                !elementUtil.getEnabledStatus(sendToApproval_btn) &&
                elementUtil.getDisplayedStatus(save_btn) &&
                elementUtil.getDisplayedStatus(firstName_input);
    }

    public void profileLogout() {
        elementUtil.waitForRequiredSec(4);
        elementUtil.doClick(logout_link);
    }

    public void enterFirstName(String fName)
    {
        elementUtil.waitForRequiredSec(2);
        elementUtil.doSendKeysAndTab(firstName_input, fName);


    }
    public void enterLastName(String lName)
    {
        elementUtil.doSendKeysAndTab(lastName_input, lName);
    }

    public void enterCompanyPosition(String position)
    {
        elementUtil.doSendKeysAndTab(positionInCompany_input, position);
    }
    public void enterCompanyName(String companyName)
    {
        elementUtil.doSendKeysAndTab(companyName_input, companyName);
    }
    public void enterCompanyRegNumber(String UENNum)
    {
        elementUtil.doSendKeysAndTab(companyReg_input, UENNum);
    }
    public void enterCompanyTaxRegNumber(String TaxNumber)
    {
        elementUtil.doSendKeysAndTab(taxRegNumber_input, TaxNumber);
    }

    public void enterLinkedInActDetails(String LinkedInAct)
    {
        elementUtil.doSendKeysAndTab(linkedInAct_input, LinkedInAct);
    }

    public boolean verifySentToApproveBtn() {
        return elementUtil.getEnabledStatus(sendToApproval_btn);
    }

    public void ClickOnSaveButton() {
        elementUtil.doClick(save_btn);
    }

    public void ClickOnSentToApproveBtn() {
        elementUtil.doClick(sendToApproval_btn);
    }

    public String verifyAlertPresence() {
        elementUtil.waitForRequiredSec(2);
        return elementUtil.doGetText(saveAlertMessage);
    }

    public boolean verifyProfileEntries(String fName, String lName, String position, String companyName, String UENNumber, String TaxNumber, String LinkedInAct) {
        elementUtil.waitForRequiredSec(5);
        return elementUtil.doGetAttribute(firstName_input, "value").equalsIgnoreCase(fName) &&
                    elementUtil.doGetAttribute(lastName_input, "value").equalsIgnoreCase(lName) &&
                    elementUtil.doGetAttribute(positionInCompany_input, "value").equalsIgnoreCase(position) &&
                    elementUtil.doGetAttribute(companyName_input, "value").equalsIgnoreCase(companyName) &&
                    elementUtil.doGetAttribute(companyReg_input, "value").equalsIgnoreCase(UENNumber) &&
                    elementUtil.doGetAttribute(taxRegNumber_input, "value").equalsIgnoreCase(TaxNumber) &&
                    elementUtil.doGetAttribute(linkedInAct_input, "value").equalsIgnoreCase(LinkedInAct);
    }
}
