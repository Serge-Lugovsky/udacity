package com.udacity.tests;

import com.udacity.pages.HomePage;
import org.testng.annotations.Test;

import static com.udacity.UserData.EMAIL;
import static com.udacity.UserData.PASSWORD;

public class LoginTest extends TestBase {

    @Test(priority = 1, description = "Login user")
    public void loginTest(){
        System.out.println("LOgin test!!!!!!!!!!!!");
        HomePage homePage = new HomePage(driver);

        homePage.goToHomePage()
                .goToLoginPage()
                .signInToAccount(EMAIL, PASSWORD);

    }

}
