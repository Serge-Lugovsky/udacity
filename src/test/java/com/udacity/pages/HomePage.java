package com.udacity.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;

import static com.udacity.UserData.TEST_URL;

public class HomePage extends PageBase {


    @FindBy(xpath = "(//*[@title='Sign In'])[2]")
    protected static WebElement signInLink;

    public HomePage (WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    public HomePage goToHomePage (){
        driver.get(TEST_URL);
        return this;
    }

    public LoginPage goToLoginPage (){
        click(signInLink);
        System.out.println("REDIRECT...TO LOGIN PAGE");
        return new LoginPage(driver);
    }

}