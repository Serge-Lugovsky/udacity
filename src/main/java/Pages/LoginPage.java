package Pages;

import Managers.PageManager;
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

    public LoginPage inputEmail(String email){
        wait.until(ExpectedConditions.elementToBeClickable(emailField));
        emailField.clear();
        emailField.sendKeys(email);
        System.out.println("ENTRY EMAIL");
        return this;
    }

    public LoginPage inputPassword(String password){
        passField.clear();
        passField.sendKeys(password);
        System.out.println("ENTRY PASSWORD");
        return this;
    }

    public void signInClick(){
        signInKey.click();
        System.out.println("LOGINING...");
    }
}
