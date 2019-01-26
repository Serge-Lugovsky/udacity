package Pages;

import Managers.PageManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class LoginPage extends Page {

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passField;

    @FindBy(xpath = "//form/button[text()='Sign In']")
    private WebElement signInKey;

    public LoginPage(PageManager pages){
        super(pages);
    }

    @Step("Input email    {email}")
    public LoginPage inputEmail(String email){
        wait.until(ExpectedConditions.elementToBeClickable(emailField));
        emailField.clear();
        emailField.sendKeys(email);
        return this;
    }

    @Step("Input password    {password}")
    public LoginPage inputPassword(String password){
        wait.until(ExpectedConditions.elementToBeClickable(passField));
        passField.clear();
        passField.sendKeys(password);
        return this;
    }

    @Step("Click signIn button")
    public void signInClick(){
        wait.until(ExpectedConditions.elementToBeClickable(signInKey));
        signInKey.click();
    }
}
