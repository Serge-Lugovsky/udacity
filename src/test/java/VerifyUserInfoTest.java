import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ScreenShotOnFailListener.class})
public class VerifyUserInfoTest extends TestBase {

    @Test(description = "Verify user personal Information", groups = {"debugGroup","fullGroup"})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify user personal information")
    public void verifyUserInfoTest(){
        app.getNavigationHelper().moveToUserInfo();
        Assert.assertEquals(app.getAttributeHelper().getFirstName(), app.getUser().getUserFirstName());
        Assert.assertEquals(app.getAttributeHelper().getLastName(), app.getUser().getUserLastName());
        Assert.assertEquals(app.getAttributeHelper().getEmail(), app.getUser().getEmail());
    }

}
