package Managers;

import Drivers.Driver;
import Models.User;
import Utils.PropertyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AppManager {

    private UserHelper userHelper;
    private AccountHelper accountHelper;
    private WebDriver driver;
    private User loginUser;
    private DesiredCapabilities capabilities;
    private String baseUrl;
    private String userFirstName;
    private String userLastName;
    private String password;
    private String email;


    public AppManager(){
        capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(PropertyLoader.loadProperty("browser.name"));

        baseUrl = PropertyLoader.loadProperty("BASE_URL");
        userFirstName = PropertyLoader.loadProperty("FIRST_USER_NAME");
        userLastName = PropertyLoader.loadProperty("LAST_USER_NAME");
        password = PropertyLoader.loadProperty("PASSWORD");
        email = PropertyLoader.loadProperty("EMAIL");

        driver = new Driver().setupDriver(capabilities);
        driver.get(baseUrl);

        loginUser = new User(email, password, userFirstName, userLastName);
        accountHelper = new AccountHelper(this);
        userHelper = new UserHelper(this);


    }

    public User getUser(){
        return loginUser;
    }

    public UserHelper getUserHelper() {
        return userHelper;
    }

    public AccountHelper getAccountHelper(){
        return accountHelper;
    }

    public WebDriver getDriver(){
        return driver;
    }
}
