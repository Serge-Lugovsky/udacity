package Pages;

import Managers.PageManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;


public class AllCoursesPage extends Page{

    @FindBy(xpath = "//input[@placeholder='Search']")
    private WebElement searchField;

    @FindBy(xpath = "//span[text()='Results']")
    private WebElement confirmSearch;

    @FindBy(xpath = "//*[contains(@class, 'course-summary-card')]")
    private List<WebElement> coursesList;

    @FindBy(xpath = "//span[@class='filters ng-star-inserted']")
    private WebElement searchFilterText;

    private WebElement learnMoreButton;

    private WebElement firstCourseLink;

    private WebElement firstCourseSubmenu;

    private static String textFirstCourseLink;


    public AllCoursesPage(PageManager pages){
        super(pages);
    }

    public AllCoursesPage searchSmth(String searchText){
        wait.until(ExpectedConditions.elementToBeClickable(searchField));
        searchField.clear();
        searchField.sendKeys(searchText);
        System.out.println("Search course");
        return this;
    }

    public void waitSearchRes(){
        wait.until(ExpectedConditions.and(
            ExpectedConditions.visibilityOf(confirmSearch),
            ExpectedConditions.textToBe(By.xpath("//span[@class='filters ng-star-inserted']"),"android")
        ));
    }

    public String getValueOfSearch(){
        wait.until(ExpectedConditions.visibilityOf(searchFilterText));
        return searchFilterText.getText();
    }

    public void getForAllElem(){
        try {
            wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@class='card-content']"), 230));
            List<WebElement> listOfNames = driver.findElements(By.xpath("//div[@class='card-content']//a"));
            wait.until(ExpectedConditions.elementToBeClickable(listOfNames.get(0)));
        }catch (StaleElementReferenceException e){
            getForAllElem();
        }
    }

    public AllCoursesPage openFirstCourseSubMenu() {
        firstCourseSubmenu = coursesList.get(0).findElement(By.xpath(".//span[contains(text(), 'Details')]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", firstCourseSubmenu);
        firstCourseSubmenu.click();
        return this;
    }

    public boolean findLearnMoreBtn(){
        learnMoreButton = coursesList.get(0).findElement(By.xpath(".//a[text()= 'Learn More']"));
        wait.until(ExpectedConditions.visibilityOf(learnMoreButton));
        return learnMoreButton.isDisplayed();
    }

    public void saveCourseLinkText(){
        firstCourseLink = coursesList.get(0).findElement(By.xpath(".//h3/a"));
        wait.until(ExpectedConditions.visibilityOf(firstCourseLink));
        textFirstCourseLink = firstCourseLink.getText();
    }

    public String getCourseLinkText(){
        return textFirstCourseLink;
    }

    public void goFirstCourseLink(){
        firstCourseLink = coursesList.get(0).findElement(By.xpath(".//h3/a"));
        wait.until(ExpectedConditions.elementToBeClickable(firstCourseLink));
        firstCourseLink.click();
    }

}
