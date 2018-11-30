import org.testng.Assert;
import org.testng.annotations.Test;

public class AccountInfoTest extends TestBase {

    @Test(description = "Verify user personal Information")
    public void userInfoTest(){
        app.getAccountHelper().moveToUserInfo();
        Assert.assertEquals(app.getAccountHelper().getFirstName(), app.getUser().getUserFirstName());
        System.out.println("FIRST_USER_NAME     PASS");
        Assert.assertEquals(app.getAccountHelper().getLastName(), app.getUser().getUserLastName());
        System.out.println("LAST_USER_NAME  PASS");
        Assert.assertEquals(app.getAccountHelper().getEmail(), app.getUser().getEmail());
        System.out.println("EMAIL   PASS");
    }

}
