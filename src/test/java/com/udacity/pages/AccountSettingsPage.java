package com.udacity.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static com.udacity.UserData.TEST_URL;
import static com.udacity.pages.HomePage.signInLink;


public class AccountSettingsPage extends PageBase {


    @FindBy(xpath = "//a[@title='Settings']")
    private WebElement settingsButton;

    @FindBy(xpath = "//a[@href='/settings/personal-info']")
    private WebElement personalInfoMenuLink;

    @FindBy(xpath = "//input[@placeholder='First Name']")
    private WebElement firstNameField;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    private WebElement lastNameField;

    @FindBy(xpath = "//input[@placeholder='Email Address']")
    private WebElement emailField;

    @FindBy(xpath = "//a[@title='Logout']")
    private  WebElement logoutButton;


    public AccountSettingsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    public AccountSettingsPage clickSettingsButton() {
        click(settingsButton);
        return this;
    }

    public AccountSettingsPage goToPersonalInfoMenuLink() {
        click(personalInfoMenuLink);
        return this;
    }

//=================================|VERIFY USER DATA|=================================================

    public AccountSettingsPage verifyUserData(String FIRST_USER_NAME, String LAST_USER_NAME, String EMAIL){

        try {
            assertEqualsElem(firstNameField, FIRST_USER_NAME);
            System.out.println("FIRST_USER_NAME     PASS");
            assertEqualsElem(lastNameField, LAST_USER_NAME);
            System.out.println("LAST_USER_NAME  PASS");
            assertEqualsElem(emailField, EMAIL);
            System.out.println("EMAIL   PASS");
        }catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e){

            System.out.println(e.getMessage());

        }
        return this;
    }

    public HomePage signOutAccount() {

        try {
            click(logoutButton);
            System.out.println("SIGN_OUT");

            waitClickable(signInLink);
            String url = driver.getCurrentUrl();
            Assert.assertEquals(url, TEST_URL);
            System.out.println("SUCCESSFUL SIGN_OUT");

        } catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {

            System.out.println(e.getMessage());

        }
        return new HomePage(driver);
    }

}