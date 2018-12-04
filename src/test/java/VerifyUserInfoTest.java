import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyUserInfoTest extends TestBase {

    @Test(description = "Verify user personal Information")
    public void verifyUserInfoTest(){
        app.getNavigationHelper().moveToUserInfo();
        Assert.assertEquals(app.getAttributeHelper().getFirstName(), app.getUser().getUserFirstName());
        System.out.println("FIRST_USER_NAME     PASS");
        Assert.assertEquals(app.getAttributeHelper().getLastName(), app.getUser().getUserLastName());
        System.out.println("LAST_USER_NAME  PASS");
        Assert.assertEquals(app.getAttributeHelper().getEmail(), app.getUser().getEmail());
        System.out.println("EMAIL   PASS");
    }

}
