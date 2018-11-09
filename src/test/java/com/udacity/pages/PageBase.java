package com.udacity.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class PageBase {

    public WebDriver driver;
    public WebDriverWait wait;


    public PageBase (WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,10);
    }

    public void waitClickable(WebElement elem) {
        wait.until(ExpectedConditions.elementToBeClickable(elem));
    }


    public void click (WebElement elem) {
        waitClickable(elem);
        elem.click();
    }

    public void entryTextInInput (WebElement elem, String text) {
        waitClickable(elem);
        elem.clear();
        elem.sendKeys(text);
    }

    public String getTextFromInput (WebElement elem) {
        waitClickable(elem);
        return elem.getAttribute("value");
    }

    public void assertEqualsElem(WebElement elem, String expectedText) {
        waitClickable(elem);
        Assert.assertEquals(getTextFromInput(elem), expectedText);
    }
}
