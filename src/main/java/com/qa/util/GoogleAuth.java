package com.qa.util;

import org.jboss.aerogear.security.otp.Totp;

public class GoogleAuth {

    public static String getTwoFactorCode() {
        //Replace with your security key copied from step 12
        Totp totp = new Totp("WFRC2XLBZYSEB2WJA6ED7LFSIWXCOTJBZ6C33S622ZUV4WPO3XGA"); // 2FA secret key
        //Totp totp = new Totp("K6DH6NRGG6HE2TD2AM5RPN2ZMRHUERMCHZ3PBTHRMWRE4TICPHKQ");
        String twoFactorCode = totp.now(); //Generated 2FA code here
        return twoFactorCode;
    }

    public static void main(String[] args) {
        System.out.println(getTwoFactorCode());
    }
}
