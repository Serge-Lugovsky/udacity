package com.udacity.tests;

import com.udacity.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class TestBase {

    protected    static WebDriver driver;
    protected    static Properties prop;
    protected    static WebDriverWait wait;


    protected TestBase(){
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/resources/test_data.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeTest
    protected static void setUp(){

        try{
            File chromeDriver = new File("/home/serhii/chromedriver");
            ChromeDriverService serviceChrome = new ChromeDriverService.Builder()
                    .usingDriverExecutable(chromeDriver)
                    .usingAnyFreePort()
                    .build();
            ChromeOptions optionsChrome = new ChromeOptions();
            optionsChrome.addArguments("start-maximized");

            driver = new ChromeDriver(serviceChrome, optionsChrome);
            driver.manage().window().maximize();
            wait = new WebDriverWait(driver, 10);

            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("profile.default_content_setting_values.notifications", 2);
            optionsChrome.setExperimentalOption("prefs", prefs);

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @AfterClass
    public void tearDown(){
        LoginPage loginPage = new LoginPage();
        loginPage.signOutAccount();
        Assert.assertTrue(loginPage.verifyUserSignOut());
        System.out.println("SUCCESSFUL SIGN_OUT");
        driver.quit();
    }

}
