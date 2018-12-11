package Managers;

import Drivers.Driver;
import Models.Course;
import Models.User;
import Utils.PropertyLoader;
import org.openqa.selenium.WebDriver;

public class AppManager {

    private UserHelper userHelper;
    private AttributeHelper attributeHelper;
    private NavigationHelper navigationHelper;
    private CourseHelper courseHelper;
    private WebDriver driver;
    private User loginUser;
    private Course course;
    private String browserName;
    private String baseUrl;
    private String userFirstName;
    private String userLastName;
    private String password;
    private String email;
    private String courseName;


    public AppManager(){
        browserName = PropertyLoader.loadProperty("browser.name");
        baseUrl = PropertyLoader.loadProperty("BASE_URL");
        userFirstName = PropertyLoader.loadProperty("FIRST_USER_NAME");
        userLastName = PropertyLoader.loadProperty("LAST_USER_NAME");
        password = PropertyLoader.loadProperty("PASSWORD");
        email = PropertyLoader.loadProperty("EMAIL");
        courseName = PropertyLoader.loadProperty("COURSE_NAME");

        driver = new Driver().setupDriver(browserName);
        driver.get(baseUrl);

        loginUser = new User(email, password, userFirstName, userLastName);
        course = new Course(courseName);
        attributeHelper = new AttributeHelper(this);
        userHelper = new UserHelper(this);
        navigationHelper = new NavigationHelper(this);
        courseHelper = new CourseHelper(this);
    }

    public User getUser(){
        return loginUser;
    }

    public Course getCourse(){
        return course;
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

    public CourseHelper getCourseHelper() {
        return courseHelper;
    }

    public WebDriver getDriver(){
        return driver;
    }
}
