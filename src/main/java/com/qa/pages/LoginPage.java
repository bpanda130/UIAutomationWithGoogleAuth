package com.qa.pages;

import com.qa.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    ElementUtil elementUtil;
    AuthenticationUtility authUtility;
    CSVFileUtility csvFileUtility;

    private final By corporateEmail_input = By.xpath("//span[label[text()='Corporate email']]/input");
    private final By password_input = By.xpath("//span[label[text()='Password']]//input");
    private final By loginBtn = By.xpath("//button[span[text()='LOGIN']]");
    private final By introduction_code = By.xpath("//span[label[text()='Introduce code']]//input");
    private final By enterBtn = By.xpath("//button[span[text()='ENTER']]");



    public LoginPage(WebDriver driver) {
        elementUtil = new ElementUtil(driver);
        authUtility = new AuthenticationUtility();
        csvFileUtility = new CSVFileUtility();
    }

    public void verifyLoginPageDetails() {

    }

    public void enterLoginDetails(String username, String password) {
        elementUtil.doSendKeys(corporateEmail_input, username);
        elementUtil.doSendKeys(password_input, password);
    }

    public void clickOnLoginBtn() {
        elementUtil.doClick(loginBtn);
    }

    public void enterIntroductionCode(String userID) {
        try{
            if(Boolean.parseBoolean(GlobalData.scenarioData.get("Registration"))){
               // Thread.sleep(30000);
                elementUtil.waitForRequiredSec(30);
            }else{
                elementUtil.waitForRequiredSec(15);
            }
            String secretCode = csvFileUtility.getUserTokenDetailsFromFile(userID);
            elementUtil.doSendKeys(introduction_code,authUtility.getTwoFactorCode(secretCode));
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void clickOnEnterBtn() {
        elementUtil.doClick(enterBtn);
    }
}
