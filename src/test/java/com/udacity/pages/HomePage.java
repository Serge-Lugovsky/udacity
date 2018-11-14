package com.udacity.pages;

import com.udacity.tests.TestBase;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class HomePage extends TestBase {


    @FindBy(xpath = "(//*[@title='Sign In'])[2]")
    private WebElement signInLink;


    public HomePage(){
        PageFactory.initElements(driver, this);
    }

    public HomePage goToHomePage(){
        driver.get(prop.getProperty("TEST_URL"));
        return this;
    }

    public void goToLoginPage(){

            wait.until(ExpectedConditions.elementToBeClickable(signInLink));

            signInLink.click();
            System.out.println("REDIRECT...TO LOGIN PAGE");
    }

}
