package com.qa.util;

import org.jboss.aerogear.security.otp.Totp;

public class GoogleAuth {

    public static String getTwoFactorCode() {
        //Replace with your security key copied from step 12
        //Totp totp = new Totp("4PZ2WXL5DRSYW43JGOZY6WIM4EJS7K5TWK6QJUJKMWX7BYG2RW6Q"); // 2FA secret key
        Totp totp = new Totp("WJ6J7ZGZBHPUYWR2YZMHKRKARJ27RXTQQSJ25CH4LD7UK4PHRL3A");
        String twoFactorCode = totp.now(); //Generated 2FA code here
        return twoFactorCode;
    }

    public static void main(String[] args) {
        System.out.println(getTwoFactorCode());
    }
}
