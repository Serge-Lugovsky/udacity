package Managers;

import Pages.HomePage;
import Pages.LoginPage;
import Pages.Page;
import Pages.AccountPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class PageManager {

    private WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    AccountPage accountPage;

    public PageManager(WebDriver driver){
        this.driver = driver;
        homePage = initElements(new HomePage(this));
        loginPage = initElements(new LoginPage(this));
        accountPage = initElements(new AccountPage(this));
    }

    private <T extends Page> T initElements(T page){
        PageFactory.initElements(driver, page);
        return page;
    }

    public WebDriver getDriver(){
        return driver;
    }
}
