import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;


public class ScreenShotOnFailListener implements ITestListener {

    private WebDriver driver;

    @Override
    public void onTestStart(ITestResult iTestResult) {}

    @Override
    public void onTestSuccess(ITestResult iTestResult) {}

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        saveScreenshot(iTestResult.getThrowable(), iTestResult);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {}

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        saveScreenshot(iTestResult.getThrowable(), iTestResult);
    }

    @Override
    public void onStart(ITestContext iTestContext) {}

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

    @Attachment(value = "Error message:   {0} " , type = "image/png", fileExtension = ".jpg")
    public byte[] saveScreenshot(Object errorMessage, ITestResult result){
        this.driver = ((TestBase)result.getInstance()).app.getDriver();
        return (((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
    }
}