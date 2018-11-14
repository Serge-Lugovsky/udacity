package com.udacity.tests;

import com.udacity.pages.HomePage;
import com.udacity.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;


public class LoginTest extends TestBase {

    private LoginPage loginPage;
    private HomePage homePage;

    public LoginTest(){
        super();
    }

    @BeforeMethod
    public void initialize(){
        loginPage = new LoginPage();
        homePage = new HomePage();
    }

    @Test(description = "Login user")
    public void loginTest(){
        homePage.goToHomePage()
                .goToLoginPage();
        loginPage.setEmailAddress(prop.getProperty("EMAIL"));
        loginPage.setPassword(prop.getProperty("PASSWORD"));
        loginPage.signInClick();
        Assert.assertTrue(loginPage.verifyUserSignIn());
        System.out.println("SIGN_IN WAS SUCCESSFUL");
    }

}
