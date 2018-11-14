package com.udacity.pages;

import com.udacity.tests.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;


public class PersonalInfoPage extends TestBase {


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


    public PersonalInfoPage() {
        PageFactory.initElements(driver, this);
    }


    public void clickSettingsButton() {
        wait.until(elementToBeClickable(settingsButton));
        settingsButton.click();
    }

    public void goToPersonalInfoMenuLink() {
        wait.until(elementToBeClickable(personalInfoMenuLink));
        personalInfoMenuLink.click();
    }

//=================================|GET USER DATA|=================================================

    public String getUserFirstName() {
        return firstNameField.getAttribute("value");
    }

    public String getUserLastName() {
        return lastNameField.getAttribute("value");
    }

    public String getUserEmailAddress() {
        return emailField.getAttribute("value");
    }

//====================================================================================================

}