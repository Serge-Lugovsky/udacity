package Pages;

import Managers.PageManager;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static Utils.JsAndJqueryWaiter.waitJsAndJQueryLoad;


public class AllCoursesPage extends Page{

    @FindBy(xpath = "//input[@placeholder='Search']")
    private WebElement searchField;

    @FindBy(xpath = "//span[text()='Results']")
    private WebElement confirmSearch;

    @FindBy(xpath = "//div[contains(@class, 'course-summary-card')]")
    private List<WebElement> coursesList;

    @FindBy(xpath = "//a[@data-analytics-event='Navbar Link Clicked']")
    private List<WebElement> navbarTopLinkList;

    @FindBy(xpath = "//span[@class='filters ng-star-inserted']")
    private WebElement searchFilterText;

    private WebElement firstCourseLink;

    private static String textFirstCourseLink;

    private static String hrefLink;


    public AllCoursesPage(PageManager pages){
        super(pages);
    }

    @Step("Search courses {searchText}")
    public AllCoursesPage searchSmth(String searchText){
        actions.sendKeys(Keys.ESCAPE).perform();
        wait.until(ExpectedConditions.elementToBeClickable(searchField));
        searchField.clear();
        searchField.sendKeys(searchText);
        return this;
    }

    @Step("Wait for search result")
    public void waitSearchRes(String searchText){
        wait.until(ExpectedConditions.and(
            ExpectedConditions.visibilityOf(confirmSearch),
            ExpectedConditions.textToBe(By.xpath("//span[@class='filters ng-star-inserted']"),searchText)
        ));
    }

    @Step("Get search value")
    public String getValueOfSearch(){
        wait.until(ExpectedConditions.visibilityOf(searchFilterText));
        return searchFilterText.getText();
    }

    @Step("Wait for Js and jQuery to load ")
    public void getForAllElem(){
        waitJsAndJQueryLoad(jse, wait);
    }

    @Step("Open first course submenu in list ")
    public void openFirstCourseSubMenu() {
        WebElement firstCourseSubmenu = coursesList.get(0).findElement(By.xpath(".//span[contains(text(), 'Details')]"));
        jse.executeScript("arguments[0].scrollIntoView(true);", firstCourseSubmenu);
        wait.until(ExpectedConditions.elementToBeClickable(firstCourseSubmenu));
        actions.click(firstCourseSubmenu).perform();
    }

    @Step("Check learn more button is displayed")
    public boolean findLearnMoreBtn(){
        WebElement learnMoreButton = coursesList.get(0).findElement(By.xpath(".//a[text()= 'Learn More']"));
        try{
            wait.until(ExpectedConditions.elementToBeClickable(learnMoreButton));
        }catch (StaleElementReferenceException | TimeoutException e){
            wait.until(ExpectedConditions.elementToBeClickable(learnMoreButton));
        }
        return learnMoreButton.isDisplayed();
    }

    @Step("Save course link text")
    public void saveCourseLinkText(){
        firstCourseLink = coursesList.get(0).findElement(By.xpath(".//h3/a"));
        wait.until(ExpectedConditions.visibilityOf(firstCourseLink));
        textFirstCourseLink = firstCourseLink.getText();
    }

    @Step("Check course link text")
    public String getCourseLinkText(){
        return textFirstCourseLink;
    }

    @Step("Open first course page")
    public void goFirstCourseLink(){
        firstCourseLink = coursesList.get(0).findElement(By.xpath(".//h3/a"));
        jse.executeScript("arguments[0].scrollIntoView(true);", firstCourseLink);
        wait.until(ExpectedConditions.elementToBeClickable(firstCourseLink));
        actions.click(firstCourseLink).perform();
    }

    @Step("Go to link '{linkName} ")
    public void goToNavbarLinkPage(String linkName){
        if (navbarTopLinkList.size() > 0) {
            for (WebElement link : navbarTopLinkList) {
                if (link.getText().trim().equals(linkName.trim())){
                    wait.until(ExpectedConditions.elementToBeClickable(link));
                    hrefLink = link.getAttribute("href")
                            .replaceAll("^(http://|https://)", "").replaceAll("/?$", "");
                    actions.click(link).perform();
                    break;
                }
            }
        }
    }

    public String getCourseHrefLinkFromPage() {
        return hrefLink;
    }

    @Step("Check navbar link")
    public String getCurrentUrl(){
        return driver.getCurrentUrl().replaceAll("^(http://|https://)", "")
                .replaceAll("/?(me)?$", "");
    }
}
