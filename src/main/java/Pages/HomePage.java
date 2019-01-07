package Pages;

import Managers.PageManager;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends Page  {

    @FindBy(xpath = "(//*[contains(@class, 'modal-close')])[2]")
    private WebElement popUp;

    @FindBy(xpath = "(//*[@title='Sign In'])[2]")
    private WebElement signInLink;

    public HomePage(PageManager pages){
        super(pages);
    }

    @Step("Close PopUp")
    public void popUpClose(){

        try{
            new WebDriverWait(driver, 2)
                    .until(ExpectedConditions.elementToBeClickable(popUp));
            jse.executeScript("arguments[0].click();", popUp);
        }catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e){
            actions.sendKeys(Keys.ESCAPE).build().perform();
        }
    }
    @Step("Go to login page")
    public void goToLoginPage() {

            new WebDriverWait(driver, 1)
                .until(ExpectedConditions.elementToBeClickable(signInLink));
            jse.executeScript("arguments[0].click();", signInLink);
    }

}
