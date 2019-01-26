import Base.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Listeners.ScreenShotOnFailListener;

@Listeners({ScreenShotOnFailListener.class})
public class VerifyCourseInfoTest extends TestBase {

    @Test(priority = 1, description = "VERIFY COURSE LINK TEXT", groups = {"debugGroup", "fullGroup"})
    @Severity(SeverityLevel.NORMAL)
    @Description("VERIFY COURSE LINK TEXT IN THE COURSE LIST")
    public void verifyCourseLinkTextTest(){
        app.getNavigationHelper().goAllCoursesPage();
        app.getAttributeHelper().waitForElements();
        app.getCourseHelper().searchCourses(app.getCourse().getCourseName());
        Assert.assertEquals(app.getAttributeHelper().getSearchResult(), app.getCourse().getCourseName());
        app.getAttributeHelper().getFirstCourseLinkText();
        app.getNavigationHelper().goFirstCoursePage();
        Assert.assertTrue(app.getAttributeHelper().getCourseH1Text().toLowerCase().trim()
                .contains(app.getCourse().getCourseName()),
                "H1 text on 'Course' page isn't contains 'Course' name.");
        Assert.assertTrue(app.getAttributeHelper().getCourseH1Text().toLowerCase().trim()
                .contains(app.getAttributeHelper().firstCourseLinkText().toLowerCase().trim()),
                "H1 text on 'Course' page isn't contains 'Course link' text.");
    }

    @Test(priority = 2, description = "VERIFY COURSE LEARN MORE BUTTON", groups = {"debugGroup", "fullGroup"})
    @Severity(SeverityLevel.NORMAL)
    @Description("VERIFY COURSE LEARN MORE BUTTON IN SUBMENU")
    public void verifyCourseLearnMoreButtonTest(){
        app.getNavigationHelper().goAllCoursesPage();
        app.getAttributeHelper().waitForElements();
        app.getCourseHelper().searchCourses(app.getCourse().getCourseName());
        Assert.assertEquals(app.getAttributeHelper().getSearchResult(), app.getCourse().getCourseName());
        app.getCourseHelper().openCourseSubMenu();
        Assert.assertTrue(app.getCourseHelper().findLearnMoreBtn(), "Button  'Learn more' isn't displayed.");
    }

}
