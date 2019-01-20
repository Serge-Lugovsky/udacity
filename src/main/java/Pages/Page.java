package Pages;

import Managers.PageManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class Page {

    WebDriver driver;
    WebDriverWait wait;
    PageManager pages;
    Actions actions;
    JavascriptExecutor jse;

    protected Page(PageManager pages){
        this.pages = pages;
        driver = pages.getDriver();
        wait = new WebDriverWait(driver, 20);
        actions = new Actions(driver);
        jse = (JavascriptExecutor) driver;
    }
}
