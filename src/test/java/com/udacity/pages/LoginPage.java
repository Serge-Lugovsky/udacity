package com.udacity.pages;

import com.udacity.tests.TestBase;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class LoginPage extends TestBase {

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passField;

    @FindBy(xpath = "//form/button[text()='Sign In']")
    private WebElement signInKey;

    @FindBy(xpath = "//a[@title='Logout']")
    private WebElement logoutButton;

    @FindBy(xpath = "(//*[@title='Sign In'])[2]")
    private WebElement signInLink;


    public LoginPage(){
        PageFactory.initElements(driver, this);
    }

//==========================|SIGN_IN_ACCOUNT|==========================================

    public void setEmailAddress(String EMAIL){
        emailField.clear();
        emailField.sendKeys(EMAIL);
        System.out.println("ENTRY EMAIL");
    }

    public void setPassword(String PASSWORD){
        passField.clear();
        passField.sendKeys(PASSWORD);
        System.out.println("ENTRY PASSWORD");
    }

    public void signInClick(){
        signInKey.click();
        System.out.println("LOGINING...");        
    }

    public boolean verifyUserSignIn(){
        try {
            wait.until(ExpectedConditions.elementToBeClickable(logoutButton));

        }catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {

            System.out.println(e.getMessage());

        }
        return logoutButton.isDisplayed();
    }

//======================================================================================


    public void signOutAccount() {
            logoutButton.click();
            System.out.println("SIGN_OUT");
    }

    public boolean verifyUserSignOut(){
        try{

            wait.until(ExpectedConditions.visibilityOf(signInLink));

        }catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {

            System.out.println(e.getMessage());

        }

        return signInLink.isDisplayed();
    }

}
