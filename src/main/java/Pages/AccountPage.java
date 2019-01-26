package Pages;

import Managers.PageManager;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;


public class AccountPage extends Page {

    @FindBy(xpath = "//li/a[@title='Logout']")
    private WebElement logoutButton;

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

    @FindBy(xpath = "(//a[@title='Sign In'])[2]")
    private WebElement signInLink;

    @FindBy(xpath = "//a[@title = 'Catalog']")
    private WebElement catalog;

    public AccountPage(PageManager pages){
        super(pages);
    }

    @Step("Click settings button")
    public AccountPage clickSettingsButton() {
        wait.until(ExpectedConditions.elementToBeClickable(settingsButton));
        jse.executeScript("arguments[0].click();", settingsButton);
        return this;
    }

    @Step("Click personal info link")
    public void clickPersonalInfoLink() {
        wait.until(ExpectedConditions.elementToBeClickable(personalInfoMenuLink));
        personalInfoMenuLink.click();
    }

    @Step("Go to courses catalog page")
    public void goToCatalog(){
        String oldTab = driver.getWindowHandle();
        wait.until(ExpectedConditions.elementToBeClickable(catalog));
        jse.executeScript("arguments[0].click();", catalog);
        ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
            tabs.remove(oldTab);
            driver.close();
            driver.switchTo().window(tabs.get(0));
    }

//=================================|GET USER DATA|=================================================
    @Step("Get first user name from input")
    public String getUserFirstName() {
        wait.until(ExpectedConditions.elementToBeClickable(firstNameField));
        return firstNameField.getAttribute("value");
    }

    @Step("Get last user name from input")
    public String getUserLastName() {
        wait.until(ExpectedConditions.elementToBeClickable(lastNameField));
        return lastNameField.getAttribute("value");
    }

    @Step("Get user email from input")
    public String getUserEmailAddress() {
        wait.until(ExpectedConditions.elementToBeClickable(emailField));
        return emailField.getAttribute("value");
    }
//========================================================================================================

    @Step("Logout from account")
    public void logOutAccount() {
        driver.get("https://classroom.udacity.com/me");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
            jse.executeScript("arguments[0].click();", logoutButton);
        }catch (StaleElementReferenceException e){
            wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
            jse.executeScript("arguments[0].click();", logoutButton);
        }
    }

    @Step("Check login")
    public boolean verifyLogin(){
        try {
            wait.until(ExpectedConditions.urlToBe("https://classroom.udacity.com/me"));
        }catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {
            driver.get("https://classroom.udacity.com");
        }
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        return logoutButton.isDisplayed();
    }

    @Step("Check logout")
    public boolean verifyLogOut(){
        try{
            wait.until(ExpectedConditions.elementToBeClickable(signInLink));
        }catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {
            driver.get("https://www.udacity.com");
            wait.until(ExpectedConditions.elementToBeClickable(signInLink));
        }
        return signInLink.isDisplayed();
    }

}
