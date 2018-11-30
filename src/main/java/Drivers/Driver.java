package Drivers;

import org.openqa.selenium.Capabilities;
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

    public WebDriver setupDriver(Capabilities capabilities){

        switch (capabilities.getBrowserName()){
            case "chrome":
                File chromeDriver = new File("/home/serhii/chromedriver");
                ChromeDriverService serviceChrome = new ChromeDriverService.Builder()
                        .usingDriverExecutable(chromeDriver)
                        .usingAnyFreePort()
                        .build();
                ChromeOptions optionsChrome = new ChromeOptions();
                optionsChrome.addArguments("start-maximized");

                Map<String, Object> prefs = new HashMap<String, Object>();
                prefs.put("profile.default_content_setting_values.notifications", 2);
                optionsChrome.setExperimentalOption("prefs", prefs);

                return new ChromeDriver(serviceChrome, optionsChrome);

            case "firefox":
                File firefoxDriver = new File("/home/serhii/geckodriver");
                GeckoDriverService serviceFirefox = new GeckoDriverService.Builder()
                        .usingDriverExecutable(firefoxDriver)
                        .usingAnyFreePort()
                        .build();
                FirefoxOptions optionsFirefox = new FirefoxOptions();

                return new FirefoxDriver(serviceFirefox, optionsFirefox);
        }
        return null;
    }

}
