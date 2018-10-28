import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;

public class BaseTest {

    protected WebDriver driver;


    @BeforeTest
    public void start(){
        ChromeDriverService serviceChrome = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("/home/serhii/chromedriver"))
                .usingAnyFreePort()
                .build();
        ChromeOptions optionsChrome = new ChromeOptions();
        optionsChrome.addArguments("start-maximized");
        driver = new ChromeDriver(serviceChrome, optionsChrome);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

    }

    @AfterTest
    protected void tearDown() {
        if(driver != null)
            driver.quit();
    }
}