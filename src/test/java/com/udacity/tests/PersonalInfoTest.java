package com.udacity.tests;

import com.udacity.pages.HomePage;
import com.udacity.pages.LoginPage;
import com.udacity.pages.PersonalInfoPage;
import org.testng.Assert;
import org.testng.annotations.*;


public class PersonalInfoTest extends TestBase {

    private PersonalInfoPage personalInfoPage;
    private LoginPage loginPage;
    private HomePage homePage;

    public PersonalInfoTest(){
        super();
    }

    @BeforeMethod
    public void initialize(){
        personalInfoPage = new PersonalInfoPage();
        loginPage = new LoginPage();
        homePage = new HomePage();
    }


    @Test(description = "Verify user info")
    public void verifyUserInfoTest(){
        homePage.goToHomePage()
                .goToLoginPage();
        loginPage.setEmailAddress(prop.getProperty("EMAIL"));
        loginPage.setPassword(prop.getProperty("PASSWORD"));
        loginPage.signInClick();
        Assert.assertTrue(loginPage.verifyUserSignIn());
            System.out.println("SIGN_IN WAS SUCCESSFUL");
        personalInfoPage.clickSettingsButton();
        personalInfoPage.goToPersonalInfoMenuLink();
        Assert.assertEquals(personalInfoPage.getUserFirstName(), prop.getProperty("FIRST_USER_NAME"));
            System.out.println("FIRST_USER_NAME     PASS");
        Assert.assertEquals(personalInfoPage.getUserLastName(), prop.getProperty("LAST_USER_NAME"));
            System.out.println("LAST_USER_NAME  PASS");
        Assert.assertEquals(personalInfoPage.getUserEmailAddress(), prop.getProperty("EMAIL"));
            System.out.println("EMAIL   PASS");
    }

}
