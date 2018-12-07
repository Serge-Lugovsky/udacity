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

    private WebElement learnMoreButton;

    private WebElement firstCourseLink;

    private WebElement firstCourseSubmenu;

    private static String textFirstCourseLink;


    public AllCoursesPage(PageManager pages){
        super(pages);
    }

    public AllCoursesPage searchSmth(String searchText){
        try {
            wait.until(ExpectedConditions.invisibilityOf(searchField));
            searchField.clear();
            searchField.sendKeys(searchText);
            System.out.println("Search course");
            wait.until(ExpectedConditions.visibilityOf(confirmSearch));

        }catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {
            searchField.clear();
            searchField.sendKeys(searchText);
            System.out.println("Search course");
            wait.until(ExpectedConditions.visibilityOf(confirmSearch));
        }
        return this;
    }

    public AllCoursesPage openFirstCourseSubMenu() {
        firstCourseSubmenu = coursesList.get(0).findElement(By.xpath(".//span[contains(text(), 'Details')]"));
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + firstCourseSubmenu.getLocation().y + ")");
        firstCourseSubmenu.click();
        return this;
    }

    public boolean findLearnMoreBtn(){
        learnMoreButton = coursesList.get(0).findElement(By.xpath(".//a[text()= 'Learn More']"));
        wait.until(ExpectedConditions.visibilityOf(learnMoreButton));
        return learnMoreButton.isDisplayed();
    }

    public void getCourseLinkText(){
        firstCourseLink = coursesList.get(0).findElement(By.xpath(".//h3/a"));
        wait.until(ExpectedConditions.visibilityOf(firstCourseLink));
        textFirstCourseLink = firstCourseLink.getAttribute("text");
    }

    public String getTextFirstCourseLink(){
        return textFirstCourseLink;
    }

    public void goFirstCourseLink(){
        firstCourseLink = coursesList.get(0).findElement(By.xpath(".//h3/a"));
        firstCourseLink.click();
    }

}
