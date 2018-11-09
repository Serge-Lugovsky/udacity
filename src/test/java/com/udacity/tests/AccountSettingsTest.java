package com.udacity.tests;

import com.udacity.pages.HomePage;
import org.testng.annotations.Test;

import static com.udacity.UserData.*;


public class AccountSettingsTest extends TestBase {

    @Test(priority = 2, description = "Verify user info")
    public void verifyUserInfoTest(){
        System.out.println("Personal info test!!!!!!!!!!");
        HomePage homePage = new HomePage(driver);

        homePage.goToHomePage()
                .goToLoginPage()
                .signInToAccount(EMAIL, PASSWORD)
                .clickSettingsButton()
                .goToPersonalInfoMenuLink()
                .verifyUserData(FIRST_USER_NAME, LAST_USER_NAME, EMAIL)
                .signOutAccount();
    }

}
