import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class Login extends BaseTest{

    private WebElement signInLink;
    private WebElement popupClose;
    private WebElement emailField;
    private WebElement passField;
    private WebElement signInKey;
    private WebElement assertElement;

    @Test
    public void openPage() {

        WebDriverWait wait = new WebDriverWait(driver, 30);
        driver.get("https://www.udacity.com/");

        popupClose = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='modal-close']")));
        wait.until(ExpectedConditions.elementToBeClickable(popupClose));

        if (popupClose != null){
            Actions escapeAction = new Actions(driver);
            escapeAction.sendKeys(Keys.ESCAPE).build().perform();
        }

        signInLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@title='Sign In'])[2]")));
        ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+signInLink.getLocation().y+")");
        wait.until(ExpectedConditions.elementToBeClickable(signInLink));
        signInLink.click();

        emailField = wait.until(visibilityOfElementLocated(By.xpath("//input[@type='email']")));
        emailField.clear();
        emailField.sendKeys("testmailforus9@gmail.com");

        passField = driver.findElement(By.xpath("//input[@type='password']"));
        passField.clear();
        passField.sendKeys("trash1010");

        signInKey = driver.findElement(By.xpath("//form/button[text()='Sign In']"));
        signInKey.click();

//        System.out.println("Test passed!");
//        assertElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Logout']")));
//        Assert.assertTrue(assertElement.isDisplayed());

    }
}
