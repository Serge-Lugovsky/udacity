import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ScreenShotOnFailListener.class)
public class VerifyCourseInfoTest extends TestBase {

    @Test(priority = 1, description = "VERIFY COURSE LINK TEXT", invocationCount = 1,
            groups = {"debugGroup", "fullGroup"})
    @Severity(SeverityLevel.NORMAL)
    @Story("VERIFY COURSE LINK TEXT")
    @Description("VERIFY COURSE LINK TEXT")
    public void verifyCourseLinkTextTest(){
        app.getNavigationHelper().goAllCoursesPage();
        app.getAttributeHelper().waitForElements();
        app.getCourseHelper().searchCourses(app.getCourse().getCourseName());
        Assert.assertEquals(app.getAttributeHelper().getSearchResult(), app.getCourse().getCourseName());
        app.getAttributeHelper().getFirstCourseLinkText();
        app.getNavigationHelper().goFirstCoursePage();
        Assert.assertTrue(app.getAttributeHelper().getCourseH1Text().toLowerCase().trim()
                .contains(app.getCourse().getCourseName()));
        Assert.assertTrue(app.getAttributeHelper().getCourseH1Text().toLowerCase().trim()
                .contains(app.getAttributeHelper().firstCourseLinkText().toLowerCase().trim()));
    }

    @Test(priority = 2, description = "VERIFY COURSE LEARN MORE BTN IN SUBMENU", invocationCount = 1,
            groups = {"debugGroup", "fullGroup"})
    @Severity(SeverityLevel.NORMAL)
    @Story("VERIFY COURSE LEARN MORE BTN IN SUBMENU")
    @Description("VERIFY COURSE LEARN MORE BTN IN SUBMENU")
    public void verifyCourseLearnMoreButtonTest(){
        app.getNavigationHelper().goAllCoursesPage();
        app.getAttributeHelper().waitForElements();
        app.getCourseHelper().searchCourses(app.getCourse().getCourseName());
        Assert.assertEquals(app.getAttributeHelper().getSearchResult(), app.getCourse().getCourseName());
        app.getCourseHelper().openCourseSubMenu();
        Assert.assertTrue(app.getCourseHelper().findLearnMoreBtn());
    }

}
