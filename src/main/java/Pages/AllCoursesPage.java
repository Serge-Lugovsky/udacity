package Pages;

import Managers.PageManager;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


public class AllCoursesPage extends Page{

    @FindBy(xpath = "//input[@placeholder='Search']")
    private WebElement searchField;

    @FindBy(xpath = "//span[text()='Results']")
    private WebElement confirmSearch;

    @FindBy(xpath = "//*[contains(@class, 'course-summary-card')]")
    private List<WebElement> coursesList;

    @FindBy(xpath = "//a[@data-analytics-event='Navbar Link Clicked']")
    private List<WebElement> navbarTopLinkList;

    @FindBy(xpath = "//span[@class='filters ng-star-inserted']")
    private WebElement searchFilterText;

    private WebElement learnMoreButton;

    private WebElement firstCourseLink;

    private WebElement firstCourseSubmenu;

    private static String textFirstCourseLink;

    private static String hrefLink;


    public AllCoursesPage(PageManager pages){
        super(pages);
    }

    @Step("Search courses")
    public AllCoursesPage searchSmth(String searchText){
        actions.sendKeys(Keys.ESCAPE).build().perform();
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
        try {
            // wait for jQuery to load
            ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    try {
                        return ((Long) jse.executeScript("return jQuery.active") == 0);
                    } catch (Exception e) {
                        // no jQuery present
                        return true;
                    }
                }
            };
            // wait for Javascript to load
            ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    return jse.executeScript("return document.readyState")
                            .toString().equals("complete");
                }
            };

            wait.until(ExpectedConditions.and(
                    jQueryLoad,
                    jsLoad
            ));

        }catch (StaleElementReferenceException | TimeoutException e){
            getForAllElem();
        }
    }

    @Step("Open first course submenu in list ")
    public void openFirstCourseSubMenu() {
        firstCourseSubmenu = coursesList.get(0).findElement(By.xpath(".//span[contains(text(), 'Details')]"));
        jse.executeScript("arguments[0].scrollIntoView(true);", firstCourseSubmenu);
        wait.until(ExpectedConditions.elementToBeClickable(firstCourseSubmenu));
        jse.executeScript("arguments[0].click();", firstCourseSubmenu);
    }

    @Step("Check learn more button is displayed")
    public boolean findLearnMoreBtn(){
        learnMoreButton = coursesList.get(0).findElement(By.xpath(".//a[text()= 'Learn More']"));
        wait.until(ExpectedConditions.visibilityOf(learnMoreButton));
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
        jse.executeScript("arguments[0].click();", firstCourseLink);
    }

    @Step("Go to link in navbar")
    public void goToNavbarLinkPage(String linkName){
        if (navbarTopLinkList.size() > 0) {
            for (WebElement link : navbarTopLinkList) {
                if (link.getText().trim().equals(linkName.trim())){
                    wait.until(ExpectedConditions.elementToBeClickable(link));
                    hrefLink = link.getAttribute("href")
                            .replaceAll("^(http://|https://)", "");
                    System.out.println(hrefLink);
                    jse.executeScript("arguments[0].click();", link);
                    break;
                }
            }
        }
    }

    @Step("Check navbar link")
    public boolean verifyNavbarLink(){
        return driver.getCurrentUrl().matches(
                "^(http|https)://"+hrefLink+"/?(me)?$");
    }

    @Step("Get page http status code")
    public boolean getStatusCode(){
        try {
            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection con = (HttpURLConnection) new URL(driver.getCurrentUrl()).openConnection();
            con.setRequestMethod("HEAD");
            return (con.getResponseCode() == 200);
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
