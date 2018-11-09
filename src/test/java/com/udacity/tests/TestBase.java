package com.udacity.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.io.File;


public class TestBase {

    public WebDriver driver;

    @BeforeClass
    public void setup(){

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

            driver.manage().deleteAllCookies();

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @AfterClass
    public void destroyDriver(){
        driver.quit();
    }
}
