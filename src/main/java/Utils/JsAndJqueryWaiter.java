package Utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JsAndJqueryWaiter {

    public static void waitJsAndJQueryLoad(JavascriptExecutor jse, WebDriverWait wait){
        try{
            ExpectedCondition<Boolean> jQueryLoad = driver -> {
                try {
                    return ((Long) jse.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    // no jQuery present
                    return true;
                }
            };
            ExpectedCondition<Boolean> jsLoad = driver -> jse.executeScript("return document.readyState")
                    .toString().equals("complete");

            wait.until(ExpectedConditions.and(
                jQueryLoad,
                jsLoad
            ));
        }catch (TimeoutException e){
            waitJsAndJQueryLoad(jse, wait);
        }
    }

}
