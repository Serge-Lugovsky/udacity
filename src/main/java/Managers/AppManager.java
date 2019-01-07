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


    protected AppManager(){
        String browserName = PropertyLoader.loadProperty("browser.name");
        String headless = PropertyLoader.loadProperty("head.less");
        String baseUrl = PropertyLoader.loadProperty("BASE_URL");
        String userFirstName = PropertyLoader.loadProperty("FIRST_USER_NAME");
        String userLastName = PropertyLoader.loadProperty("LAST_USER_NAME");
        String password = PropertyLoader.loadProperty("PASSWORD");
        String email = PropertyLoader.loadProperty("EMAIL");
        String courseName = PropertyLoader.loadProperty("COURSE_NAME");

        driver = new Driver().setupDriver(browserName, headless);
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
