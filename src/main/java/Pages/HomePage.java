package Pages;

import Managers.PageManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends Page  {

    @FindBy(xpath = "//*[@class='modal-close white']")
    private WebElement popUp;

    @FindBy(xpath = "(//*[@title='Sign In'])[2]")
    private WebElement signInLink;

    public HomePage(PageManager pages){
        super(pages);
    }

    public HomePage popUpClose(){

        try{
            new WebDriverWait(driver, 1)
                    .until(ExpectedConditions.elementToBeClickable(popUp));
            popUp.click();
        }catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e){
            actions.sendKeys(Keys.ESCAPE).build().perform();
        }
        return this;
    }

    public void goToLoginPage() {

            new WebDriverWait(driver, 1)
                    .until(ExpectedConditions.elementToBeClickable(signInLink));
//            actions.moveToElement(signInLink).click(signInLink).build().perform();
            signInLink.click();
            System.out.println("REDIRECT...TO LOGIN PAGE");
    }

}
