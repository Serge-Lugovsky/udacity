import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;


import java.io.ByteArrayInputStream;


public class ScreenShotOnFailListener implements ITestListener {

    private WebDriver driver;

    @Override
    public void onTestStart(ITestResult iTestResult) {
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        screenShot(iTestResult);
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        screenShot(iTestResult);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        screenShot(iTestResult);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        screenShot(iTestResult);
    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

    @Step("Make screen shot")
    private void screenShot(ITestResult result){
        this.driver = ((TestBase)result.getInstance()).app.getDriver();
        Allure.addAttachment("Screenshot",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }
}