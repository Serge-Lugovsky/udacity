package com.udacity.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;


public class LoginPage extends PageBase {

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passField;

    @FindBy(xpath = "//form/button[text()='Sign In']")
    private WebElement signInKey;

    @FindBy(xpath = "//a[@title='Logout']")
    private  WebElement logoutButton;


    public LoginPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

//==========================|SIGN_IN_ACCOUNT|==========================================


    public AccountSettingsPage signInToAccount (String EMAIL, String PASSWORD){

        try{
            entryTextInInput(emailField, EMAIL);
            System.out.println("ENTRY EMAIL");
            entryTextInInput(passField, PASSWORD);
            System.out.println("ENTRY PASSWORD");
            click(signInKey);

            wait.until(ExpectedConditions.urlToBe("https://classroom.udacity.com/me"));
            System.out.println("URL     PASSED");
            Assert.assertTrue(logoutButton.isDisplayed());
            System.out.println("SIGN_IN WAS SUCCESSFUL");
        }catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e){


            System.out.println("FAILED SIGN_IN!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println(e.getMessage());


        }
        return new AccountSettingsPage(driver);

    }

//======================================================================================
}
