import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ScreenShotOnFailListener.class})
public class NavigationTest extends TestBase {

    @Test(description = "Check My Classroom link", groups = {"fullGroup"})
    @Severity(SeverityLevel.NORMAL)
    @Description("Check 'My Classroom' link IN NAVBAR ")
    public void checkMyClassroomLinkTest(){
        app.getNavigationHelper().goAllCoursesPage();
        app.getAttributeHelper().waitForElements();
        app.getNavigationHelper().goLinkPage("My Classroom");
        app.getAttributeHelper().waitForElements();
        Assert.assertTrue(app.getAttributeHelper().getStatusPageCode(), "Check page status code");
        Assert.assertEquals(app.getAttributeHelper().getCurrentPageUrl(), TestLinks.myClassroomLink);
    }

    @Test(description = "Check Blog link", groups = {"fullGroup"}, priority = 1)
    @Severity(SeverityLevel.MINOR)
    @Description("Check 'Blog' link IN NAVBAR")
    public void checkBlogLinkTest(){
        app.getNavigationHelper().goAllCoursesPage();
        app.getAttributeHelper().waitForElements();
        app.getNavigationHelper().goLinkPage("Blog");
        app.getAttributeHelper().waitForElements();
        Assert.assertTrue(app.getAttributeHelper().getStatusPageCode(), "Check page status code");
        Assert.assertEquals(app.getAttributeHelper().getCurrentPageUrl(), TestLinks.blogLink);
    }

    @Test(description = "Check For Enterprise link", groups = {"fullGroup"}, priority = 2)
    @Severity(SeverityLevel.TRIVIAL)
    @Description("Check 'For Enterprise' link IN NAVBAR")
    public void checkEnterpriseLinkTest(){
        app.getNavigationHelper().goAllCoursesPage();
        app.getAttributeHelper().waitForElements();
        app.getNavigationHelper().goLinkPage("For Enterprise");
        app.getAttributeHelper().waitForElements();
        Assert.assertTrue(app.getAttributeHelper().getStatusPageCode(), "Check page status code");
        Assert.assertEquals(app.getAttributeHelper().getCurrentPageUrl(), TestLinks.forEnterpriseLink);
    }

}