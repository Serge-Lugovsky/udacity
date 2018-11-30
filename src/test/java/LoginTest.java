import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @Test(description = "Login user")
    public void loginTest(){
        app.getUserHelper().loginAs(app.getUser());
        Assert.assertTrue(app.getUserHelper().verifyAuth());
            System.out.println("SUCCESS LOGIN");
    }

}
