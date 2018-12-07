package Managers;

import Pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class PageManager {

    private WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    AccountPage accountPage;
    AllCoursesPage allCoursesPage;
    CoursePage coursePage;

    public PageManager(WebDriver driver){
        this.driver = driver;
        homePage = initElements(new HomePage(this));
        loginPage = initElements(new LoginPage(this));
        accountPage = initElements(new AccountPage(this));
        allCoursesPage = initElements(new AllCoursesPage(this));
        coursePage = initElements(new CoursePage(this));
    }

    private <T extends Page> T initElements(T page){
        PageFactory.initElements(driver, page);
        return page;
    }

    public WebDriver getDriver(){
        return driver;
    }
}
