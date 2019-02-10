package Utils;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

public class AllureEnvironmentWriter {

    public static void setAllureEnvironment(WebDriver driver) {
        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = cap.getBrowserName().toLowerCase();
        String browserVersion = cap.getVersion();

        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Browser", browserName +" "+ browserVersion)
                        .put("Test site", PropertyLoader.loadProperty("BASE_URL"))
                        .put("OS", System.getProperty("os.name") +" "+ System.getProperty("os.version") +" "+
                                System.getProperty("os.arch"))
                        .put("java.version", System.getProperty("java.version"))
                        .put("Maven.version", PropertyLoader.loadProperty("maven.version"))
                        .put("Allure.testng.version", PropertyLoader.loadProperty("allure.testng.version"))
                        .put("Allure.report.version", PropertyLoader.loadProperty("allure.report.version"))
                        .build(), System.getProperty("user.dir")
                        + "/target/allure-results/");
    }
}
