package Pages;

import Managers.PageManager;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class HomePage extends Page  {

    @FindBy(xpath = "(//div[contains(@class, 'modal-close')])[2]")
    private WebElement popUp;

    @FindBy(xpath = "//button[contains(@class, 'close-btn')]")
    private List<WebElement> baners;

    @FindBy(xpath = "(//a[@title='Sign In'])[2]")
    private WebElement signInLink;

    public HomePage(PageManager pages){
        super(pages);
    }

    @Step("Close PopUp")
    public void popUpClose(){
        try{
            new WebDriverWait(driver, 1)
                    .until(ExpectedConditions.elementToBeClickable(popUp));
            actions.click(popUp).perform();
        }catch (TimeoutException | NoSuchElementException | StaleElementReferenceException | NoSuchWindowException e){
            actions.sendKeys(Keys.ESCAPE).perform();
        }
    }

    @Step("Close banners")
    public void closeBanners(){
        try {
            for (WebElement elem : baners){
                new WebDriverWait(driver, 1).until(ExpectedConditions.elementToBeClickable(elem));
                actions.click(elem).perform();
            }
        }catch (TimeoutException | ElementNotVisibleException e){
            actions.sendKeys(Keys.ESCAPE).perform();
        }
    }

    @Step("Go to login page")
    public void goToLoginPage() {
        try{
            new WebDriverWait(driver, 1)
                    .until(ExpectedConditions.elementToBeClickable(signInLink));
            actions.click(signInLink).perform();
        }catch (TimeoutException | StaleElementReferenceException e){
            new WebDriverWait(driver, 3)
                    .until(ExpectedConditions.elementToBeClickable(signInLink));
            actions.click(signInLink).perform();
        }
    }

}
