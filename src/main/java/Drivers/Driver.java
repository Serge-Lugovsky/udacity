package Drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class Driver {

    public WebDriver setupDriver(String browserName, String headless){

        switch (browserName){
            case "chrome":
                File chromeDriver = new File("./src/main/resources/chromedriver");
                ChromeDriverService serviceChrome = new ChromeDriverService.Builder()
                        .usingDriverExecutable(chromeDriver)
                        .usingAnyFreePort()
                        .build();
                ChromeOptions optionsChrome = new ChromeOptions();

                if (headless.equals("--headless")){
                    optionsChrome.addArguments("--headless","--disable-gpu", "--window-size=1366,768","--ignore-certificate-errors");
                }
                optionsChrome.addArguments("start-maximized");
//                optionsChrome.addArguments("--disable-cache", "--aggressive-cache-discard", "--disk-cache-size=1");

                Map<String, Object> prefs = new HashMap<>();
                prefs.put("profile.default_content_setting_values.notifications", 2);
                optionsChrome.setExperimentalOption("prefs", prefs);
                return new ChromeDriver(serviceChrome, optionsChrome);

            case "firefox":
                File firefoxDriver = new File("./src/main/resources/geckodriver");
                GeckoDriverService serviceFirefox = new GeckoDriverService.Builder()
                        .usingDriverExecutable(firefoxDriver)
                        .usingAnyFreePort()
                        .build();
                FirefoxOptions optionsFirefox = new FirefoxOptions();

                if (headless.equals("--headless")){
                    optionsFirefox.addArguments("--headless","--disable-gpu", "--window-size=1366,768","--ignore-certificate-errors");
                }

                return new FirefoxDriver(serviceFirefox, optionsFirefox);
        }
        return null;
    }

}
