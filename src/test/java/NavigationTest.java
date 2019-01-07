import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ScreenShotOnFailListener.class)
public class NavigationTest extends TestBase {

    @Test(description = "Check My Classroom link", groups = {"fullGroup"}, invocationCount = 1)
    @Severity(SeverityLevel.NORMAL)
    @Story("Check status code My Classroom link")
    @Description("Check My Classroom link")
    public void checkMyClassroomLinkTest(){
        app.getNavigationHelper().goAllCoursesPage();
        app.getAttributeHelper().waitForElements();
        app.getNavigationHelper().goLinkPage("My Classroom");
        app.getAttributeHelper().waitForElements();
        Assert.assertTrue(app.getAttributeHelper().getStatusPageCode());
        Assert.assertTrue(app.getAttributeHelper().getVerifyResult());
    }

    @Test(description = "Check Blog link", groups = {"fullGroup"}, priority = 1, invocationCount = 1)
    @Severity(SeverityLevel.MINOR)
    @Story("Check status code Blog link")
    @Description("Check Blog link")
    public void checkBlogLinkTest(){
        app.getNavigationHelper().goAllCoursesPage();
        app.getAttributeHelper().waitForElements();
        app.getNavigationHelper().goLinkPage("Blog");
        app.getAttributeHelper().waitForElements();
        Assert.assertTrue(app.getAttributeHelper().getStatusPageCode());
        Assert.assertTrue(app.getAttributeHelper().getVerifyResult());
    }

    @Test(description = "Check For Enterprise link", groups = {"fullGroup"}, priority = 2, invocationCount = 1)
    @Severity(SeverityLevel.TRIVIAL)
    @Story("Check status code For Enterprise link")
    @Description("Check For Enterprise link")
    public void checkEnterpriseLinkTest(){
        app.getNavigationHelper().goAllCoursesPage();
        app.getAttributeHelper().waitForElements();
        app.getNavigationHelper().goLinkPage("For Enterprise");
        app.getAttributeHelper().waitForElements();
        Assert.assertTrue(app.getAttributeHelper().getStatusPageCode());
        Assert.assertTrue(app.getAttributeHelper().getVerifyResult());
    }

}