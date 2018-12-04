package Managers;

import Drivers.Driver;
import Models.User;
import Utils.PropertyLoader;
import org.openqa.selenium.WebDriver;

public class AppManager {

    private UserHelper userHelper;
    private AttributeHelper attributeHelper;
    private NavigationHelper navigationHelper;
    private WebDriver driver;
    private User loginUser;
    private String browserName;
    private String baseUrl;
    private String userFirstName;
    private String userLastName;
    private String password;
    private String email;


    public AppManager(){
        browserName = PropertyLoader.loadProperty("browser.name");
        baseUrl = PropertyLoader.loadProperty("BASE_URL");
        userFirstName = PropertyLoader.loadProperty("FIRST_USER_NAME");
        userLastName = PropertyLoader.loadProperty("LAST_USER_NAME");
        password = PropertyLoader.loadProperty("PASSWORD");
        email = PropertyLoader.loadProperty("EMAIL");

        driver = new Driver().setupDriver(browserName);
        driver.get(baseUrl);

        loginUser = new User(email, password, userFirstName, userLastName);
        attributeHelper = new AttributeHelper(this);
        userHelper = new UserHelper(this);
        navigationHelper = new NavigationHelper(this);


    }

    public User getUser(){
        return loginUser;
    }

    public UserHelper getUserHelper() {
        return userHelper;
    }

    public AttributeHelper getAttributeHelper() {
        return attributeHelper;
    }

    public NavigationHelper getNavigationHelper(){
        return navigationHelper;
    }

    public WebDriver getDriver(){
        return driver;
    }
}
