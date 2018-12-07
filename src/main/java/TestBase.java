import Managers.AppManager;
import Managers.SingletonAppManager;
import org.testng.Assert;
import org.testng.annotations.*;


public class TestBase{
    AppManager app = SingletonAppManager.getInstance().manager;

    @BeforeMethod
    public void login(){
        app.getUserHelper().popUpClose();
        app.getNavigationHelper().goLoginPage();
        app.getUserHelper().loginAs(app.getUser());
        Assert.assertTrue(app.getUserHelper().verifyAuth());
        System.out.println("SUCCESS LOGIN");
    }

    @AfterMethod
    public void logOut(){
        app.getUserHelper().logOutFromAccount();
        Assert.assertTrue(app.getUserHelper().verifyLogOut());
        System.out.println("LOGOUT SUCCESS!");
    }

    @AfterSuite
    public void quit(){
        app.getDriver().quit();
    }

}
