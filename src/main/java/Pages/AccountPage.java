package Pages;

import Managers.PageManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;


public class AccountPage extends Page {

    @FindBy(xpath = "//a[@title='Logout']")
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

    @FindBy(xpath = "(//*[@title='Sign In'])[2]")
    private WebElement signInLink;

    @FindBy(xpath = "//a[@title = 'Catalog']")
    private WebElement catalog;

    public AccountPage(PageManager pages){
        super(pages);
    }

    public AccountPage clickSettingsButton() {
        wait.until(ExpectedConditions.elementToBeClickable(settingsButton));
        settingsButton.click();
        return this;
    }

    public AccountPage clickPersonalInfoLink() {
        wait.until(ExpectedConditions.elementToBeClickable(personalInfoMenuLink));
        personalInfoMenuLink.click();
        return this;
    }

    public void goToCatalog(){
        wait.until(ExpectedConditions.elementToBeClickable(catalog));
        String oldTab = driver.getWindowHandle();
        catalog.click();
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
            tabs.remove(oldTab);
            driver.close();
            driver.switchTo().window(tabs.get(0));
    }

//=================================|GET USER DATA|=================================================

    public String getUserFirstName() {
        wait.until(ExpectedConditions.elementToBeClickable(firstNameField));
        return firstNameField.getAttribute("value");
    }

    public String getUserLastName() {
        wait.until(ExpectedConditions.elementToBeClickable(lastNameField));
        return lastNameField.getAttribute("value");
    }

    public String getUserEmailAddress() {
        wait.until(ExpectedConditions.elementToBeClickable(emailField));
        return emailField.getAttribute("value");
    }

    public boolean verifyLogin(){

        try {
            wait.until(ExpectedConditions.visibilityOf(logoutButton));

        }catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {
            driver.get("https://classroom.udacity.com/me");
            wait.until(ExpectedConditions.visibilityOf(logoutButton));
        }
        return logoutButton.isDisplayed();
    }
//======================================================================================================================

    public void logOutAccount() {
        driver.get("https://classroom.udacity.com/me");
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        logoutButton.click();
        System.out.println("LOGOUT");
    }

    public boolean verifyLogOut(){

        try{

            wait.until(ExpectedConditions.visibilityOf(signInLink));

        }catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {

            driver.get("https://www.udacity.com");
            wait.until(ExpectedConditions.visibilityOf(signInLink));
        }
        return signInLink.isDisplayed();
    }

}
