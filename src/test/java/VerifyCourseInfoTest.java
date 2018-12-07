import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyCourseInfoTest extends TestBase {

    @Test(priority = 1, description = "VERIFY COURSE LINK TEXT")
    public void verifyCourseLinkTextTest(){
        app.getNavigationHelper().goAllCoursesPage();
        app.getCourseHelper().searchCourses();
        app.getAttributeHelper().getFirstCourseLinkText();
        app.getNavigationHelper().goFirstCoursePage();
        Assert.assertTrue(app.getAttributeHelper().getCourseH1Text().toLowerCase().trim()
                .contains(app.getAttributeHelper().firstCourseLinkText().toLowerCase().trim()));
        System.out.println("COURSE LINK TEXT IS CONTAINS COURSE H1 TEXT!");
    }

    @Test(priority = 2, description = "VERIFY COURSE LEARN MORE BTN IN SUBMENU")
    public void verifyCourseLearnMoreButtonTest(){
        app.getNavigationHelper().goAllCoursesPage();
        app.getCourseHelper().searchCourses();
        app.getCourseHelper().openCourseSubMenu();
        Assert.assertTrue(app.getCourseHelper().findLearnMoreBtn());
        System.out.println("LEARN MORE BTN IS DISPLAYED!");
    }


}